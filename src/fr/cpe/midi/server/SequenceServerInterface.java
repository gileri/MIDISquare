package fr.cpe.midi.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SequenceServerInterface extends Remote {
	public byte[] getSequence(String path) throws RemoteException;
}
