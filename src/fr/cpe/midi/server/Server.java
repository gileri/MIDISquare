package fr.cpe.midi.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {
	public static void main(String[] args) {
		try {
			int port = 1099;
			SequenceServerInterface si = (SequenceServerInterface) UnicastRemoteObject
					.exportObject(new SequenceServer(), port);
			Registry registry = LocateRegistry.createRegistry(port);
			registry.rebind("SequenceServer", si);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
