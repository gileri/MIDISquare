package fr.cpe.midi.model;

import java.net.URI;
import java.util.HashMap;
import java.util.List;

import fr.cpe.midi.ClassFinder;
import fr.cpe.midi.model.sequence.SequenceStreamFile;
import fr.cpe.midi.model.sequence.SequenceStreamRandom;
import fr.cpe.midi.model.sequence.SequenceStreamServer;

/**
 * Instancie la "bonne" classe dérivée de SequenceStream
 *
 */
public class SequenceStreamFactory {

	private static SequenceStreamFactory sequenceStreamFactory;
	private static HashMap<String, Class<?>> sequenceStreamMap = new HashMap<String, Class<?>>();

	public SequenceStreamFactory() {
		sequenceStreamMap.put(SequenceStreamFile.SCHEME, SequenceStreamFile.class);
		sequenceStreamMap.put(SequenceStreamRandom.SCHEME, SequenceStreamRandom.class);
		sequenceStreamMap.put(SequenceStreamServer.SCHEME, SequenceStreamServer.class);
	}

	public static SequenceStreamFactory getInstance() {
		return (sequenceStreamFactory == null) ? new SequenceStreamFactory()
				: sequenceStreamFactory;
	}

	/**
	 * Instancie la bonne classe dérivée de SequenceStream en fonction du schema
	 * de l'URI.
	 */
	public SequenceStreamInterface loadSequenceFromUri(URI uri) {
		String key = uri.getScheme();
		ClassLoader c = this.getClass().getClassLoader();
		SequenceStreamInterface s = null;
		try {
			s = (SequenceStreamInterface) sequenceStreamMap.get(key)
					.newInstance();
			s.setPath(uri.getPath());
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return s;
	}
}
