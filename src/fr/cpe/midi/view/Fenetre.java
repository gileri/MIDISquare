package fr.cpe.midi.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Iterator;

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

	URI sequenceURI;
	private JPanel container = new JPanel(new BorderLayout());
	private JPanel topContainer = new JPanel();

	private final static HashMap<String, String> sources = new HashMap<String, String>();

	private final JComboBox<String> combo = new JComboBox<String>();
	private final JButton playButton = new JButton("▶");
	private final JButton pauseButton = new JButton("❚❚");
	private final JButton stopButton = new JButton("■");
	private final JLabel statusLabel = new JLabel("Aucun morceau en lecture");
	private final JButton fileChooserButton = new JButton("Choisir fichier");
	private final JFileChooser fileChooser = new JFileChooser();

	private MusicPlayerController controler;

	public Fenetre() {
		sources.put("", "Aucune");
		sources.put("Aléatoire", "random:///");
		sources.put("Fichier", "file:///");
		sources.put("Server", "server:///");
		Iterator it = sources.keySet().iterator();
		while (it.hasNext()) {
			combo.addItem((String) it.next());
		}
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

		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"Fichiers MIDI", "mid");
		fileChooser.setFileFilter(filter);

		fileChooserButton.setVisible(false);
		
		combo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controler.clearPlayer();
				try {
					sequenceURI = new URI(sources.get(combo.getSelectedItem()));
				} catch (URISyntaxException e1) {
					// TODO Display an actual error
					e1.printStackTrace();
				}
				fileChooserButton.setVisible(combo.getSelectedIndex() == 3);
			}
		});
		fileChooserButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int returnVal = fileChooser.showOpenDialog(topContainer);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					System.out.println("Fichier choisi : "
							+ fileChooser.getSelectedFile().getAbsolutePath());
					sequenceURI = fileChooser.getSelectedFile().toURI();
					controler.loadSequence(sequenceURI);
				}
			}
		});
		playButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controler.loadSequence(sequenceURI);
				try {
					controler.play();
				} catch (MidiUnavailableException | InvalidMidiDataException e1) {
					e1.printStackTrace();
				}
			}
		});
		pauseButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controler.pause();
			}
		});
		stopButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controler.stop();
			}
		});

		topContainer.setLayout(new FlowLayout());
		topContainer.add(combo);
		topContainer.add(fileChooserButton);
		topContainer.add(playButton);
		topContainer.add(pauseButton);
		topContainer.add(stopButton);

		container.add(topContainer, BorderLayout.NORTH);

	}

	@Override
	public void update() {

	}

	class ComboListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			
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
