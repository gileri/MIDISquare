package fr.cpe.midi.model;

import java.net.URI;
import java.util.ArrayList;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequencer;

import fr.cpe.midi.model.sequence.SequenceStreamFactory;
import fr.cpe.midi.model.sequence.SequenceStreamInterface;

public class Player implements fr.cpe.midi.model.Observable {

	private static Player player;
	private Sequencer sequencer;
	private SequenceStreamInterface sequence;
	private ArrayList<Observer> listObserver = new ArrayList<Observer>();
	protected String status;

	// DP Singleton
	public static Player getInstance() throws MidiUnavailableException {
		return (player == null) ? new Player() : player;
	}

	private Player() throws MidiUnavailableException {
		status = "Waiting for a song";
		sequencer = MidiSystem.getSequencer();
	}

	public SequenceStreamInterface getSequence() {
		return sequence;
	}

	public void play() throws MidiUnavailableException,
			InvalidMidiDataException {
		if (sequence == null)
			return;

		sequencer.open();
		sequencer.setSequence(sequence.getSequence());
		status = sequence.getDescription();
		sequencer.setTempoInBPM(sequence.getTempo());
		sequencer.start();
	}

	public void pause() {
		sequencer.stop();
	}

	public void stop() {
		if (sequence == null)
			return;
		if (sequencer != null) {
			sequencer.stop();
			sequencer.close();
		}
		this.sequence = null;
	}

	public boolean isRunning() {
		return (sequencer != null) ? sequencer.isRunning() : false;
	}

	/**
	 * Récupère la séquence à l'URI passée en paramètre en utilisant la
	 * SequenceStreamFactory
	 * 
	 * @param uri
	 *            URI correspondant à la séquence
	 */
	public void loadSequenceFromUri(URI uri) {
		this.sequence = SequenceStreamFactory.getInstance()
				.loadSequenceFromUri(uri);
	}

	@Override
	public void addObserver(Observer obs) {
		sequencer.addControllerEventListener(obs, new int[] { 127 });
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
}
