package fr.cpe.midi.model.sequence;

import javax.sound.midi.Sequence;

/**
 * Représente une Sequence, ainsi que son tempo
 * 
 */
public abstract class SequenceStream {

	public static final String SCHEME = null;

	protected Sequence sequence;
	protected String description;
	protected int tempo;

	public Sequence getSequence() {
		return sequence;
	}

	public int getTempo() {
		return tempo;
	}

	public String getDescription() {
		return description;
	}
}
