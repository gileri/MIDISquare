package fr.cpe.midi.model.sequence;

import java.net.URI;

/**
 * Instancie la "bonne" classe dérivée de SequenceStream
 *
 */
public class SequenceStreamFactory {

	private static SequenceStreamFactory sequenceStreamFactory;

	public static SequenceStreamFactory getInstance() {
		if (sequenceStreamFactory == null)
			sequenceStreamFactory = new SequenceStreamFactory();

		return sequenceStreamFactory;
	}

	/**
	 * Instancie la bonne classe dérivée de SequenceStream en fonction du schema
	 * de l'URI.
	 */
	public SequenceStreamInterface loadSequenceFromUri(URI uri) {
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
