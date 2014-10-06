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

	private Player() throws MidiUnavailableException {
		status = "Waiting for a song";
		sequencer = MidiSystem.getSequencer();
	}

	public static Player getInstance() throws MidiUnavailableException {
		if (player == null) {
			player = new Player();
		}
		return player;
	}

	public void play() throws MidiUnavailableException,
			InvalidMidiDataException {
		if (sequence == null) {
			return;
		}
		sequencer.open();
		sequencer.setSequence(sequence.getSequence());
		sequencer.setTempoInBPM(sequence.getTempo());
		this.status = "Current song : " + sequence.getDescription();
		sequencer.start();

	}

	public SequenceStreamInterface getSequence() {
		return sequence;
	}

	public void pause() {
		sequencer.stop();
	}

	public boolean isRunning() {
		if (sequencer != null)
			return sequencer.isRunning();
		return false;
	}

	public void stop() {
		if (sequence == null) {
			// System.err.println("Aucune séquence sélectionnée");
			return;
		}
		if (sequencer != null) {
			sequencer.stop();
			sequencer.close();
		}
		this.sequence = null;
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
