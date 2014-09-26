package Model.Sequence;

import java.net.URI;

import javax.sound.midi.Sequence;

public abstract class SequenceStream {
	protected Sequence sequence;
	public Sequence getSequence() {
		return sequence;
	}
}
