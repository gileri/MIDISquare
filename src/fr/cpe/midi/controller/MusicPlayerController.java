package fr.cpe.midi.controller;

import java.net.URI;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

import fr.cpe.midi.model.Observer;
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
		player.togglePause();
	}

	public void loadSequence(URI uri) {
		player.loadSequenceFromUri(uri);
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
