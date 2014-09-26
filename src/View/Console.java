package View;

import java.net.URI;
import java.net.URISyntaxException;

import Model.Player;

public class Console {

	public static void main(String[] args) {
		Player player = new Player();
		try {
			player.loadSequenceFromUri(new URI("file:" + args[0]));
			while(true) {
				player.play();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				player.pause();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (URISyntaxException e) {
			System.err.println("Exception : \n");
		}
	}

}
