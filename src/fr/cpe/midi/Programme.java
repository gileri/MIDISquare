package fr.cpe.midi;

import java.net.URISyntaxException;

import javax.sound.midi.MidiUnavailableException;

import fr.cpe.midi.controller.MusicPlayerController;
import fr.cpe.midi.model.Player;
import fr.cpe.midi.model.PlayerObservable;
import fr.cpe.midi.view.Fenetre;

public class Programme {

	public static void main(String[] args) throws URISyntaxException {
		Player p;
		try {
			Fenetre f = new Fenetre();
			p = Player.getInstance();
			PlayerObservable pl = new PlayerObservable(p);
			MusicPlayerController m = new MusicPlayerController(pl);
			f.setController(m);
		} catch (MidiUnavailableException e) {
			e.printStackTrace();
		}
	}

}
