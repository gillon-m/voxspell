package voxspell.main;

import javax.swing.JPanel;

import voxspell.fileManagement.SpellingList;
import voxspell.media.audio.SoundEffect;
import voxspell.media.audio.voice.Festival;
import voxspell.quizSetup.QuizSetupPanel;
import voxspell.settings.SettingsPanel;
import voxspell.statistics.StatisticsPanel;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.UIManager;

public class MainMenuPanel extends JPanel{
	VoxspellPanel vp;
	public static final String NAME = "MainMenuPanel";
	private Festival _festival = Festival.getInstance(Festival.AMERICAN);
	private MainMenuHandler _mainMenuHandler = new MainMenuHandler();
	private MouseHover _mouseHover = new MouseHover();
	private JButton btnNewQuiz = new JButton("Start Quiz");
	private JButton btnAddSpellingList = new JButton("Add Spelling Lists");
	private JButton btnStatistics = new JButton("Statistics");
	private JButton btnExit = new JButton("Exit");
	private final JLabel label = new JLabel("");
	private SoundEffect _soundEffect = new SoundEffect();
	private JComboBox comboBoxVoice = new JComboBox();

	/**
	 * Create the panel.
	 * @param voxspellPanel 
	 */
	public MainMenuPanel(VoxspellPanel voxspellPanel) {
		setBackground(Color.WHITE);
		vp = voxspellPanel;

		setLayout(null);
		btnNewQuiz.setForeground(Color.BLACK);
		btnNewQuiz.setToolTipText("");
		btnNewQuiz.setFont(new Font("Courier", Font.BOLD, 20));
		btnNewQuiz.addActionListener(_mainMenuHandler);
		btnNewQuiz.setBackground(Color.WHITE);
		btnNewQuiz.setBounds(298, 110, 253, 51);
		add(btnNewQuiz);

		btnAddSpellingList.setFont(new Font("Courier", Font.BOLD, 20));
		btnAddSpellingList.addActionListener(_mainMenuHandler);
		btnAddSpellingList.setBackground(Color.WHITE);
		btnAddSpellingList.setBounds(298, 173, 253, 51);
		add(btnAddSpellingList);

		btnStatistics.addActionListener(_mainMenuHandler);
		btnStatistics.setFont(new Font("Courier", Font.BOLD, 20));
		btnStatistics.setBackground(Color.WHITE);
		btnStatistics.setBounds(298, 236, 253, 51);
		add(btnStatistics);

		btnExit.setFont(new Font("Courier", Font.BOLD, 20));
		btnExit.addActionListener(_mainMenuHandler);
		btnExit.setBackground(Color.WHITE);
		btnExit.setBounds(298, 437, 253, 51);
		add(btnExit);

		JLabel lblVoxspell = new JLabel("VOXSPELL");
		lblVoxspell.setFont(new Font("Courier", Font.BOLD, 80));
		lblVoxspell.setHorizontalAlignment(SwingConstants.CENTER);
		lblVoxspell.setBounds(155, 12, 559, 86);
		add(lblVoxspell);
		label.setIcon(new ImageIcon(Settings.mainBackgroundLocation));
		label.setBounds(-69, -16, 989, 532);

		btnNewQuiz.addMouseListener(_mouseHover);
		btnAddSpellingList.addMouseListener(_mouseHover);
		btnStatistics.addMouseListener(_mouseHover);
		btnExit.addMouseListener(_mouseHover);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(UIManager.getColor("Button.darkShadow")));
		panel.setBackground(Color.WHITE);
		panel.setBounds(298, 299, 253, 51);
		add(panel);
		panel.setLayout(null);

		JLabel lblSelectVoice = new JLabel("Select Voice");
		lblSelectVoice.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectVoice.setFont(new Font("Courier", Font.BOLD, 12));
		lblSelectVoice.setBounds(0, 0, 253, 15);
		panel.add(lblSelectVoice);
		comboBoxVoice.setBackground(Color.WHITE);

		comboBoxVoice.setFont(new Font("Courier", Font.BOLD, 12));
		comboBoxVoice.setBounds(0, 12, 253, 39);
		comboBoxVoice.addItem("American");
		comboBoxVoice.addItem("British");
		comboBoxVoice.addItem("New Zealand");
		comboBoxVoice.addActionListener(_mainMenuHandler);
		panel.add(comboBoxVoice);

		add(label);
	}

	private void listAdd(){
		JFileChooser fc = new JFileChooser();
		fc.setCurrentDirectory(new File(Settings.voxspellDirectory));
		fc.setDialogTitle("Add Spelling List");
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
		fc.setFileFilter(filter);
		int value = fc.showOpenDialog(this);
		if(value == JFileChooser.APPROVE_OPTION){
			File f = fc.getSelectedFile();
			String fileName = f.getName();
			int extensionIndex = fileName.indexOf('.');
			if(!fileName.substring(extensionIndex).equals(".txt")){//get file extension
				JOptionPane.showMessageDialog(this, "file not a txt file. Choose another file", "Incorrect file format", JOptionPane.ERROR_MESSAGE);
			}else{//add file to spelling list directory
				File destination = new File(Settings.spellingListLocation+fileName);
				File directory = new File(Settings.spellingListLocation);
				boolean fileExists=false;
				for(String directoryFileName: directory.list()){
					if(directoryFileName.equals(fileName)){
						fileExists=true;
						JOptionPane.showMessageDialog(this, "File already exists!");
						break;
					}
				}		
				if(!fileExists){
					try {
						Files.copy(f.toPath(), destination.toPath());
						JOptionPane.showMessageDialog(this, "Word list added to spelling lists", "Added list", JOptionPane.INFORMATION_MESSAGE);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			}
		}
	}

	private class MainMenuHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==btnNewQuiz){
				_soundEffect.playClick();
				vp.show(QuizSetupPanel.NAME);
			}
			else if (e.getSource()==btnAddSpellingList){
				_soundEffect.playClick();
				listAdd();
			}
			else if(e.getSource()==btnStatistics){
				_soundEffect.playClick();
				vp.show(StatisticsPanel.NAME);
			}
			else if(e.getSource()==btnExit){
				_soundEffect.playClick();
				System.exit(0);
			}
			else if(e.getSource()==comboBoxVoice){
				JComboBox<String> cb = (JComboBox<String>)e.getSource();
				String option = (String)cb.getSelectedItem();
				String voice;
				if(option.equals("American")){
					voice=Festival.AMERICAN;
				}
				else if(option.equals("British")){
					voice=Festival.BRITISH;
				}
				else if(option.equals("New Zealand")){
					voice=Festival.NEWZEALAND;
				}
				else{
					voice=Festival.AMERICAN;
				}
				_festival.changeVoice(voice);
			}
		}
	}

	private class MouseHover extends MouseAdapter{
		@Override
		public void mouseEntered(MouseEvent e) {
			((JComponent) e.getSource()).setBackground(Color.BLACK);
			((JComponent) e.getSource()).setForeground(Color.WHITE);
		}
		@Override
		public void mouseExited(MouseEvent e) {
			((JComponent) e.getSource()).setForeground(Color.BLACK);
			((JComponent) e.getSource()).setBackground(Color.WHITE);
		}
	}
}
