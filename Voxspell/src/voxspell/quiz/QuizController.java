package voxspell.quiz;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import voxspell.main.MainMenuPanel;

public class QuizController {
	private QuizPanel _quizPanel;
	private Quiz _quizModel;
	private QuizHandler _quizHandler = new QuizHandler();

	public QuizController(QuizPanel quizPanel, Quiz quizModel){
		_quizPanel = quizPanel;
		_quizModel = quizModel;
		
		//add actionListeners
		_quizPanel.inputTextField.addActionListener(_quizHandler);
		_quizPanel.btnHearWord.addActionListener(_quizHandler);
		_quizPanel.btnEndQuiz.addActionListener(_quizHandler);
		_quizPanel.btnRestartQuiz.addActionListener(_quizHandler);
		_quizPanel.btnHearWord.addActionListener(_quizHandler);
	}

	private class QuizHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource()==_quizPanel.inputTextField){
				if(!_quizModel.isQuizEnded()){
					_quizModel.quizAttempt(_quizPanel.inputTextField.getText());
				}
				_quizPanel.inputTextField.setText("");
			}
			else if(e.getSource()==_quizPanel.btnRestartQuiz){
				_quizModel.startQuiz();
			}
			else if(e.getSource()==_quizPanel.btnEndQuiz){
				_quizPanel.vp.show(MainMenuPanel.NAME);
			}
			else if(e.getSource()==_quizPanel.btnHearWord){
				_quizModel.sayWord();
			}
			
		}

	}
}
