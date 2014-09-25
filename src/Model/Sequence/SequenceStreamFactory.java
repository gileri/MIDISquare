package Model.Sequence;

import java.net.URI;

public class SequenceStreamFactory {
	
	private static SequenceStreamFactory sequenceStreamFactory;
	
	public static SequenceStreamFactory getInstance()
	{
		if(sequenceStreamFactory==null) 
			sequenceStreamFactory = new SequenceStreamFactory();
		
		return sequenceStreamFactory;
	}
	
	public SequenceStreamInterface loadSequenceFromUri(URI uri)
	{
		System.out.println(uri);
		
		switch (uri.getScheme()) {
		case "file":
			return new SequenceStreamFile(uri.getPath());
		case "random":
			return new SequenceStreamRandom();
		case "server":
			return new SequenceStreamServer(uri.getPath());
		default:
			return null;
		}
	}
}
