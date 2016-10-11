package viewAndController;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class SpellingAidView extends JFrame{
	
	private JPanel _mainMenuPanel = new JPanel(new GridLayout(5,1));
	private JPanel _quizPanel = new JPanel(new BorderLayout());
	private JPanel _statsPanel = new JPanel(new BorderLayout());
	private JPanel _optionsPanel = new JPanel(new BorderLayout());

	JPanel _mainPanel = new JPanel();
	CardLayout _mainLayout = new CardLayout();
	JButton _startQuizButton = new JButton("Start New Quiz");
	JButton _viewStatsButton = new JButton("View Statistics");
	JButton _optionsButton = new JButton("Options");
	JButton _quitButton = new JButton("Quit");
	
	JPanel _quizSessionPanel = new JPanel(new GridLayout(4,1));
	JPanel _quizEndPanel = new JPanel(new GridLayout(3,1));
	JButton _hearWordButton = new JButton("Hear word");
	JButton _spellWordButton = new JButton("Spell word");
	JButton _quizMenuButton = new JButton("Back to menu");
	JTextField _input = new JTextField();
	JLabel _quizEndMessageLabel = new JLabel("");
	JLabel _sessionCorrectnessLabel = new JLabel("");
	JLabel _sessionAccuracyLabel = new JLabel("");
	JButton _currentLevelButton = new JButton("Same Level");
	JButton _nextLevelButton = new JButton("Next Level");
	JButton _playVideoButton = new JButton("Video Reward");
	
	JButton _optionsMenuButton = new JButton("Back to menu");
	JLabel _wordCountLabel = new JLabel(" ");
	JLabel _levelLabel = new JLabel(" ");
	ArrayList<JLabel> _accuracyRatingQuizLabels = new ArrayList<JLabel>();
	ArrayList<JLabel> _accuracyRatingStatsLabels = new ArrayList<JLabel>();
	JComboBox<String> _quizType = new JComboBox<String>();
	JComboBox<Integer> _quizLevel = new JComboBox<Integer>();
	JComboBox<String> _voiceType = new JComboBox<String>();
	
	JButton _statsMenuButton = new JButton("Back to menu");
	JButton _clearStatsButton = new JButton("Clear statistics");
	JTextArea _statsTextArea = new JTextArea();
	
	public SpellingAidView(){
		//create SpellingAid window
		super("VOXSPELL (prototype)");
		setDefaultCloseOperation(EXIT_ON_CLOSE);	
		//setLocationRelativeTo(null);
		setUndecorated(true);
		setAlwaysOnTop(true);
		setResizable(false);
		Toolkit tk = Toolkit.getDefaultToolkit();
		setVisible(true);
		//setSize(500,350);
		setSize((int)tk.getScreenSize().getWidth(),(int)tk.getScreenSize().getHeight());
		
		_mainPanel.setLayout(_mainLayout);
		setupAccuracyRatings();
		setupMainPanel();
		setupOptionsPanel();
		setupQuizPanel();
		setupMainMenuPanel();
		setupStatsPanel();
		this.add(_mainPanel);
		_mainLayout.show(_mainPanel, "mainMenuPanel");
		setResizable(false);
		//pack();
	}
	
	//sets up the main panel
	private void setupMainPanel() {
		_mainPanel.add(_quizPanel, "quizPanel");
		_mainPanel.add(_statsPanel , "statsPanel");
		_mainPanel.add(_optionsPanel, "optionsPanel");
		_mainPanel.add(_mainMenuPanel , "mainMenuPanel");
		_mainPanel.add(_statsPanel, "statsPanel");
	}
	
	private void setupStatsPanel() {
		_statsTextArea.setEditable(false);
		
		JPanel infoPanel = new JPanel(new GridLayout(1,2));
		JPanel accuracyPanel = new JPanel(new GridLayout(12,2));
		JPanel statsPanel = new JPanel(new BorderLayout());
		JScrollPane statsPane = new JScrollPane(_statsTextArea);
		
		accuracyPanel.add(new JLabel("Level"));
		accuracyPanel.add(new JLabel("Accuracy"));
		for(int i = 0; i < 11; i++){
			accuracyPanel.add(new JLabel(i+1+":"));
			accuracyPanel.add(_accuracyRatingStatsLabels.get(i));
		}
		accuracyPanel.setBorder(BorderFactory.createTitledBorder("Spelling Accuracy"));
		
		statsPanel.add(new JLabel("Word stats"), BorderLayout.NORTH);
		statsPanel.add(statsPane, BorderLayout.CENTER);
		
		infoPanel.add(statsPanel);
		infoPanel.add(accuracyPanel);
		
		_statsPanel.add(infoPanel, BorderLayout.CENTER);
		_statsPanel.add(_clearStatsButton, BorderLayout.SOUTH);
		_statsPanel.add(_statsMenuButton, BorderLayout.NORTH);
	}
	
	private void setupMainMenuPanel() {
		_mainMenuPanel.add(new JLabel("Welcome to VOXSPELL!", SwingConstants.CENTER));
		_mainMenuPanel.add(_startQuizButton);
		_mainMenuPanel.add(_viewStatsButton);
		_mainMenuPanel.add(_optionsButton);
		_mainMenuPanel.add(_quitButton);
	}
	
	private void setupAccuracyRatings(){
		for(int i=0; i < 11; i++){
			_accuracyRatingQuizLabels.add(new JLabel(" "));
			_accuracyRatingStatsLabels.add(new JLabel(" "));
		}
	}

	private void setupQuizPanel(){
		_spellWordButton.setVisible(false);
		
		JPanel quizGridPanel = new JPanel(new GridLayout(1,2));
		JPanel quizInfoPanel = new JPanel(new GridLayout(12,2));
		JPanel quizIOPanel = new JPanel(new GridLayout(2,1));
		JPanel currentSessionInfoPanel = new JPanel(new GridLayout(2,2));
		JPanel speakButtonsPanel = new JPanel(new GridLayout(1,2));
		JPanel quizEndInfoPanel = new JPanel(new GridLayout(2,2));
		JPanel quizEndButtonsPanel = new JPanel(new GridLayout(3,1));
		
		quizInfoPanel.add(new JLabel("Level"));
		quizInfoPanel.add(new JLabel("Accuracy"));
		for(int i = 0; i < 11; i++){
			quizInfoPanel.add(new JLabel(i+1+":"));
			quizInfoPanel.add(_accuracyRatingQuizLabels.get(i));
		}
		quizInfoPanel.setBorder(BorderFactory.createTitledBorder("Spelling Accuracy"));
		
		speakButtonsPanel.add(_hearWordButton);
		speakButtonsPanel.add(_spellWordButton);
		currentSessionInfoPanel.add(new JLabel("Level: "));
		currentSessionInfoPanel.add(_levelLabel);
		currentSessionInfoPanel.add(new JLabel("Test progress: "));
		currentSessionInfoPanel.add(_wordCountLabel);
		_quizSessionPanel.add(currentSessionInfoPanel);
		_quizSessionPanel.add(new JLabel("Enter Spelling:"));
		_quizSessionPanel.add(_input);
		_quizSessionPanel.add(speakButtonsPanel);
		_quizSessionPanel.setBorder(BorderFactory.createTitledBorder("Normal Quiz"));
		
		quizEndInfoPanel.add(new JLabel("Correct words:"));
		quizEndInfoPanel.add(_sessionCorrectnessLabel);
		quizEndInfoPanel.add(new JLabel("Accuracy:"));
		quizEndInfoPanel.add(_sessionAccuracyLabel);
		_quizEndPanel.add(quizEndInfoPanel);
		_quizEndPanel.add(_quizEndMessageLabel);
		quizEndButtonsPanel.add(_currentLevelButton);
		quizEndButtonsPanel.add(_nextLevelButton);
		quizEndButtonsPanel.add(_playVideoButton);
		_quizEndPanel.add(quizEndButtonsPanel);
		_quizEndPanel.setBorder(BorderFactory.createTitledBorder("End of quiz!"));
		_quizEndPanel.setVisible(false);
		_nextLevelButton.setVisible(false);
		_playVideoButton.setVisible(false);
		
		quizIOPanel.add(_quizSessionPanel);
		quizIOPanel.add(_quizEndPanel);
		quizGridPanel.add(quizIOPanel);
		quizGridPanel.add(quizInfoPanel);
		_quizPanel.add(BorderLayout.NORTH,_quizMenuButton);
		_quizPanel.add(BorderLayout.CENTER,quizGridPanel);
	}
	
	private void setupOptionsPanel(){
		_quizType.addItem("Normal");
		_quizType.addItem("Review");
		_quizLevel.addItem(1);
		_quizLevel.addItem(2);
		_quizLevel.addItem(3);
		_quizLevel.addItem(4);
		_quizLevel.addItem(5);
		_quizLevel.addItem(6);
		_quizLevel.addItem(7);
		_quizLevel.addItem(8);
		_quizLevel.addItem(9);
		_quizLevel.addItem(10);
		_quizLevel.addItem(11);
		_voiceType.addItem("American");
		_voiceType.addItem("British");
		_voiceType.addItem("New Zealander");
		
		JPanel optionGridPanel = new JPanel(new GridLayout(6,2));
		optionGridPanel.add(new JLabel("Quiz Type"));
		optionGridPanel.add(_quizType);
		optionGridPanel.add(new JLabel(" "));
		optionGridPanel.add(new JLabel(" "));
		optionGridPanel.add(new JLabel("Quiz Starting Level"));
		optionGridPanel.add(_quizLevel);
		optionGridPanel.add(new JLabel(" "));
		optionGridPanel.add(new JLabel(" "));
		optionGridPanel.add(new JLabel("Voice"));
		optionGridPanel.add(_voiceType);
		optionGridPanel.add(new JLabel(" "));
		optionGridPanel.add(new JLabel(" "));
		
		_optionsPanel.add(BorderLayout.NORTH, _optionsMenuButton);
		_optionsPanel.add(BorderLayout.CENTER, optionGridPanel);
	}
}
