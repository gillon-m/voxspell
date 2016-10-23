package voxspell.main;

import javax.swing.JPanel;

import voxspell.quizSetup.QuizSetupPanel;
import voxspell.settings.SettingsPanel;
import voxspell.statistics.StatisticsPanel;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainMenuPanel extends JPanel{
	VoxspellPanel vp;
	public static final String NAME = "MainMenuPanel";
	private MainMenuHandler _mainMenuHandler = new MainMenuHandler();
	private MouseHover _mouseHover = new MouseHover();
	private JButton btnNewQuiz = new JButton("Start Quiz");
	private JButton btnMakeSpellingList = new JButton("Spelling Lists");
	private JButton btnStatistics = new JButton("Statistics");
	private JButton btnOptions = new JButton("Options");
	private JButton btnExit = new JButton("Exit");
	private final JLabel label = new JLabel("");
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
		//btnNewQuiz.setOpaque(false);
		//btnNewQuiz.setContentAreaFilled(false);
		//btnNewQuiz.setBorderPainted(false);
		add(btnNewQuiz);
		
		btnMakeSpellingList.setFont(new Font("Courier", Font.BOLD, 20));
		btnMakeSpellingList.addActionListener(_mainMenuHandler);
		btnMakeSpellingList.setBackground(Color.WHITE);
		btnMakeSpellingList.setBounds(298, 173, 253, 51);
		//btnMakeSpellingList.setOpaque(false);
		//btnMakeSpellingList.setContentAreaFilled(false);
		//btnMakeSpellingList.setBorderPainted(false);
		add(btnMakeSpellingList);
		
		btnStatistics.addActionListener(_mainMenuHandler);
		btnStatistics.setFont(new Font("Courier", Font.BOLD, 20));
		btnStatistics.setBackground(Color.WHITE);
		btnStatistics.setBounds(298, 236, 253, 51);
		//btnAchievements.setOpaque(false);
		//btnAchievements.setContentAreaFilled(false);
		//btnAchievements.setBorderPainted(false);
		add(btnStatistics);
		
		btnOptions.setFont(new Font("Courier", Font.BOLD, 20));
		btnOptions.addActionListener(_mainMenuHandler);
		btnOptions.setBackground(Color.WHITE);
		btnOptions.setBounds(298, 299, 253, 51);
		//btnOptions.setOpaque(false);
		//btnOptions.setContentAreaFilled(false);
		//btnOptions.setBorderPainted(false);
		add(btnOptions);
		
		btnExit.setFont(new Font("Courier", Font.BOLD, 20));
		btnExit.addActionListener(_mainMenuHandler);
		btnExit.setBackground(Color.WHITE);
		btnExit.setBounds(298, 437, 253, 51);
		//btnExit.setOpaque(false);
		//btnExit.setContentAreaFilled(false);
		//btnExit.setBorderPainted(false);
		add(btnExit);
		
		JLabel lblVoxspell = new JLabel("VOXSPELL");
		lblVoxspell.setFont(new Font("Courier", Font.BOLD, 80));
		lblVoxspell.setHorizontalAlignment(SwingConstants.CENTER);
		lblVoxspell.setBounds(155, 12, 559, 86);
		add(lblVoxspell);
		label.setIcon(new ImageIcon("/home/gillon/workspace/Voxspell_git/Voxspell/bin/.media/images/rock-1573068_960_720.jpg"));
		label.setBounds(-69, -16, 989, 532);
		
		btnNewQuiz.addMouseListener(_mouseHover);
		btnMakeSpellingList.addMouseListener(_mouseHover);
		btnStatistics.addMouseListener(_mouseHover);
		btnOptions.addMouseListener(_mouseHover);
		btnExit.addMouseListener(_mouseHover);
		
		add(label);
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
			else if(e.getSource()==btnStatistics){
				vp.show(StatisticsPanel.NAME);
			}
			else if(e.getSource()==btnOptions){
				vp.show(SettingsPanel.NAME);
			}
			else if(e.getSource()==btnExit){
				System.exit(0);
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
