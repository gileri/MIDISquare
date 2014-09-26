package fr.cpe.midi.model.sequence;

import javax.sound.midi.Sequence;

/**
 * Représente une Sequence, ainsi que son tempo
 * 
 */
public abstract class SequenceStream {
	protected Sequence sequence;
	protected int tempo;
	
	public Sequence getSequence() {
		return sequence;
	}
	
	public int getTempo() {
		return tempo;
	}
}
