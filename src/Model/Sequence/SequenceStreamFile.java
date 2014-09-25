package Model.Sequence;

import java.io.File;
import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;

public class SequenceStreamFile extends SequenceStream implements SequenceStreamInterface{

	public SequenceStreamFile(String path) {
		try {
			sequence = MidiSystem.getSequence(new File(path));	
		} catch (IOException e){
			e.printStackTrace();
		} catch (InvalidMidiDataException e) {
			e.printStackTrace();
		}
	}
}
