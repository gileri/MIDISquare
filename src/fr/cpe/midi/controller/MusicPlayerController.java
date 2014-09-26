package fr.cpe.midi.controller;

import java.net.URI;
import java.net.URISyntaxException;

import fr.cpe.midi.model.Player;

public class MusicPlayerController {
	
	private Player player;
	
	public MusicPlayerController(Player player) {
		this.player = player;
	}
	
	public void play() {
		player.play();
	}
	
	public void stop() {
		player.stop();
	}
	
	public void pause() {
		player.pause();
	}
	
	public void loadSequence(String str) {
		try {
			player.loadSequenceFromUri(new URI(str));
		} catch (URISyntaxException e) {
			System.err.println("URI incorrect");
			e.printStackTrace();
		}
	}
}
