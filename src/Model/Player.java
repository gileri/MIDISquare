package Model;

import java.net.URI;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequencer;

import Model.Sequence.SequenceStreamFactory;
import Model.Sequence.SequenceStreamInterface;

public class Player {
	
	private Sequencer sequencer;
	private SequenceStreamInterface sequence;
	
	
	public Player()
	{
		try {
			sequencer = MidiSystem.getSequencer();
		} catch(MidiUnavailableException e) {
			e.printStackTrace();
		}
	}
	
	public void play()
	{
		if(sequence==null) {
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
		if(sequencer.isRunning())
			sequencer.stop();
		else if(sequencer.isOpen()){
			sequencer.start();
		}
	}
	
	public void stop()
	{
		if(sequence==null) {
			System.err.println("Aucune séquence sélectionnée");
			return;
		}
		sequencer.stop();
		sequencer.close();
		this.sequence = null;
	}
	
	public void loadSequenceFromUri(URI uri)
	{
		this.sequence = SequenceStreamFactory.getInstance().loadSequenceFromUri(uri);
	}

}
