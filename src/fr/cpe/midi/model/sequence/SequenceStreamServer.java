package fr.cpe.midi.model.sequence;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;

import fr.cpe.midi.model.SequenceStream;
import fr.cpe.midi.model.SequenceStreamInterface;
import fr.cpe.midi.server.SequenceServerInterface;

/**
 * TODO Class loading a sequence from a remote server
 *
 */
public class SequenceStreamServer extends SequenceStream implements
		SequenceStreamInterface {
	
	private SequenceServerInterface stub;

	public static final String SCHEME = "server";

	public SequenceStreamServer() {

	}

	@Override
	public void setPath(String path) {
		try {
			Registry registry = LocateRegistry.getRegistry(1099);

			// Récupération du stub lié au serveur
			this.stub = (SequenceServerInterface) registry.lookup("SequenceServer");
			byte[] bytes = this.stub.getSequence(path);
			description = path;
			sequence = MidiSystem.getSequence(new ByteArrayInputStream(bytes));
			tempo = 120;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidMidiDataException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
