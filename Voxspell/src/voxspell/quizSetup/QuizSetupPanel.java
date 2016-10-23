package voxspell.quizSetup;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import voxspell.main.VoxspellPanel;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.border.LineBorder;

public class QuizSetupPanel extends JPanel {
	public static final String NAME = "QuizSetupPanel";
	VoxspellPanel vp;
	JLabel lblSelectList = new JLabel("Spelling List");
	JLabel lblSelectCategory = new JLabel("Select Category");
	JComboBox<String> comboBoxSpellingList = new JComboBox<String>();
	JComboBox<String> comboBoxStartCategory = new JComboBox<String>();
	JCheckBox chckbxReviewMode = new JCheckBox("Review Mode");
	JButton btnBegin = new JButton("Begin");
	JButton btnBackToMenu = new JButton("Back to Menu");
	JLabel lblSetup = new JLabel("Set-up Quiz");
	/**
	 * Create the panel.
	 */
	public QuizSetupPanel(VoxspellPanel voxspellPanel) {
		setBackground(Color.WHITE);
		vp = voxspellPanel;
		setLayout(null);
		lblSelectList.setBounds(12, 213, 398, 54);

		lblSelectList.setFont(new Font("Courier", Font.PLAIN, 40));
		lblSelectList.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblSelectList);
		lblSelectCategory.setBounds(12, 264, 398, 54);
		
		lblSelectCategory.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSelectCategory.setFont(new Font("Courier", Font.PLAIN, 40));
		add(lblSelectCategory);
		comboBoxSpellingList.setBounds(428, 213, 284, 43);
		
		comboBoxSpellingList.setBackground(Color.WHITE);
		add(comboBoxSpellingList);
		comboBoxStartCategory.setBounds(428, 264, 284, 43);
		
		comboBoxStartCategory.setBackground(Color.WHITE);
		add(comboBoxStartCategory);
		chckbxReviewMode.setBounds(348, 326, 154, 23);
		
		chckbxReviewMode.setBackground(Color.WHITE);
		chckbxReviewMode.setFont(new Font("Courier", Font.BOLD, 16));
		add(chckbxReviewMode);
		btnBegin.setBounds(325, 357, 199, 54);
		
		btnBegin.setBackground(Color.WHITE);
		btnBegin.setFont(new Font("Courier", Font.PLAIN, 40));
		add(btnBegin);
		btnBackToMenu.setBounds(12, 463, 154, 25);
		
		btnBackToMenu.setBackground(Color.WHITE);
		btnBackToMenu.setFont(new Font("Courier", Font.BOLD, 16));
		add(btnBackToMenu);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new LineBorder(Color.BLACK, 4));
		panel.setBounds(102, 48, 646, 116);
		add(panel);
		panel.setLayout(null);
		lblSetup.setBounds(92, 17, 462, 82);
		panel.add(lblSetup);
		
		lblSetup.setHorizontalAlignment(SwingConstants.CENTER);
		lblSetup.setFont(new Font("Courier", Font.BOLD, 70));
	}
}
