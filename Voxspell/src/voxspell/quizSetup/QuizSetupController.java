package voxspell.quizSetup;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import voxspell.main.MainMenuPanel;
import voxspell.main.Settings;
import voxspell.main.SpellingList;
import voxspell.quiz.QuizPanel;

public class QuizSetupController {
	private QuizSetupPanel _quizSetupPanel;
	private QuizSetupModel _quizSetupModel;
	private SpellingList _spellingList = new SpellingList();
	private QuizSetupHandler _quizSetupHandler = new QuizSetupHandler();

	public QuizSetupController(QuizSetupPanel quizSetupPanel, QuizSetupModel quizSetupModel){

		_quizSetupPanel = quizSetupPanel;
		_quizSetupModel = quizSetupModel;
		//add ActionListeners
		_quizSetupPanel.btnBackToMenu.addActionListener(_quizSetupHandler);
		_quizSetupPanel.btnBegin.addActionListener(_quizSetupHandler);
		_quizSetupPanel.btnRefresh.addActionListener(_quizSetupHandler);
		_quizSetupPanel.chckbxReviewMode.addActionListener(_quizSetupHandler);
		_quizSetupPanel.comboBoxSpellingList.addActionListener(_quizSetupHandler);
		_quizSetupPanel.comboBoxStartCategory.addActionListener(_quizSetupHandler);
		//update spelling lists options
		updateSpellingLists();
	}
	/**
	 * Updates gui elements to match available spelling lists and categories
	 */
	private void updateSpellingLists(){
		_quizSetupPanel.comboBoxSpellingList.removeAllItems();
		for(String list : _spellingList.getLists()){
			_quizSetupPanel.comboBoxSpellingList.addItem(list);
		}
	}
	private void updateCategories(String list){
		_quizSetupPanel.comboBoxStartCategory.removeAllItems();
		for(String category: _spellingList.getCategories(list)){
			_quizSetupPanel.comboBoxStartCategory.addItem(category);
		}
		if(_spellingList.getCategories(list).size()==0){
			_quizSetupPanel.comboBoxStartCategory.addItem("NO CATEGORIES FOUND");
		}
	}

	private class QuizSetupHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==_quizSetupPanel.btnBackToMenu){
				_quizSetupPanel.vp.show(MainMenuPanel.NAME);
			}
			else if(e.getSource() == _quizSetupPanel.btnBegin){
				_quizSetupPanel.vp.show(QuizPanel.NAME);
			}
			else if(e.getSource()==_quizSetupPanel.btnRefresh){
				updateSpellingLists();
			}
			else if(e.getSource()==_quizSetupPanel.chckbxReviewMode){
				Settings.isReviewMode=true;
			}
			else if(e.getSource()==_quizSetupPanel.comboBoxSpellingList){
				JComboBox<String> cb = (JComboBox<String>)e.getSource();
				String option = (String)cb.getSelectedItem();
				Settings.currentSpellingList=option;
				updateCategories(option);
			}
			else if(e.getSource()==_quizSetupPanel.comboBoxStartCategory){
				JComboBox<String> cb = (JComboBox<String>)e.getSource();
				String option = (String)cb.getSelectedItem();
				Settings.currentCategory=option;
			}
		}
	}
}
