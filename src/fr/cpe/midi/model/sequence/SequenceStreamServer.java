package fr.cpe.midi.model.sequence;

import java.io.File;
import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;

import fr.cpe.midi.model.SequenceStream;
import fr.cpe.midi.model.SequenceStreamInterface;

/**
 * TODO Class loading a sequence from a remote server
 *
 */
public class SequenceStreamServer extends SequenceStream implements
		SequenceStreamInterface {

	public static final String SCHEME = "server";

	public SequenceStreamServer(String path) {
		description = "Server song";
	}

	@Override
	public void setPath(String path) {
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
