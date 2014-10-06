package fr.cpe.midi.model;

import java.net.URI;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

public interface PlayerInterface {
	
	public void play() throws MidiUnavailableException, InvalidMidiDataException;
	public void pause();
	public void stop();
	public boolean isRunning();
	public void loadSequenceFromUri(URI uri);
}
