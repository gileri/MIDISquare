package View;

import java.net.URI;
import java.net.URISyntaxException;

import Model.Player;

public class Console {

	public static void main(String[] args) {
		Player player = new Player();
		try {
			player.loadSequenceFromUri(new URI("file:/Users/nicolas/Development/Etudes/Java/workspace/MIDISquare/src/nb.mid"));
		} catch (URISyntaxException e) {
			System.err.println("Exception : \n");
		}
	}

}
