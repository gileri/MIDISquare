package fr.cpe.midi;

import java.net.URI;
import java.net.URISyntaxException;

import fr.cpe.midi.controller.MusicPlayerController;
import fr.cpe.midi.model.Player;
import fr.cpe.midi.view.Fenetre;


public class Programme {

	public static void main(String[] args) throws URISyntaxException {
		Player p = new Player();
		p.loadSequenceFromUri(new URI("file:/home/twix/CPE/POO/MIDISquare/nb.mid"));
		MusicPlayerController m = new MusicPlayerController(p);
		p.play();
		Fenetre ff = new Fenetre();
	}

}
