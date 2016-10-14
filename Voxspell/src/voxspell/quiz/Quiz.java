package voxspell.quiz;

import spellingAid.Festival;
import spellingAid.FileManager;
import voxspell.main.Settings;
import voxspell.main.SpellingList;

public class Quiz {
	private Festival _voice = new Festival(Festival.AMERICAN);
	private FileManager _fm = new FileManager();
	private int _quizLevel = 1;
//	private String _quizType = "Normal";
	private SpellingList _spellingList = new SpellingList();;

	private final int MAX_ATTEMPTS = 2;
	private int _quizLength = 10;
	private int _nWords;
	private String _attempt;
	private String _word;
	private int _nWordsCount=0;
	private int _nAttempts=0;
	private int _nCorrect=0;
	private boolean _isQuizEnded=true;
	private int _nTotalAttempts = 0;
	private boolean _isFaulted = false;
	
	/**
	 * Starts a new quiz
	 */
	public void startQuiz(){
		//create word list
		_spellingList.createWordList();
		//determine quiz length
		if(_spellingList.size() < _quizLength){
			_nWords = _spellingList.size();
		}
		else{
			_nWords = _quizLength;
		}
		//set quiz variables
		_nWordsCount = 0;
		_isQuizEnded = false;
		_nCorrect=0;
		_nTotalAttempts=0;
		if(_nWords > 0){
			continueQuiz();
		}
	}

	/**
	 * Determines if the attempt was correct or incorrect
	 * @param attempt
	 */
	public void quizAttempt(String attempt) {
		_attempt = attempt;
		_nAttempts++;
		_nTotalAttempts++;
		//if attempt is correct
		if(_attempt.equalsIgnoreCase(_word)){
			_nCorrect++;
			/*if(_quizType.equals("Normal")){
				_fm.updateAccuracyRatings(_quizLevel, true);
			}*/
			_voice.speakIt("Correct");
			if(_nAttempts == 1){
				_fm.handleQuizzedWords(_word, ".mastered");
			}
			else{
				_fm.handleQuizzedWords(_word, ".faulted");
			}
			if(_nWordsCount>=_nWords){
				_isQuizEnded = true;
			}
			else{
				continueQuiz();
			}
		}
		//if attempt is incorrect
		else{
			/*if(_quizType.equals("Normal")){
				_fm.updateAccuracyRatings(_quizLevel, false);
			}*/
			if(_nAttempts < MAX_ATTEMPTS){
				_isFaulted=true;
				_voice.speakIt("Incorrect, try once more");
				_voice.speakWord(_word);
			}
			else{
				_fm.handleQuizzedWords(_word, ".failed");
				_voice.speakIt("Incorrect");
				if(_nWordsCount>=_nWords){
					_isQuizEnded = true;
				}
				else{
					continueQuiz();
				}
			}
		}
	}
	/**
	 * Continues the current quiz
	 */
	private void continueQuiz(){
		_nWordsCount++;
		_isFaulted = false;
		_word = _spellingList.getWord(); //get new word
		_nAttempts = 0;
		_voice.speakIt("Please spell the word: ");
		_voice.speakWord(_word);
	}
	/**
	 * Returns whether or not the quiz has ended
	 * @return
	 */
	public boolean isQuizEnded() {
		return _isQuizEnded;
	}
	/**
	 * Returns if the word spelling is accessible to the user
	 */
	public boolean isSpellEnabled(){
		return false;
		//return _isFaulted&&_quizType.equalsIgnoreCase("review");
	}
	/**
	 * Spells out the letter's in a word
	 */
	public void _spellWord() {
		_voice.speakLetter(_word);
	}
}
