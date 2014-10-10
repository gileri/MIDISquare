package fr.cpe.midi.server;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.rmi.RemoteException;

public class SequenceServer implements SequenceServerInterface {

	@Override
	public byte[] getSequence(String path) throws RemoteException {
		 try {
			return Files.readAllBytes(Paths.get(path));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
