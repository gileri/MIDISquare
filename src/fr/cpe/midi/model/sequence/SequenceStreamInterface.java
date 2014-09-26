package fr.cpe.midi.model.sequence;

import javax.sound.midi.Sequence;

public interface SequenceStreamInterface {
	public Sequence getSequence();

	public int getTempo();
}
