package voxspell.quizSetup;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComboBox;
import javax.swing.JComponent;

import voxspell.fileManagement.SpellingList;
import voxspell.main.Controller;
import voxspell.main.MainMenuPanel;
import voxspell.main.Settings;
import voxspell.quiz.QuizPanel;

public class QuizSetupController implements Controller{
	private QuizSetupPanel _quizSetupPanel;
	private SpellingList _spellingList = new SpellingList();
	private QuizSetupHandler _quizSetupHandler = new QuizSetupHandler();
	private MouseHover _mouseHover = new MouseHover();
	private String _selectedSpellingList;
	private String _selectedCategory;

	public QuizSetupController(QuizSetupPanel quizSetupPanel){
		_quizSetupPanel = quizSetupPanel;
		//add ActionListeners
		_quizSetupPanel.btnBackToMenu.addActionListener(_quizSetupHandler);
		_quizSetupPanel.btnBegin.addActionListener(_quizSetupHandler);
		_quizSetupPanel.chckbxReviewMode.addActionListener(_quizSetupHandler);
		_quizSetupPanel.comboBoxSpellingList.addActionListener(_quizSetupHandler);
		_quizSetupPanel.comboBoxStartCategory.addActionListener(_quizSetupHandler);
		//add mouse listeners
		_quizSetupPanel.btnBackToMenu.addMouseListener(_mouseHover);
		_quizSetupPanel.btnBegin.addMouseListener(_mouseHover);
		//update spelling lists options
		refreshSpellingLists();
	}
	/**
	 * Updates gui elements to match available spelling lists and categories
	 */
	private void refreshSpellingLists(){
		_quizSetupPanel.comboBoxSpellingList.removeAllItems();
		for(String list : _spellingList.getLists()){
			_quizSetupPanel.comboBoxSpellingList.addItem(list);
		}
		_selectedSpellingList = _quizSetupPanel.comboBoxSpellingList.getItemAt(0);
	}
	private void refreshCategories(String list){
		_quizSetupPanel.comboBoxStartCategory.removeAllItems();
		for(String category: _spellingList.getCategories(list)){
			_quizSetupPanel.comboBoxStartCategory.addItem(category);
		}
		if(_spellingList.getCategories(list).size()==0){
			_quizSetupPanel.comboBoxStartCategory.addItem(SpellingList.NO_CATEGORIES);
		}
		_selectedCategory = _quizSetupPanel.comboBoxStartCategory.getItemAt(0);
	}

	private class QuizSetupHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==_quizSetupPanel.btnBackToMenu){
				_quizSetupPanel.vp.show(MainMenuPanel.NAME);
			}
			else if(e.getSource() == _quizSetupPanel.btnBegin){
				Settings.currentSpellingList=_selectedSpellingList;
				Settings.currentCategory=_selectedCategory;
				_quizSetupPanel.vp.show(QuizPanel.NAME);
			}
			else if(e.getSource()==_quizSetupPanel.chckbxReviewMode){
				if(_quizSetupPanel.chckbxReviewMode.isSelected()){
					Settings.isReviewMode=true;
					//refreshCategories(SpellingList.DEFAULT_LIST);
				}else{
					Settings.isReviewMode=false;
					//refreshCategories(Settings.currentSpellingList);
				}
			}
			else if(e.getSource()==_quizSetupPanel.comboBoxSpellingList){
				JComboBox<String> cb = (JComboBox<String>)e.getSource();
				String option = (String)cb.getSelectedItem();
				_selectedSpellingList = option;
				if(option!=null){
					if(_quizSetupPanel.chckbxReviewMode.isSelected()){
						refreshCategories(SpellingList.DEFAULT_LIST);
					}
					else{
						refreshCategories(option);
					}
				}
			}
			else if(e.getSource()==_quizSetupPanel.comboBoxStartCategory){
				JComboBox<String> cb = (JComboBox<String>)e.getSource();
				String option = (String)cb.getSelectedItem();
				_selectedCategory = option;
			}
		}
	}

	private class MouseHover extends MouseAdapter{
		@Override
		public void mouseEntered(MouseEvent e) {
			((JComponent) e.getSource()).setBackground(Color.BLACK);
			((JComponent) e.getSource()).setForeground(Color.WHITE);
		}
		@Override
		public void mouseExited(MouseEvent e) {
			((JComponent) e.getSource()).setForeground(Color.BLACK);
			((JComponent) e.getSource()).setBackground(Color.WHITE);
		}
	}
	@Override
	public void refresh() {
		refreshSpellingLists();
		refreshCategories((String)_quizSetupPanel.comboBoxSpellingList.getItemAt(0));
	}
}
