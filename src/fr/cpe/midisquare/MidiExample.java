package fr.cpe.midisquare;
/**
 * @author Kathy Sierra, Bert Bates : "Java Tête la Première" 
 * Mise en forme des commentaires Françoise PERRIN
 */

/**
 * Le programme suivant affiche des formes dans un panneau 
 * au rythme d'une partition de musique qu'il a préalablement construite
 * 
 * Pour quun son sorte des haut-parleurs, il faut envoyer les données MIDI à un équipement MIDI,
 * qui lit les instructions et les transforme en son, 
 * en déclenchant un instrument réel ou un instrument « virtuel » (un programme synthétiseur).
 * 
 * Il faut QUATRE composants :
 * 1 - Celui qui joue la musique et restitue réellement le son : le séquenceur (le voir comme un lecteur de CD)
 * 2 - la musique à jouer : la séquence est le morceau joué par le séquenceur (la voir comme 1 CD)
 * 3 - La partie de la séquence qui contient les vraies informations : une piste (la voir comme 1 morceau du CD)
 * 4 - Les vraies informations contenues dans une piste sont des événements MIDI : quelles notes jouer, combien de temps, etc.
 * Un événement MIDI est un message compréhensible par le séquenceur. Il pourrait dire (sil parlait) : 
 * « Maintenant joue un do, joue-le à cette vitesse et tiens la note pendant tant de temps ».
 * Un événement MIDI pourrait également dire par exemple : « Change linstrument courant en flûte ».
 */

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;

import javax.sound.midi.ControllerEventListener;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;
import javax.swing.JFrame;
import javax.swing.JPanel;




// ce programme joue une musique aléatoire,et affiche des rectangles pleins, en rythme.

public class MidiExample {

	static JFrame f = new JFrame("Mon nouveau clip vidéo");
	static MonPanneau ml;

	public static void main(String[] args) {
		MidiExample mini = new MidiExample();
		mini.go();
	}

	public void go() {

		installerIHM();

		/*
		 * Le processus comprend 5 étapes
		 * 1 - Obtenir un séquenceur, un objet Sequencer, et louvrir
		 * 2 - Créer un nouvel objet Sequence
		 * 3 - Demander à la Sequence de créer une piste piste de type Track
		 * 4 - Remplir la piste dévénements MIDI  MidiEvents  et transmettre la séquence au séquenceur
		 * 5 - Démarrer le séquenceur avec la méthode start() 
		 */

		try {

			// créer (et ouvrir) un séquenceur
			Sequencer sequenceur = MidiSystem.getSequencer();
			sequenceur.open();


			// créer une séquence et une piste
			Sequence seq = MidiSystem.getSequence(new File("./src/nb.mid"));
			Track piste = seq.createTrack();

			// maintenant créer deux événements midi (contenant un message midi)
			int r = 0;
			for (int i = 0; i < 100; i+= 4) {

				r = (int) ((Math.random() * 50) + 1);
				
				// ajouter les événements à la piste
				
				// 144 = noteOn, 1 = piano, 44 = la note, 100 = vélocité
				piste.add(makeEvent(144,1,r,100,i));
				
				/* Pour suivre le rythme. Nous insérons notre PROPRE ControllerEvent :
				 * 176 indique que le type de l'événement est ControllerEvent) 
				 * avec un argument pour le numéro d'événement, 127. 
				 * Cet événement ne fera RIEN ! 
				 * Il n'est là QUE pour que nous ayons un événement chaque fois qu'une note est jouée. 
				 * Autrement dit, sa seule raison d'être est qu'un évènement se déclenche 
				 * que NOUS puissions écouter (impossible d'écouter NOTE ON/OFF ). 
				 * Cet événement a lieu sur le MÊME temps que NOTE ON. 
				 */
				piste.add(makeEvent(176,1,127,0,i));
				
				// 128 = noteOff				
				piste.add(makeEvent(128,1,r,100,i + 2));
				
			} // fin de la boucle

		
			// ajouter la séquence au séquenceur, fixer le timing et démarrer
			sequenceur.setSequence(seq);
			sequenceur.setTempoInBPM(120);
			sequenceur.start();
			
			/* le panneau de dessin (écouteur) doit s'enregistrer auprès du séquenceur. 
			 * La méthode d'enregistrement accepte l'écouteur 
			 * ET un tableau d'entiers représentant la liste d'événements voulus. 
			 * Ici, nous n'en voulons qu'un, le N° 127.
			 */
			sequenceur.addControllerEventListener(ml, new int[] {127});
			
		} catch (Exception ex) {ex.printStackTrace();}
	} // fin de la méthode go()


	public MidiEvent makeEvent(int comd, int can, int un, int deux, int tic) {
		MidiEvent evenement = null;
		try {
			ShortMessage a = new ShortMessage();
			a.setMessage(comd, can, un, deux);
			evenement = new MidiEvent(a, tic);

		}catch(Exception e) { }
		return evenement;
	}

	public  void installerIHM() {
		ml = new MonPanneau();
		f.setContentPane(ml);
		f.setBounds(30,30, 300,300);
		f.setVisible(true);
	}


	/* le panneau de dessin va écouter les ControllerEvents
	 * et donc doit implémenter l'interface écouteur
	 */
	class MonPanneau extends JPanel implements ControllerEventListener {

		// nous ne voulons d'images que si nous avons un événement
		boolean msg = false;

		public void controlChange(ShortMessage evenement) {
			msg = true;
			repaint();
		}

		public void paintComponent(Graphics g) {
			if (msg) {

				int r = (int) (Math.random() * 250);
				int gr = (int) (Math.random() * 250);
				int b = (int) (Math.random() * 250);

				g.setColor(new Color(r,gr,b));

				int ht = (int) ((Math.random() * 120) + 10);
				int width = (int) ((Math.random() * 120) + 10);

				int x = (int) ((Math.random() * 40) + 10);
				int y = (int) ((Math.random() * 40) + 10);

				g.fillRect(x,y,ht, width);
				msg = false;
			} 
		} 
	}  

} 
