package voxspell.main;

import javax.swing.JPanel;

import voxspell.quizSetup.QuizSetupPanel;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class MainMenuPanel extends JPanel {
	VoxspellPanel vp;
	public static final String NAME = "MainMenuPanel";
	private MainMenuHandler _mainMenuHandler = new MainMenuHandler();
	private JButton btnNewQuiz = new JButton("Start Quiz");
	private JButton btnMakeSpellingList = new JButton("Spelling Lists");
	private JButton btnAchievements = new JButton("Achievements");
	private JButton btnOptions = new JButton("Options");
	private JButton btnExit = new JButton("Exit");
	/**
	 * Create the panel.
	 * @param voxspellPanel 
	 */
	public MainMenuPanel(VoxspellPanel voxspellPanel) {
		setBackground(Color.WHITE);
		vp = voxspellPanel;
		setLayout(null);

		btnNewQuiz.addActionListener(_mainMenuHandler);
		btnNewQuiz.setBackground(Color.WHITE);
		btnNewQuiz.setBounds(585, 185, 253, 51);
		//btnNewQuiz.setOpaque(false);
		//btnNewQuiz.setContentAreaFilled(false);
		//btnNewQuiz.setBorderPainted(false);
		add(btnNewQuiz);

		btnMakeSpellingList.setBackground(Color.WHITE);
		btnMakeSpellingList.setBounds(585, 248, 253, 51);
		//btnMakeSpellingList.setOpaque(false);
		//btnMakeSpellingList.setContentAreaFilled(false);
		//btnMakeSpellingList.setBorderPainted(false);
		add(btnMakeSpellingList);

		btnAchievements.setBackground(Color.WHITE);
		btnAchievements.setBounds(585, 311, 253, 51);
		//btnAchievements.setOpaque(false);
		//btnAchievements.setContentAreaFilled(false);
		//btnAchievements.setBorderPainted(false);
		add(btnAchievements);

		btnOptions.setBackground(Color.WHITE);
		btnOptions.setBounds(585, 374, 253, 51);
		//btnOptions.setOpaque(false);
		//btnOptions.setContentAreaFilled(false);
		//btnOptions.setBorderPainted(false);
		add(btnOptions);
		
		btnExit.addActionListener(_mainMenuHandler);
		btnExit.setBackground(Color.WHITE);
		btnExit.setBounds(585, 437, 253, 51);
		//btnExit.setOpaque(false);
		//btnExit.setContentAreaFilled(false);
		//btnExit.setBorderPainted(false);
		add(btnExit);
	}

	private class MainMenuHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==btnNewQuiz){
				vp.show(QuizSetupPanel.NAME);
			}
			else if (e.getSource()==btnMakeSpellingList){
				//TODO
			}
			else if(e.getSource()==btnAchievements){
				//TODO
			}
			else if(e.getSource()==btnOptions){
				//TODO
			}
			else if(e.getSource()==btnExit){
				System.exit(0);
			}
		}
	}
}
