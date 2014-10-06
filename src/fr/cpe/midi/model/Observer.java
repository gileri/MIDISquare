package fr.cpe.midi.model;

import javax.sound.midi.ControllerEventListener;

public interface Observer extends ControllerEventListener {
	public void update();
}