package fr.cpe.midi.model.sequence;

import java.io.File;
import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;

public class SequenceStreamFile extends SequenceStream implements
		SequenceStreamInterface {

	public SequenceStreamFile(String path) {
		try {
			description = path;
			sequence = MidiSystem.getSequence(new File(path));
			this.tempo = 120;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidMidiDataException e) {
			e.printStackTrace();
		}
	}
}
