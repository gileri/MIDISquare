package Model;

import java.net.URI;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequencer;

import Model.Sequence.SequenceStream;
import Model.Sequence.SequenceStreamFactory;

public class Player {
	
	private Sequencer sequencer;
	private SequenceStream sequence;
	
	
	public Player()
	{
		try {
			sequencer = MidiSystem.getSequencer();
			sequencer.open();
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
	}
	
	public void stop()
	{
		if(sequence==null) {
			System.err.println("Aucune séquence sélectionnée");
			return;
		}
	}
	
	public void loadSequenceFromUri(URI uri)
	{
		SequenceStreamFactory.getInstance().loadSequenceFromUri(uri);
	}
	
	

}
