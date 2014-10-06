package fr.cpe.midi.view;

import java.awt.Color;
import java.awt.Graphics;

import javax.sound.midi.ShortMessage;
import javax.swing.JPanel;

import fr.cpe.midi.model.Observer;

public class Visualization extends JPanel implements Observer {
	// nous ne voulons d'images que si nous avons un événement
	boolean msg = false;

	public void paintComponent(Graphics g) {
		int maxwidth = this.getWidth();
		int maxheight = this.getWidth();
		if (msg) {

			int r = (int) (Math.random() * 250);
			int gr = (int) (Math.random() * 250);
			int b = (int) (Math.random() * 250);

			g.setColor(new Color(r, gr, b));

			int ht = (int) (Math.random() * maxheight );
			int width = (int) (Math.random() * maxwidth);

			int x = (int) (Math.random() * maxwidth - width);
			int y = (int) (Math.random() * maxheight - ht);

			g.fillRect(x, y, ht, width);
			msg = false;
		}
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
	}

	@Override
	public void controlChange(ShortMessage event) {
		msg = true;
		repaint();
	}

}
