package fr.cpe.midi.model.sequence;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.Sequence;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

import fr.cpe.midi.model.SequenceStream;
import fr.cpe.midi.model.SequenceStreamInterface;

public class SequenceStreamRandom extends SequenceStream implements
		SequenceStreamInterface {
	public static final String SCHEME = "random";

	public SequenceStreamRandom() {
		description = "Random song";
		try {
			sequence = new Sequence(Sequence.PPQ, 4);
			Track piste = sequence.createTrack();
			// maintenant créer deux événements midi (contenant un message midi)
			int r = 0;
			for (int i = 0; i < 100; i += 4) {
				r = (int) ((Math.random() * 50) + 1);
				// ajouter les événements à la piste
				// 144 = noteOn, 1 = piano, 44 = la note, 100 = vélocité
				piste.add(makeEvent(144, 1, r, 100, i));
				/*
				 * Pour suivre le rythme. Nous insérons notre PROPRE
				 * ControllerEvent : 176 indique que le type de l'événement est
				 * ControllerEvent) avec un argument pour le numéro d'événement,
				 * 127. Cet événement ne fera RIEN ! Il n'est là QUE pour que
				 * nous ayons un événement chaque fois qu'une note est jouée.
				 * Autrement dit, sa seule raison d'être est qu'un évènement se
				 * déclenche que NOUS puissions écouter (impossible d'écouter
				 * NOTE ON/OFF ). Cet événement a lieu sur le MÊME temps que
				 * NOTE ON.
				 */
				piste.add(makeEvent(176, 1, 127, 0, i));
				// 128 = noteOff
				piste.add(makeEvent(128, 1, r, 100, i + 2));
			} // fin de la boucle
			this.tempo = 120;
		} catch (InvalidMidiDataException e) {
			System.err.println("Midi invalide");
			e.printStackTrace();
		}
	}

	public MidiEvent makeEvent(int comd, int can, int un, int deux, int tic) {
		MidiEvent evenement = null;
		try {
			ShortMessage a = new ShortMessage();
			a.setMessage(comd, can, un, deux);
			evenement = new MidiEvent(a, tic);

		} catch (Exception e) {
		}
		return evenement;
	}

	@Override
	public void setPath(String path) {

	}
}
