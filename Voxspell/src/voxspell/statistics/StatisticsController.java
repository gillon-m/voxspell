package voxspell.statistics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JLabel;

import voxspell.fileManagement.SpellingList;
import voxspell.fileManagement.Statistics;
import voxspell.main.Controller;
import voxspell.main.MainMenuPanel;
import voxspell.main.Settings;

public class StatisticsController implements Controller {
	private StatisticsPanel _statsPanel;
	private Statistics _stats = new Statistics();
	private SpellingList _spellingList = new SpellingList();
	private ActionListener _statsHandler = new StatisticsHandler();
	private String _selectedSpellingList;
	private String _selectedCategory;

	public StatisticsController(StatisticsPanel statisticsPanel) {
		_statsPanel = statisticsPanel;
		//add action listeners
		_statsPanel.btnBack.addActionListener(_statsHandler);
		_statsPanel.comboBoxSpellingList.addActionListener(_statsHandler);
		_statsPanel.comboBoxCategory.addActionListener(_statsHandler);
		_statsPanel.btnRefresh.addActionListener(_statsHandler);
	}

	/**
	 * Updates gui elements to match available spelling lists
	 */
	private void refreshSpellingLists(){
		_statsPanel.comboBoxSpellingList.removeAllItems();
		for(String list : _spellingList.getLists()){
			_statsPanel.comboBoxSpellingList.addItem(list);
		}
	}
	/**
	 * Updates gui elements to match available categories
	 * @param list
	 */
	private void refreshCategories(String list){
		_statsPanel.comboBoxCategory.removeAllItems();
		_statsPanel.comboBoxCategory.addItem(SpellingList.ALL_CATEGORIES);
		for(String category: _spellingList.getCategories(list)){
			_statsPanel.comboBoxCategory.addItem(category);
		}
	}
	/**
	 * updates the GUI elements for player statistics
	 */
	private void refreshStatistics(){
		_stats.calculateStatistics(_selectedSpellingList, _selectedCategory);
		//refresh best and worst spelled
		ArrayList<String> bestSpelled = _stats.getBestWords(3);
		ArrayList<String> worstSpelled = _stats.getWorstWords(3);
		for(int i = 0; i < 3; i++){
			if(i<bestSpelled.size()){
				_statsPanel.bestSpelledWords.get(i).setText(bestSpelled.get(i));
			}else{
				_statsPanel.bestSpelledWords.get(i).setText("");
			}
		}
		for(int i = 0; i < 3; i++){
			if(i<worstSpelled.size()){
				_statsPanel.worstSpelledWords.get(i).setText(worstSpelled.get(i));
			}else{
				_statsPanel.worstSpelledWords.get(i).setText("");
			}
		}
		//refresh overall category accuracy
		_statsPanel.lblAccuracyvalue.setText(_stats.getOverallCategoryAccuracy()+"%");
		//refresh streak
		_statsPanel.lblStreakValue.setText(_stats.getStreak(_selectedSpellingList, _selectedCategory)+"");
	}

	/**
	 * Rereshes the graph
	 */
	public void refreshGraph(){
		ArrayList<Integer> accuracyHistory = _stats.getAccuracyHistory(_selectedSpellingList, _selectedCategory);
		_statsPanel.graph.updateScore(accuracyHistory);
		_statsPanel.graph.repaint();
		_statsPanel.graph.revalidate();
	}
	private class StatisticsHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==_statsPanel.btnRefresh){
				refresh();
			}
			else if(e.getSource()==_statsPanel.comboBoxSpellingList){
				JComboBox<String> cb = (JComboBox<String>)e.getSource();
				String option = (String)cb.getSelectedItem();
				_selectedSpellingList = option;
				refreshCategories(option);
				refreshStatistics();
				refreshGraph();
			}
			else if(e.getSource()==_statsPanel.comboBoxCategory){
				JComboBox<String> cb = (JComboBox<String>)e.getSource();
				String option = (String)cb.getSelectedItem();
				_selectedCategory = option;
				refreshStatistics();
				refreshGraph();
			}
			else if(e.getSource()==_statsPanel.btnBack){
				_statsPanel.vp.show(MainMenuPanel.NAME);
			}
		}
	}

	@Override
	public void refresh() {
		refreshSpellingLists();
		refreshCategories(_statsPanel.comboBoxSpellingList.getItemAt(0));
	}


}
