package voxspell.statistics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import voxspell.fileManagement.SpellingList;
import voxspell.fileManagement.Statistics;
import voxspell.main.Controller;
import voxspell.main.MainMenuPanel;

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
		for(String category: _spellingList.getCategories(list)){
			_statsPanel.comboBoxCategory.addItem(category);
		}
		if(_spellingList.getCategories(list).size()==0){
			_statsPanel.comboBoxCategory.addItem(SpellingList.NO_CATEGORIES);
		}
	}

	/**
	 * Refreshes the statistics panel
	 */
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
			}
			else if(e.getSource()==_statsPanel.comboBoxCategory){
				JComboBox<String> cb = (JComboBox<String>)e.getSource();
				String option = (String)cb.getSelectedItem();
				_selectedCategory = option;
			}
			else if(e.getSource()==_statsPanel.btnBack){
				_statsPanel.vp.show(MainMenuPanel.NAME);
			}
			// TODO Auto-generated method stub
		}
	}

	@Override
	public void refresh() {
		refreshSpellingLists();
		refreshCategories(_statsPanel.comboBoxSpellingList.getItemAt(0));
	}
}
