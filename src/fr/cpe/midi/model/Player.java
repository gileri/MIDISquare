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
	
	private Sequencer sequencer;
	private SequenceStreamInterface sequence;
	private ArrayList<Observer> listObserver = new ArrayList<Observer>();
	
	public Player()
	{
		try {
			sequencer = MidiSystem.getSequencer();
		} catch(MidiUnavailableException e) {
			e.printStackTrace();
		}
	}

	public void play() {
		if (sequence == null) {
			System.err.println("Aucune séquence sélectionnée");
			return;
		}
		try {
			sequencer.open();
			sequencer.setSequence(sequence.getSequence());
			sequencer.setTempoInBPM(sequence.getTempo());
			sequencer.start();
		} catch (InvalidMidiDataException e) {
			System.err.println("Error in MIDI file");
			e.printStackTrace();
		} catch (MidiUnavailableException e) {
			System.err.println("MIDI device is not available");
			e.printStackTrace();
		}
	}

	public void pause() {
		if (sequencer.isRunning())
			sequencer.stop();
		else if (sequencer.isOpen()) {
			sequencer.start();
		}
	}

	public void stop() {
		if (sequence == null) {
			System.err.println("Aucune séquence sélectionnée");
			return;
		}
		sequencer.stop();
		sequencer.close();
		this.sequence = null;
	}

	/**
	 * Récupère la séquence à l'URI passée en paramètre en utilisant la SequenceStreamFactory 
	 * @param uri URI correspondant à la séquence
	 */
	public void loadSequenceFromUri(URI uri) {
		this.sequence = SequenceStreamFactory.getInstance()
				.loadSequenceFromUri(uri);
	}

	@Override
	public void addObserver(Observer obs) {
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
