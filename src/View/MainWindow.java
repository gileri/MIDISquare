package View;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainWindow extends JFrame {
	
	private static String[] sources = {"File", "Random", "Server"};

	public MainWindow() {
		this.setTitle("Midi Player");
		this.setSize(1800, 600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.createInterface();
		this.setVisible(true);
	}
	
	private void createInterface(){
		Container mainPane = this.getContentPane();
		JPanel topPanel = new JPanel(new FlowLayout());
		
		mainPane.setLayout(new BorderLayout());
		mainPane.add(topPanel, BorderLayout.PAGE_START);
		
		JComboBox<String> combo = new JComboBox<String>(sources);
		combo.setSelectedIndex(1);
		
		JButton playBtn = new JButton("Play");
		JButton pauseBtn = new JButton("Pause");
		JButton stopBtn = new JButton("Stop");

		topPanel.add(combo);
		topPanel.add(playBtn);
		topPanel.add(pauseBtn);
		topPanel.add(stopBtn);
		
		combo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switch (combo.getSelectedIndex()) {
				case 0:
					break;
				case 1:
					break;
				case 2:
					break;
				default:
					break;
				}
			}
		});
		
		playBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		pauseBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		stopBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
	}
	
	public static void main(String[] args){
		MainWindow w = new MainWindow();
	}
}
