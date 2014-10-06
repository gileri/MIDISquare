package fr.cpe.midi.view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	
	private JPanel container = new JPanel();
	private JPanel topContainer = new JPanel();
	
	private static String[] sources = {"Aucune","Aléatoire","Fichier","Serveur"};
	private static String[] playPause = {"Play","Pause"}; 
	
	private final JComboBox<String>combo = new JComboBox<String>(sources);
	private final JButton playPauseButton = new JButton(playPause[0]);
	private final JButton stopButton = new JButton("Stop");
	private final JLabel statusLabel = new JLabel("Aucun morceau en lecture");
	private final JButton fileChooserButton = new JButton("Choisir fichier");
	private final JFileChooser fileChooser = new JFileChooser();
	
	private MusicPlayerController controler;

	public Fenetre(MusicPlayerController controler) {
		this.controler = controler;
		this.setTitle("Music player");
		this.setSize(800, 600);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setContentPane(container);
		initComposants();
		this.setVisible(true);
	}
	
	private void initComposants() {
		ComboListener comboListener = new ComboListener();
		ButtonListener buttonListener = new ButtonListener();
		
		combo.addActionListener(comboListener);
		
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichiers MIDI", "mid");
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
		
		container.add(topContainer);
	}

	@Override
	public void update() {
		
		
	}
	
	private void updateUI() {
		String btnTxt = (controler.isPlaying()) ? playPause[1] : playPause[0];
		fileChooserButton.setVisible(combo.getSelectedIndex()==2);
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
			JButton b = (JButton)e.getSource();
			switch(b.getText()) {
				case "Play":
					controler.play();
					break;
				case "Pause":
					controler.pause();
					break;
				case "Stop":
					controler.stop();
				break;
				case "Choisir fichier":
					int returnVal = fileChooser.showOpenDialog(topContainer);
					if(returnVal == JFileChooser.APPROVE_OPTION) {
						System.out.println("Fichier choisit : "+fileChooser.getSelectedFile().getAbsolutePath());
						controler.loadSequence("file://"+fileChooser.getSelectedFile().getPath());
					}
			}
			updateUI();
		}
	}
}
