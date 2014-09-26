package fr.cpe.midi;

import fr.cpe.midi.controller.MusicPlayerController;
import fr.cpe.midi.model.Player;
import fr.cpe.midi.view.Fenetre;


public class Programme {

	public static void main(String[] args) {
		Player p = new Player();
		MusicPlayerController m = new MusicPlayerController(p);
//		p.play();
		Fenetre ff = new Fenetre();
	}

}
