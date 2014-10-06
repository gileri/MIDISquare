package fr.cpe.midi.model.sequence;

import javax.sound.midi.Sequence;

public interface SequenceStreamInterface {
	public Sequence getSequence();

	public void setPath(String path);

	public int getTempo();

	public String getDescription();
}
