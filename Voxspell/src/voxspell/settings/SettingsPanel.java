package voxspell.settings;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import voxspell.main.VoxspellPanel;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.JComboBox;

public class SettingsPanel extends JPanel {
	public static final String NAME = "SettingsPanel";
	VoxspellPanel vp;
	/**
	 * Create the panel.
	 */
	public SettingsPanel(VoxspellPanel voxspellPanel) {
		vp = voxspellPanel;
		setLayout(null);
		
		JLabel lblOptions = new JLabel("Options");
		lblOptions.setHorizontalAlignment(SwingConstants.CENTER);
		lblOptions.setFont(new Font("Dialog", Font.BOLD, 40));
		lblOptions.setBounds(331, 12, 187, 40);
		add(lblOptions);
		
		JPanel panel = new JPanel();
		panel.setBounds(61, 89, 728, 321);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblMusicVolume = new JLabel("Music Volume");
		lblMusicVolume.setHorizontalAlignment(SwingConstants.CENTER);
		lblMusicVolume.setFont(new Font("Dialog", Font.BOLD, 20));
		lblMusicVolume.setBounds(151, 65, 187, 25);
		panel.add(lblMusicVolume);
		
		JLabel lblSoundVolume = new JLabel("Sound Volume");
		lblSoundVolume.setHorizontalAlignment(SwingConstants.CENTER);
		lblSoundVolume.setFont(new Font("Dialog", Font.BOLD, 20));
		lblSoundVolume.setBounds(151, 102, 187, 25);
		panel.add(lblSoundVolume);
		
		JLabel lblVoiceVolume = new JLabel("Voice Volume");
		lblVoiceVolume.setHorizontalAlignment(SwingConstants.CENTER);
		lblVoiceVolume.setFont(new Font("Dialog", Font.BOLD, 20));
		lblVoiceVolume.setBounds(151, 148, 187, 25);
		panel.add(lblVoiceVolume);
		
		JLabel lblVoiceType = new JLabel("Voice Type");
		lblVoiceType.setHorizontalAlignment(SwingConstants.CENTER);
		lblVoiceType.setFont(new Font("Dialog", Font.BOLD, 20));
		lblVoiceType.setBounds(151, 185, 187, 25);
		panel.add(lblVoiceType);
		
		JButton btnResetProfile = new JButton("Reset Profile");
		btnResetProfile.setBounds(281, 259, 166, 25);
		panel.add(btnResetProfile);
		
		JSlider musicVolumeSlider = new JSlider();
		musicVolumeSlider.setBounds(417, 65, 200, 16);
		panel.add(musicVolumeSlider);
		
		JSlider soundVolumeSlider = new JSlider();
		soundVolumeSlider.setBounds(417, 102, 200, 16);
		panel.add(soundVolumeSlider);
		
		JSlider voiceVolumeSlider = new JSlider();
		voiceVolumeSlider.setBounds(417, 148, 200, 16);
		panel.add(voiceVolumeSlider);
		
		JComboBox voiceTypeComboBox = new JComboBox();
		voiceTypeComboBox.setBounds(417, 185, 208, 25);
		panel.add(voiceTypeComboBox);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(12, 464, 117, 25);
		add(btnBack);

	}
}
