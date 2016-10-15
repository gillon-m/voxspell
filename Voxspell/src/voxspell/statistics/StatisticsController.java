package voxspell.statistics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import voxspell.fileManagement.SpellingList;
import voxspell.fileManagement.Statistics;

public class StatisticsController {
	StatisticsPanel _statsPanel;
	Statistics _stats = new Statistics();
	SpellingList _spellingList = new SpellingList();

	public StatisticsController(StatisticsPanel statisticsPanel) {
		_statsPanel = statisticsPanel;
	}
	
	/**
	 * Refreshes the statistics panel
	 */
	private void refresh(){
		_statsPanel.comboBoxSpellingList.removeAllItems();
		for(String list : _spellingList.getLists()){
			//System.out.println(list);
			_statsPanel.comboBoxSpellingList.addItem(list);
		}
		
	}
	private class StatisticsHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==_statsPanel.btnRefresh){
				refresh();
			}
			// TODO Auto-generated method stub
			
		}
		
	}
}
