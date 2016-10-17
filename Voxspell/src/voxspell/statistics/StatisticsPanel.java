package voxspell.statistics;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import voxspell.main.VoxspellPanel;

public class StatisticsPanel extends JPanel {
	public static final String NAME = "StatisticsPanel";
	VoxspellPanel vp;
	JComboBox<String> comboBoxSpellingList = new JComboBox<String>();
	JComboBox<String> comboBoxCategory = new JComboBox<String>();
	JLabel lblBest1 = new JLabel("");
	JLabel lblBest2 = new JLabel("");
	JLabel lblBest3 = new JLabel("");
	JLabel lblWorst1 = new JLabel("");
	JLabel lblworst2 = new JLabel("");
	JLabel lblWorst3 = new JLabel("");
	JLabel lblStreakValue = new JLabel("");
	JLabel lblAccuracyvalue = new JLabel("");
	JButton btnBack = new JButton("Back");
	JButton btnRefresh = new JButton("Refresh");
	
	/**
	 * Create the panel.
	 */
	public StatisticsPanel(VoxspellPanel voxspellPanel) {
		setBorder(null);
		vp = voxspellPanel;
		setBackground(Color.WHITE);
		setLayout(null);
		
		JPanel listSelectionPanel = new JPanel();
		listSelectionPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		listSelectionPanel.setBackground(Color.WHITE);
		listSelectionPanel.setBounds(12, 12, 394, 439);
		add(listSelectionPanel);
		listSelectionPanel.setLayout(null);
		
		JLabel lblSpellingList = new JLabel("Spelling List:");
		lblSpellingList.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSpellingList.setFont(new Font("Courier", Font.BOLD, 20));
		lblSpellingList.setBounds(12, 12, 189, 24);
		listSelectionPanel.add(lblSpellingList);
		
		comboBoxSpellingList.setBackground(Color.WHITE);
		comboBoxSpellingList.setBounds(219, 16, 163, 24);
		listSelectionPanel.add(comboBoxSpellingList);
		
		JLabel lblCategory = new JLabel("Category:");
		lblCategory.setBounds(12, 48, 189, 24);
		listSelectionPanel.add(lblCategory);
		lblCategory.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCategory.setFont(new Font("Courier", Font.BOLD, 20));
		
		comboBoxCategory.setBounds(219, 48, 163, 24);
		listSelectionPanel.add(comboBoxCategory);
		comboBoxCategory.setBackground(Color.WHITE);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(12, 84, 370, 343);
		listSelectionPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblBestSpelledWords = new JLabel("Best Spelled Words");
		lblBestSpelledWords.setHorizontalAlignment(SwingConstants.CENTER);
		lblBestSpelledWords.setFont(new Font("Courier", Font.PLAIN, 20));
		lblBestSpelledWords.setBounds(12, 12, 346, 15);
		panel.add(lblBestSpelledWords);
		
		JLabel lblWorstSpelledWords = new JLabel("Worst Spelled Words");
		lblWorstSpelledWords.setHorizontalAlignment(SwingConstants.CENTER);
		lblWorstSpelledWords.setFont(new Font("Courier", Font.PLAIN, 20));
		lblWorstSpelledWords.setBounds(12, 151, 346, 15);
		panel.add(lblWorstSpelledWords);
		
		JLabel lblLongestStreak = new JLabel("Longest Streak");
		lblLongestStreak.setFont(new Font("Courier", Font.BOLD, 12));
		lblLongestStreak.setBounds(12, 289, 161, 15);
		panel.add(lblLongestStreak);
		
		JLabel lblAccuracy = new JLabel("Accuracy");
		lblAccuracy.setFont(new Font("Courier", Font.BOLD, 12));
		lblAccuracy.setBounds(12, 316, 161, 15);
		panel.add(lblAccuracy);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(12, 38, 346, 101);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		lblBest1.setFont(new Font("Courier", Font.PLAIN, 12));
		lblBest1.setHorizontalAlignment(SwingConstants.CENTER);
		lblBest1.setBounds(12, 12, 322, 15);
		panel_1.add(lblBest1);
		
		lblBest2.setHorizontalAlignment(SwingConstants.CENTER);
		lblBest2.setFont(new Font("Courier", Font.BOLD, 12));
		lblBest2.setBounds(12, 39, 322, 15);
		panel_1.add(lblBest2);
		
		lblBest3.setFont(new Font("Courier", Font.BOLD, 12));
		lblBest3.setHorizontalAlignment(SwingConstants.CENTER);
		lblBest3.setBounds(12, 66, 322, 15);
		panel_1.add(lblBest3);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(12, 178, 346, 99);
		panel.add(panel_2);
		panel_2.setLayout(null);
		

		lblWorst1.setFont(new Font("Courier", Font.BOLD, 12));
		lblWorst1.setHorizontalAlignment(SwingConstants.CENTER);
		lblWorst1.setBounds(138, 12, 70, 15);
		panel_2.add(lblWorst1);

		lblworst2.setFont(new Font("Courier", Font.BOLD, 12));
		lblworst2.setHorizontalAlignment(SwingConstants.CENTER);
		lblworst2.setBounds(138, 39, 70, 15);
		panel_2.add(lblworst2);
		
		lblWorst3.setFont(new Font("Courier", Font.BOLD, 12));
		lblWorst3.setHorizontalAlignment(SwingConstants.CENTER);
		lblWorst3.setBounds(138, 66, 70, 15);
		panel_2.add(lblWorst3);
		
		lblStreakValue.setFont(new Font("Courier", Font.PLAIN, 12));
		lblStreakValue.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStreakValue.setBounds(185, 287, 173, 15);
		panel.add(lblStreakValue);
		
		lblAccuracyvalue.setFont(new Font("Courier", Font.PLAIN, 12));
		lblAccuracyvalue.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAccuracyvalue.setBounds(185, 314, 173, 15);
		panel.add(lblAccuracyvalue);
		
		btnBack.setBackground(Color.WHITE);
		btnBack.setFont(new Font("Courier", Font.PLAIN, 20));
		btnBack.setBounds(12, 463, 117, 25);
		add(btnBack);
		btnRefresh.setBackground(Color.WHITE);
		btnRefresh.setFont(new Font("Courier", Font.PLAIN, 20));
		btnRefresh.setBounds(340, 463, 169, 25);
		
		add(btnRefresh);
		
	}
}
