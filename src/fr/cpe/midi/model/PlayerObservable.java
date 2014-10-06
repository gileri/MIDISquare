package fr.cpe.midi.model;

import java.net.URI;
import java.util.ArrayList;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

public class PlayerObservable implements fr.cpe.midi.model.Observable, PlayerInterface {
	private Player player;
	private ArrayList<Observer> listObserver = new ArrayList<Observer>();

	public PlayerObservable(Player player) {
		this.player = player;
	}

	@Override
	public void addObserver(Observer obs) {
		player.getSequencer().addControllerEventListener(obs, new int[] { 127 });
		this.listObserver.add(obs);
	}

	@Override
	public void removeObserver(Observer obs) {
		this.listObserver.remove(obs);
	}

	@Override
	public void notifyObserver() {
		for (Observer o : listObserver) {
			o.update();
		}
	}

	@Override
	public void play() throws MidiUnavailableException, InvalidMidiDataException {
		player.play();
	}

	@Override
	public void pause() {
		player.pause();
		
	}

	@Override
	public void stop() {
		player.stop();
	}

	@Override
	public boolean isRunning() {
		return player.isRunning();
	}

	@Override
	public void loadSequenceFromUri(URI uri) {
		player.loadSequenceFromUri(uri);
	}

	public boolean canPlaySomething() {
		return player.canPlaySomething();
	}
}
