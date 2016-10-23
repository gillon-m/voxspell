package voxspell.statistics;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;

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
	ArrayList<JLabel> bestSpelledWords = new ArrayList<JLabel>();
	ArrayList<JLabel> worstSpelledWords = new ArrayList<JLabel>();
	private JLabel lblBest1 = new JLabel("");
	private JLabel lblBest2 = new JLabel("");
	private JLabel lblBest3 = new JLabel("");
	private JLabel lblWorst1 = new JLabel("");
	private JLabel lblWorst2 = new JLabel("");
	private JLabel lblWorst3 = new JLabel("");
	JLabel lblStreakValue = new JLabel("");
	JLabel lblAccuracyvalue = new JLabel("");
	JButton btnBack = new JButton("Back");
	JButton btnRefresh = new JButton("Refresh");
	Graph graph = new Graph(new ArrayList<Integer>());
	private final JLabel label = new JLabel("100%");
	private final JLabel label_1 = new JLabel("0%");
	private final JLabel lblSpellingAccuracyProgress = new JLabel("Spelling Accuracy Progress");

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
		listSelectionPanel.setBounds(12, 12, 826, 86);
		add(listSelectionPanel);
		listSelectionPanel.setLayout(null);

		JLabel lblSpellingList = new JLabel("Spelling List:");
		lblSpellingList.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSpellingList.setFont(new Font("Courier", Font.BOLD, 20));
		lblSpellingList.setBounds(12, 12, 189, 24);
		listSelectionPanel.add(lblSpellingList);

		comboBoxSpellingList.setBackground(Color.WHITE);
		comboBoxSpellingList.setBounds(219, 16, 595, 24);
		listSelectionPanel.add(comboBoxSpellingList);

		JLabel lblCategory = new JLabel("Category:");
		lblCategory.setBounds(12, 48, 189, 24);
		listSelectionPanel.add(lblCategory);
		lblCategory.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCategory.setFont(new Font("Courier", Font.BOLD, 20));

		comboBoxCategory.setBounds(219, 48, 595, 24);
		listSelectionPanel.add(comboBoxCategory);
		comboBoxCategory.setBackground(Color.WHITE);

		btnBack.setBackground(Color.WHITE);
		btnBack.setFont(new Font("Courier", Font.PLAIN, 20));
		btnBack.setBounds(12, 463, 117, 25);
		add(btnBack);
		btnRefresh.setBackground(Color.WHITE);
		btnRefresh.setFont(new Font("Courier", Font.PLAIN, 20));
		btnRefresh.setBounds(340, 463, 169, 25);

		add(btnRefresh);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(12, 110, 826, 341);
		add(panel_3);
		panel_3.setLayout(null);
		graph.setBackground(Color.WHITE);
		graph.setBounds(460, 35, 354, 290);
		panel_3.add(graph);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 35, 346, 79);
		panel_3.add(panel_1);
		panel_1.setBackground(Color.WHITE);
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

		bestSpelledWords.add(lblBest1);
		bestSpelledWords.add(lblBest2);
		bestSpelledWords.add(lblBest3);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 160, 346, 86);
		panel_3.add(panel_2);
		panel_2.setBackground(Color.WHITE);
		panel_2.setLayout(null);


		lblWorst1.setFont(new Font("Courier", Font.BOLD, 12));
		lblWorst1.setHorizontalAlignment(SwingConstants.CENTER);
		lblWorst1.setBounds(12, 12, 322, 15);
		panel_2.add(lblWorst1);

		lblWorst2.setFont(new Font("Courier", Font.BOLD, 12));
		lblWorst2.setHorizontalAlignment(SwingConstants.CENTER);
		lblWorst2.setBounds(12, 39, 322, 15);
		panel_2.add(lblWorst2);

		lblWorst3.setFont(new Font("Courier", Font.BOLD, 12));
		lblWorst3.setHorizontalAlignment(SwingConstants.CENTER);
		lblWorst3.setBounds(12, 66, 322, 15);
		panel_2.add(lblWorst3);

		worstSpelledWords.add(lblWorst1);
		worstSpelledWords.add(lblWorst2);
		worstSpelledWords.add(lblWorst3);

		JLabel lblBestSpelledWords = new JLabel("Best Spelled Words");
		lblBestSpelledWords.setBounds(10, 8, 346, 24);
		panel_3.add(lblBestSpelledWords);
		lblBestSpelledWords.setHorizontalAlignment(SwingConstants.CENTER);
		lblBestSpelledWords.setFont(new Font("Courier", Font.BOLD, 20));

		JLabel lblLongestStreak = new JLabel("Longest Streak");
		lblLongestStreak.setBounds(10, 285, 161, 15);
		panel_3.add(lblLongestStreak);
		lblLongestStreak.setFont(new Font("Courier", Font.BOLD, 12));
		lblStreakValue.setBounds(183, 285, 173, 15);
		panel_3.add(lblStreakValue);


		lblStreakValue.setFont(new Font("Courier", Font.PLAIN, 12));
		lblStreakValue.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAccuracyvalue.setBounds(183, 310, 173, 15);
		panel_3.add(lblAccuracyvalue);

		lblAccuracyvalue.setFont(new Font("Courier", Font.PLAIN, 12));
		lblAccuracyvalue.setHorizontalAlignment(SwingConstants.RIGHT);

		JLabel lblWorstSpelledWords = new JLabel("Worst Spelled Words");
		lblWorstSpelledWords.setBounds(10, 133, 346, 24);
		panel_3.add(lblWorstSpelledWords);
		lblWorstSpelledWords.setHorizontalAlignment(SwingConstants.CENTER);
		lblWorstSpelledWords.setFont(new Font("Courier", Font.BOLD, 20));

		JLabel lblAccuracy = new JLabel("Accuracy");
		lblAccuracy.setBounds(10, 310, 161, 15);
		panel_3.add(lblAccuracy);
		lblAccuracy.setFont(new Font("Courier", Font.BOLD, 12));
		label.setFont(new Font("Courier", Font.BOLD, 12));
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(388, 35, 70, 15);
		
		panel_3.add(label);
		label_1.setFont(new Font("Courier", Font.BOLD, 12));
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setBounds(398, 310, 60, 15);
		
		panel_3.add(label_1);
		lblSpellingAccuracyProgress.setFont(new Font("Courier", Font.BOLD, 20));
		lblSpellingAccuracyProgress.setHorizontalAlignment(SwingConstants.CENTER);
		lblSpellingAccuracyProgress.setBounds(460, 8, 354, 24);
		
		panel_3.add(lblSpellingAccuracyProgress);

	}
}
