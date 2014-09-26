package View;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import Model.Player;

public class Console {
	
	private static void commandInterpreter(Player p) {
		try {
			char c = (char) System.in.read();
			switch (c) {
			case 'p':
				p.pause();
				break;
			default:
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		
		Player player = new Player();
		try {
			player.loadSequenceFromUri(new URI(args[0]));
			player.play();
			while (true) {
				commandInterpreter(player);
			}
		} catch (URISyntaxException e) {
			System.err.println("Wrong URI");
			System.err.println("Pass a correct URI as first parameter");
			System.exit(1);
		}

	}

}
