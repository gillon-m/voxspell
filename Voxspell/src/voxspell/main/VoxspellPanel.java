package voxspell.main;

import javax.swing.JPanel;

import voxspell.quiz.QuizController;
import voxspell.quiz.*;
import voxspell.quizSetup.*;
import voxspell.statistics.*;

import java.awt.CardLayout;
import java.util.ArrayList;

public class VoxspellPanel extends JPanel{
	private CardLayout _cl = new CardLayout();
	private ArrayList<Controller> _controllers = new ArrayList<Controller>();
	private MainMenuPanel _mainMenuPanel = new MainMenuPanel(this);
	private QuizSetupPanel _quizSetupPanel = new QuizSetupPanel(this);
	private QuizPanel _quizPanel = new QuizPanel(this);
	private StatisticsPanel _statisticsPanel = new StatisticsPanel(this);

	private Controller _quizSetupController;
	private Controller _quizController;
	private Controller _statisticsController;

	/**
	 * Create the panel.
	 */
	public VoxspellPanel() {
		setLayout(_cl);
		add(_mainMenuPanel, MainMenuPanel.NAME);
		add(_quizSetupPanel, QuizSetupPanel.NAME);
		add(_quizPanel, QuizPanel.NAME);
		add(_statisticsPanel, StatisticsPanel.NAME);

		_quizSetupController = new QuizSetupController(_quizSetupPanel);
		_controllers.add(_quizSetupController);
		_quizController = new QuizController(_quizPanel, new Quiz());
		_controllers.add(_quizController);
		_statisticsController = new StatisticsController(_statisticsPanel);
		_controllers.add(_statisticsController);

		_cl.show(this, MainMenuPanel.NAME);
	}

	/**
	 * Displays the specified panel name on the screen 
	 * @param panelName
	 */
	public void show(String panelName){
		//refresh all panels
		for(Controller c : _controllers){
			c.refresh();
		}
		_cl.show(this,  panelName);
	}
}
