package fr.cpe.midi.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.ShortMessage;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import fr.cpe.midi.controller.MusicPlayerController;
import fr.cpe.midi.model.Observer;

public class Fenetre extends JFrame implements Observer {

	/**
	 * Merci Eclipse de ne plus me faire ****
	 */
	private static final long serialVersionUID = 1L;

	private JPanel container = new JPanel(new BorderLayout());
	private JPanel topContainer = new JPanel();

	private static String[] playPause = { "Play", "Pause" };

	private final static String[] sources = { "Aucune", "Aléatoire", "Fichier",
			"Serveur" };

	private final JComboBox<String> combo = new JComboBox<String>(sources);
	private final JButton playPauseButton = new JButton(playPause[0]);
	private final JButton stopButton = new JButton("Stop");
	private final JLabel statusLabel = new JLabel("Aucun morceau en lecture");
	private final JButton fileChooserButton = new JButton("Choisir fichier");
	private final JFileChooser fileChooser = new JFileChooser();

	private MusicPlayerController controler;

	public Fenetre() {
		this.setTitle("Music player");
		this.setSize(800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane(container);
		initComposants();
		this.setVisible(true);
	}

	private void initComposants() {

		container.setLayout(new BorderLayout());
		ComboListener comboListener = new ComboListener();
		ButtonListener buttonListener = new ButtonListener();

		combo.addActionListener(comboListener);

		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"Fichiers MIDI", "mid");
		fileChooser.setFileFilter(filter);

		playPauseButton.setEnabled(false);
		stopButton.setEnabled(false);
		fileChooserButton.setVisible(false);

		fileChooserButton.addActionListener(buttonListener);
		playPauseButton.addActionListener(buttonListener);
		stopButton.addActionListener(buttonListener);

		topContainer.setLayout(new FlowLayout());
		topContainer.add(combo);
		topContainer.add(fileChooserButton);
		topContainer.add(playPauseButton);
		topContainer.add(stopButton);

		container.add(topContainer, BorderLayout.NORTH);

	}

	@Override
	public void update() {

	}

	private void updateUI() {
		String btnTxt = (controler.isPlaying()) ? playPause[1] : playPause[0];
		fileChooserButton.setVisible(combo.getSelectedIndex() == 2);
		playPauseButton.setText(btnTxt);
		playPauseButton.setEnabled(controler.canPlaySomething());
		stopButton.setEnabled(controler.isPlaying());
		combo.setEnabled(!controler.isPlaying());
	}

	class ComboListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			controler.clearPlayer();
			switch (sources[combo.getSelectedIndex()]) {
			case "Aléatoire":
				controler.loadSequence("random:/");
				break;
			case "Fichier":

				break;
			case "Serveur":
				controler.loadSequence("random:/");
				break;

			}
			updateUI();
		}
	}

	class ButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton) e.getSource();
			switch (b.getText()) {
			case "Play":
				try {
					controler.play();
				} catch (MidiUnavailableException e1) {
					e1.printStackTrace();
				} catch (InvalidMidiDataException e1) {
					e1.printStackTrace();
				}
				break;
			case "Pause":
				controler.pause();
				break;
			case "Stop":
				controler.stop();
				break;
			case "Choisir fichier":
				int returnVal = fileChooser.showOpenDialog(topContainer);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					System.out.println("Fichier choisit : "
							+ fileChooser.getSelectedFile().getAbsolutePath());
					controler.loadSequence("file://"
							+ fileChooser.getSelectedFile().getPath());
				}
			}
			updateUI();
		}
	}

	@Override
	public void controlChange(ShortMessage event) {
		// TODO Auto-generated method stub

	}

	public void setController(MusicPlayerController c) {
		this.controler = c;
		Visualization v = new Visualization();
		controler.addListener(v);
		container.add(v, BorderLayout.CENTER);
	}
}
