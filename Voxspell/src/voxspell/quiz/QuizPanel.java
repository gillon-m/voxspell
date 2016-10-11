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
	JLabel lblquiznameQuiz = new JLabel("(quiz_name) Quiz");
	JButton btnEndQuiz = new JButton("End Quiz");
	JButton btnRestartQuiz = new JButton("Restart Quiz");
	JButton btnSpellWord = new JButton("Options");
	JButton btnHearWord = new JButton("Hear Word");
	JButton btnNewButton = new JButton("Spell Word");
	JPanel progressPanel = new JPanel();
	JPanel endPanel = new JPanel();
	
	/**
	 * Create the panel.
	 * @param voxspellPanel 
	 */
	public QuizPanel(VoxspellPanel voxspellPanel) {
		setBackground(Color.WHITE);
		setLayout(null);
		
		lblquiznameQuiz.setHorizontalAlignment(SwingConstants.CENTER);
		lblquiznameQuiz.setFont(new Font("Courier", Font.BOLD, 70));
		lblquiznameQuiz.setBounds(12, 12, 826, 71);
		add(lblquiznameQuiz);
		
		btnEndQuiz.setBackground(Color.WHITE);
		btnEndQuiz.setBounds(12, 464, 117, 25);
		add(btnEndQuiz);
		
		btnRestartQuiz.setBackground(Color.WHITE);
		btnRestartQuiz.setBounds(338, 464, 173, 25);
		add(btnRestartQuiz);
		
		btnSpellWord.setBackground(Color.WHITE);
		btnSpellWord.setBounds(721, 464, 117, 25);
		add(btnSpellWord);
		
		JPanel inputPanel = new JPanel();
		inputPanel.setBackground(Color.WHITE);
		inputPanel.setBounds(204, 161, 441, 179);
		add(inputPanel);
		inputPanel.setLayout(null);
		

		inputTextField.setBounds(12, 12, 417, 102);
		inputPanel.add(inputTextField);
		inputTextField.setColumns(10);
		
		btnHearWord.setBackground(Color.WHITE);
		btnHearWord.setBounds(12, 126, 210, 41);
		inputPanel.add(btnHearWord);
		
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setBounds(234, 126, 195, 41);
		inputPanel.add(btnNewButton);
		

		progressPanel.setBackground(Color.WHITE);
		progressPanel.setBounds(204, 95, 441, 52);
		add(progressPanel);
		
		endPanel.setBackground(Color.WHITE);
		endPanel.setBounds(204, 352, 441, 100);
		add(endPanel);
		vp = voxspellPanel;
	}
}
