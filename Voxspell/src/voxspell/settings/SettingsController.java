package voxspell.settings;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import voxspell.main.Controller;
import voxspell.main.VoxspellPanel;

public class SettingsController implements Controller{
	private SettingsPanel _settingsPanel;
	
	public SettingsController(SettingsPanel settingsPanel){
		_settingsPanel = settingsPanel;
	}
	
	private class SettingsHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
	}
	
	@Override
	public void refresh() {	
	}
	
}
