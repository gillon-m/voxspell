package voxspell.main;

import javax.swing.JPanel;

import voxspell.quiz.QuizController;
import voxspell.quiz.QuizModel;
import voxspell.quiz.QuizPanel;
import voxspell.quizSetup.QuizSetupController;
import voxspell.quizSetup.QuizSetupModel;
import voxspell.quizSetup.QuizSetupPanel;

import java.awt.CardLayout;

public class VoxspellPanel extends JPanel {
	private CardLayout _cl = new CardLayout();
	MainMenuPanel _mainMenuPanel = new MainMenuPanel(this);
	QuizSetupPanel _quizSetupPanel = new QuizSetupPanel(this);
	QuizPanel _quizPanel = new QuizPanel(this);
	
	/**
	 * Create the panel.
	 */
	public VoxspellPanel() {
		setLayout(_cl);
		add(_mainMenuPanel, MainMenuPanel.NAME);
		new QuizSetupController(_quizSetupPanel, new QuizSetupModel());
		add(_quizSetupPanel, QuizSetupPanel.NAME);
		new QuizController(_quizPanel, new QuizModel());
		add(_quizPanel, QuizPanel.NAME);
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
