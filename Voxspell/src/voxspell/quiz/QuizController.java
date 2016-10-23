package voxspell.quiz;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import voxspell.fileManagement.SpellingList;
import voxspell.main.Controller;
import voxspell.main.MainMenuPanel;
import voxspell.main.Settings;
import voxspell.media.audio.Music;
import voxspell.media.audio.SoundEffect;

public class QuizController implements Controller{
	private QuizPanel _quizPanel;
	private Quiz _quizModel;
	private QuizHandler _quizHandler = new QuizHandler();
	private SoundEffect _soundEffect = new SoundEffect();
	
	public QuizController(QuizPanel quizPanel, Quiz quizModel){
		_quizPanel = quizPanel;
		_quizModel = quizModel;

		//add actionListeners
		_quizPanel.inputTextField.addActionListener(_quizHandler);
		_quizPanel.btnHearWord.addActionListener(_quizHandler);
		_quizPanel.btnEndQuiz.addActionListener(_quizHandler);
		_quizPanel.btnHearWord.addActionListener(_quizHandler);
		_quizPanel.btnSpellWord.addActionListener(_quizHandler);
		_quizPanel.btnStart.addActionListener(_quizHandler);
	}

	private class QuizHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==_quizPanel.inputTextField){
				if(!_quizModel.isQuizEnded()){
					_quizModel.quizAttempt(_quizPanel.inputTextField.getText());
					if(_quizModel.isNextWord()){
						_quizPanel.lblWordSpelling.setText("");
						_quizModel.setUpdateble(true);
					}
				}
				_quizPanel.inputTextField.setText("");
			}
			else if(e.getSource()==_quizPanel.btnEndQuiz){
				_quizModel.isQuizEnded(true);
				_quizPanel.vp.show(MainMenuPanel.NAME);
			}
			else if(e.getSource()==_quizPanel.btnHearWord){
				if(!_quizModel.isQuizEnded()){
					_quizModel.sayWord();
				}
			}
			else if(e.getSource()==_quizPanel.btnStart){
				_quizPanel.inputPanel.setVisible(true);
				_quizPanel.btnStart.setVisible(false);
				_quizModel.startQuiz();
				
			}
			else if(e.getSource()==_quizPanel.btnSpellWord){
				if(!_quizModel.isQuizEnded()){
					_quizModel.setUpdateble(false);
					_quizModel.spellWord();
					_quizPanel.lblWordSpelling.setText(_quizModel.getWord());
				}
			}
		}
	}

	@Override
	public void refresh() {
		if(Settings.isReviewMode){
			_quizPanel.btnSpellWord.setVisible(true);
			_quizPanel.lblWordSpelling.setVisible(true);
			_quizPanel.lblSpellingListQuiz.setText(Settings.currentSpellingList+" Review Quiz");
		}else{
			_quizPanel.lblWordSpelling.setVisible(false);
			_quizPanel.btnSpellWord.setVisible(false);
			_quizPanel.lblSpellingListQuiz.setText(Settings.currentSpellingList+" Quiz");
		}
		if(Settings.currentCategory.equals(SpellingList.NO_CATEGORIES)){
			_quizPanel.lblcategory.setText("");
		}else{
			_quizPanel.lblcategory.setText(Settings.currentCategory);
		}
		_quizPanel.inputPanel.setVisible(false);
		_quizPanel.btnStart.setVisible(true);
	}
}
