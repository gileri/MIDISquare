package Model.Sequence;

import java.net.URI;

import javax.sound.midi.Sequence;

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
