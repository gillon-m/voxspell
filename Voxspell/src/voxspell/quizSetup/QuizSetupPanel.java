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

public class QuizSetupPanel extends JPanel {
	public static final String NAME = "QuizSetupPanel";
	VoxspellPanel vp;
	JLabel lblSelectList = new JLabel("Spelling List");
	JLabel lblSelectCategory = new JLabel("Select Level");
	JComboBox comboBoxSpellingList = new JComboBox();
	JComboBox comboBoxStartCategory = new JComboBox();
	JCheckBox chckbxReviewMode = new JCheckBox("Review Mode");
	JButton btnBegin = new JButton("Begin");
	JButton btnBackToMenu = new JButton("Back to Menu");
	JLabel lblSetup = new JLabel("Set-up Quiz");
	JButton btnRefresh = new JButton("Refresh");
	/**
	 * Create the panel.
	 */
	public QuizSetupPanel(VoxspellPanel voxspellPanel) {
		setBackground(Color.WHITE);
		vp = voxspellPanel;
		
		setLayout(null);

		lblSelectList.setFont(new Font("Courier", Font.PLAIN, 40));
		lblSelectList.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSelectList.setBounds(144, 213, 312, 54);
		add(lblSelectList);
		
		lblSelectCategory.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSelectCategory.setFont(new Font("Courier", Font.PLAIN, 40));
		lblSelectCategory.setBounds(144, 264, 312, 54);
		add(lblSelectCategory);
		
		comboBoxSpellingList.setBackground(Color.WHITE);
		comboBoxSpellingList.setBounds(474, 213, 238, 43);
		add(comboBoxSpellingList);
		
		comboBoxStartCategory.setBackground(Color.WHITE);
		comboBoxStartCategory.setBounds(474, 264, 238, 43);
		add(comboBoxStartCategory);
		
		chckbxReviewMode.setBackground(Color.WHITE);
		chckbxReviewMode.setFont(new Font("Courier", Font.BOLD, 16));
		chckbxReviewMode.setBounds(144, 326, 154, 23);
		add(chckbxReviewMode);
		
		btnBegin.setBackground(Color.WHITE);
		btnBegin.setFont(new Font("Courier", Font.PLAIN, 40));
		btnBegin.setBounds(325, 357, 199, 54);
		add(btnBegin);
		
		btnBackToMenu.setBackground(Color.WHITE);
		btnBackToMenu.setFont(new Font("Courier", Font.BOLD, 16));
		btnBackToMenu.setBounds(12, 463, 154, 25);
		add(btnBackToMenu);
		
		lblSetup.setHorizontalAlignment(SwingConstants.CENTER);
		lblSetup.setFont(new Font("Courier", Font.BOLD, 70));
		lblSetup.setBounds(82, 81, 685, 71);
		add(lblSetup);
		
		btnRefresh.setBackground(Color.WHITE);
		btnRefresh.setBounds(366, 423, 117, 25);
		add(btnRefresh);
	}
}
