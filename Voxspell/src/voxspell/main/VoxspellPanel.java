package voxspell.main;

import javax.swing.JPanel;

import voxspell.quiz.QuizController;
import voxspell.quiz.*;
import voxspell.quizSetup.*;
import voxspell.statistics.*;

import java.awt.CardLayout;

public class VoxspellPanel extends JPanel {
	private CardLayout _cl = new CardLayout();
	private MainMenuPanel _mainMenuPanel = new MainMenuPanel(this);
	private QuizSetupPanel _quizSetupPanel = new QuizSetupPanel(this);
	private QuizPanel _quizPanel = new QuizPanel(this);
	private StatisticsPanel _statisticsPanel = new StatisticsPanel(this);
	
	/**
	 * Create the panel.
	 */
	public VoxspellPanel() {
		setLayout(_cl);
		add(_mainMenuPanel, MainMenuPanel.NAME);
		new QuizSetupController(_quizSetupPanel);
		add(_quizSetupPanel, QuizSetupPanel.NAME);
		new QuizController(_quizPanel, new Quiz());
		add(_quizPanel, QuizPanel.NAME);
		new StatisticsController(_statisticsPanel);
		_cl.show(this, MainMenuPanel.NAME);
	}
	
	/**
	 * Displays the specified panel name on the screen 
	 * @param panelName
	 */
	public void show(String panelName){
		_cl.show(this,  panelName);
	}
}
