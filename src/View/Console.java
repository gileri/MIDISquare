package View;

import java.net.URI;
import java.net.URISyntaxException;

import Model.Player;

public class Console {

	public static void main(String[] args) {
		Player player = new Player();
		try {
			URI fileUri = new URI("file:" + args[0]);
			URI randomUri = new URI("random:/");
			player.loadSequenceFromUri(fileUri);
			player.play();
		} catch (URISyntaxException e) {
			System.err.println("Exception : \n");
			e.printStackTrace();
		}
	}

}
