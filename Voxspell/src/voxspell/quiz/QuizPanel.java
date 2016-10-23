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

public class QuizPanel extends JPanel {
	public static final String NAME = "QuizPanel";
	VoxspellPanel vp;
	JTextField inputTextField = new JTextField();;
	JLabel lblSpellingListQuiz = new JLabel("(quiz_name) Quiz");
	JButton btnEndQuiz = new JButton("End Quiz");
	JButton btnHearWord = new JButton("Hear Word");
	JButton btnSpellWord = new JButton("Spell Word");
	JPanel progressPanel = new JPanel();
	JLabel lblcategory = new JLabel("(category)");
	JLabel lblWordSpelling = new JLabel("");
	JPanel inputPanel = new JPanel();
	JButton btnStart = new JButton("START");
	
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
		
		btnEndQuiz.setBackground(Color.WHITE);
		btnEndQuiz.setBounds(12, 464, 117, 25);
		add(btnEndQuiz);
		
		inputPanel.setBackground(Color.WHITE);
		inputPanel.setBounds(204, 161, 441, 291);
		add(inputPanel);
		inputPanel.setLayout(null);
		

		inputTextField.setBounds(12, 12, 417, 102);
		inputPanel.add(inputTextField);
		inputTextField.setColumns(10);
		
		btnHearWord.setBackground(Color.WHITE);
		btnHearWord.setBounds(115, 126, 210, 41);
		inputPanel.add(btnHearWord);
		
		btnSpellWord.setBackground(Color.WHITE);
		btnSpellWord.setBounds(115, 179, 210, 41);
		inputPanel.add(btnSpellWord);
		lblWordSpelling.setFont(new Font("Dialog", Font.BOLD, 20));
		lblWordSpelling.setHorizontalAlignment(SwingConstants.CENTER);
		lblWordSpelling.setBounds(12, 230, 417, 49);
		
		inputPanel.add(lblWordSpelling);
		

		progressPanel.setBackground(Color.WHITE);
		progressPanel.setBounds(204, 95, 441, 52);
		add(progressPanel);
		
		lblcategory.setHorizontalAlignment(SwingConstants.CENTER);
		lblcategory.setFont(new Font("Courier", Font.BOLD, 20));
		lblcategory.setBounds(17, 70, 816, 15);
		add(lblcategory);
		
		btnStart.setBackground(Color.WHITE);
		btnStart.setForeground(Color.BLACK);
		btnStart.setFont(new Font("Dialog", Font.BOLD, 40));
		btnStart.setBounds(293, 211, 261, 77);
		add(btnStart);
		vp = voxspellPanel;
	}
}
