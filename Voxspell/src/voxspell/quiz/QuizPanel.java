package voxspell.quiz;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

import voxspell.main.VoxspellPanel;

import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JTable;
import java.awt.GridLayout;

public class QuizPanel extends JPanel {
	public static final String NAME = "QuizPanel";
	VoxspellPanel vp;
	JTextField inputTextField = new JTextField();;
	JLabel lblSpellingListQuiz = new JLabel("(quiz_name) Quiz");
	JButton btnEndQuiz = new JButton("End Quiz");
	JButton btnHearWord = new JButton("Hear Word");
	JButton btnSpellWord = new JButton("Spell Word");
	JLabel lblcategory = new JLabel("(category)");
	JLabel lblWordSpelling = new JLabel("");
	JPanel inputPanel = new JPanel();
	JButton btnStart = new JButton("START");
	JLabel lblProgress = new JLabel("");
	JPanel endPanel = new JPanel();
	JLabel lblEndOfQuiz = new JLabel("End of Quiz!");
	JLabel lblYouGotnumber = new JLabel("You got (number) out of (number) currect");
	JLabel lblYourlistAccuracy = new JLabel("Your (list) accuracy is now:");
	JLabel lblYourcategoryAccuracy = new JLabel("Your (category) accuracy is now:");
	JLabel lblListaccuracy = new JLabel("listAccuracy");
	JLabel lblCategoryaccuracy = new JLabel("categoryAccuracy");
	JLabel lblCongratulations = new JLabel("Congratulations! You earned these two rewards:");
	JButton btnAudioReward = new JButton("Audio Reward");
	JButton btnVideoReward = new JButton("Video Reward");
	
	/**
	 * Create the panel.
	 * @param voxspellPanel 
	 */
	public QuizPanel(VoxspellPanel voxspellPanel) {
		setBackground(Color.WHITE);
		setLayout(null);
		
		lblSpellingListQuiz.setHorizontalAlignment(SwingConstants.CENTER);
		lblSpellingListQuiz.setFont(new Font("Courier", Font.BOLD, 40));
		lblSpellingListQuiz.setBounds(12, 12, 826, 46);
		add(lblSpellingListQuiz);
		btnEndQuiz.setFont(new Font("Courier", Font.BOLD, 12));
		
		btnEndQuiz.setBackground(Color.WHITE);
		btnEndQuiz.setBounds(12, 464, 117, 25);
		add(btnEndQuiz);
		endPanel.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		endPanel.setBackground(Color.WHITE);
		endPanel.setBounds(204, 97, 442, 355);
		
		add(endPanel);
		endPanel.setLayout(null);
		lblEndOfQuiz.setHorizontalAlignment(SwingConstants.CENTER);
		lblEndOfQuiz.setFont(new Font("Courier", Font.BOLD, 20));
		lblEndOfQuiz.setBounds(12, 12, 418, 21);
		
		endPanel.add(lblEndOfQuiz);
		lblYouGotnumber.setHorizontalAlignment(SwingConstants.CENTER);
		lblYouGotnumber.setFont(new Font("Courier", Font.BOLD, 12));
		lblYouGotnumber.setBounds(12, 45, 417, 29);
		
		endPanel.add(lblYouGotnumber);
		lblYourlistAccuracy.setHorizontalAlignment(SwingConstants.CENTER);
		lblYourlistAccuracy.setFont(new Font("Courier", Font.BOLD, 20));
		lblYourlistAccuracy.setBounds(12, 86, 418, 29);
		
		endPanel.add(lblYourlistAccuracy);
		lblYourcategoryAccuracy.setHorizontalAlignment(SwingConstants.CENTER);
		lblYourcategoryAccuracy.setFont(new Font("Courier", Font.BOLD, 20));
		lblYourcategoryAccuracy.setBounds(12, 168, 418, 29);
		
		endPanel.add(lblYourcategoryAccuracy);
		lblListaccuracy.setFont(new Font("Courier", Font.BOLD, 12));
		lblListaccuracy.setHorizontalAlignment(SwingConstants.CENTER);
		lblListaccuracy.setBounds(12, 127, 418, 29);
		
		endPanel.add(lblListaccuracy);
		lblCategoryaccuracy.setFont(new Font("Courier", Font.BOLD, 12));
		lblCategoryaccuracy.setHorizontalAlignment(SwingConstants.CENTER);
		lblCategoryaccuracy.setBounds(12, 209, 418, 29);
		
		endPanel.add(lblCategoryaccuracy);
		lblCongratulations.setFont(new Font("Courier", Font.BOLD, 12));
		lblCongratulations.setHorizontalAlignment(SwingConstants.CENTER);
		lblCongratulations.setBounds(12, 250, 418, 29);
		
		endPanel.add(lblCongratulations);
		

		btnAudioReward.setBackground(Color.WHITE);
		btnAudioReward.setFont(new Font("Courier", Font.BOLD, 12));
		btnAudioReward.setBounds(63, 291, 151, 25);
		endPanel.add(btnAudioReward);
		
		btnVideoReward.setBackground(Color.WHITE);
		btnVideoReward.setFont(new Font("Courier", Font.BOLD, 12));
		btnVideoReward.setBounds(226, 291, 158, 25);
		endPanel.add(btnVideoReward);
		inputPanel.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		
		inputPanel.setBackground(Color.WHITE);
		inputPanel.setBounds(204, 97, 441, 355);
		add(inputPanel);
		inputPanel.setLayout(null);
		inputTextField.setHorizontalAlignment(SwingConstants.CENTER);
		inputTextField.setFont(new Font("Courier", Font.PLAIN, 20));
		

		inputTextField.setBounds(12, 74, 417, 102);
		inputPanel.add(inputTextField);
		inputTextField.setColumns(10);
		btnHearWord.setFont(new Font("Courier", Font.BOLD, 20));
		
		btnHearWord.setBackground(Color.WHITE);
		btnHearWord.setBounds(115, 188, 210, 41);
		inputPanel.add(btnHearWord);
		btnSpellWord.setFont(new Font("Courier", Font.BOLD, 20));
		
		btnSpellWord.setBackground(Color.WHITE);
		btnSpellWord.setBounds(115, 241, 210, 41);
		inputPanel.add(btnSpellWord);
		lblWordSpelling.setFont(new Font("Courier", Font.BOLD, 20));
		lblWordSpelling.setHorizontalAlignment(SwingConstants.CENTER);
		lblWordSpelling.setBounds(12, 294, 417, 49);
		
		inputPanel.add(lblWordSpelling);
		lblProgress.setHorizontalAlignment(SwingConstants.CENTER);
		lblProgress.setFont(new Font("Courier", Font.BOLD, 20));
		lblProgress.setBackground(Color.WHITE);
		lblProgress.setBounds(12, 12, 417, 50);
		
		inputPanel.add(lblProgress);
		
		lblcategory.setHorizontalAlignment(SwingConstants.CENTER);
		lblcategory.setFont(new Font("Courier", Font.BOLD, 20));
		lblcategory.setBounds(17, 70, 816, 15);
		add(lblcategory);
		
		btnStart.setBackground(Color.WHITE);
		btnStart.setForeground(Color.BLACK);
		btnStart.setFont(new Font("Courier", Font.BOLD, 40));
		btnStart.setBounds(293, 211, 261, 77);
		add(btnStart);
		vp = voxspellPanel;
	}
}
