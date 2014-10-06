package fr.cpe.midi.controller;

import java.net.URI;
import java.net.URISyntaxException;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

import fr.cpe.midi.model.Observer;
import fr.cpe.midi.model.Player;
import fr.cpe.midi.model.PlayerObservable;

public class MusicPlayerController {

	private PlayerObservable player;

	public MusicPlayerController(PlayerObservable player) {
		this.player = player;
	}

	public void play() throws MidiUnavailableException,
			InvalidMidiDataException {
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

	public boolean canPlaySomething() {
		return (player.canPlaySomething());
	}

	public boolean isPlaying() {
		return player.isRunning();
	}

	public void clearPlayer() {
		player.stop();
	}

	public void addListener(Observer o) {
		player.addObserver(o);
	}
}
