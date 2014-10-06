package fr.cpe.midi.view;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

import fr.cpe.midi.model.Player;

public class Console {

	private static void commandInterpreter(Player p) {
		try {
			char c = (char) System.in.read();
			switch (c) {
			case 'p':
				p.togglePause();
				break;
			default:
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {
			Player player = Player.getInstance();
			player.loadSequenceFromUri(new URI(args[0]));
			player.play();
			while (true) {
				commandInterpreter(player);
			}
		} catch (URISyntaxException e) {
			System.err.println("Wrong URI");
			System.err.println("Pass a correct URI as first parameter");
			System.exit(1);
		} catch (MidiUnavailableException e) {
			e.printStackTrace();
		} catch (InvalidMidiDataException e) {
			e.printStackTrace();
		}

	}

}
