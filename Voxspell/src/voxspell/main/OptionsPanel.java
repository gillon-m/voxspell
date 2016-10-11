package voxspell.main;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.JComboBox;

public class OptionsPanel extends JPanel {
	public static final String NAME = "OptionsPanel";
	VoxspellPanel _vp;
	/**
	 * Create the panel.
	 */
	public OptionsPanel(VoxspellPanel voxspellPanel) {
		_vp = voxspellPanel;
		setLayout(null);
		
		JLabel lblOptions = new JLabel("Options");
		lblOptions.setHorizontalAlignment(SwingConstants.CENTER);
		lblOptions.setFont(new Font("Dialog", Font.BOLD, 40));
		lblOptions.setBounds(331, 12, 187, 40);
		add(lblOptions);
		
		JPanel panel = new JPanel();
		panel.setBounds(61, 90, 728, 321);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblMusicVolume = new JLabel("Music Volume");
		lblMusicVolume.setHorizontalAlignment(SwingConstants.CENTER);
		lblMusicVolume.setFont(new Font("Dialog", Font.BOLD, 20));
		lblMusicVolume.setBounds(151, 36, 187, 44);
		panel.add(lblMusicVolume);
		
		JLabel lblSoundVolume = new JLabel("Sound Volume");
		lblSoundVolume.setHorizontalAlignment(SwingConstants.CENTER);
		lblSoundVolume.setFont(new Font("Dialog", Font.BOLD, 20));
		lblSoundVolume.setBounds(161, 92, 162, 44);
		panel.add(lblSoundVolume);
		
		JLabel lblVoiceVolume = new JLabel("Voice Volume");
		lblVoiceVolume.setHorizontalAlignment(SwingConstants.CENTER);
		lblVoiceVolume.setFont(new Font("Dialog", Font.BOLD, 20));
		lblVoiceVolume.setBounds(151, 148, 187, 25);
		panel.add(lblVoiceVolume);
		
		JLabel lblVoiceType = new JLabel("Voice Type");
		lblVoiceType.setHorizontalAlignment(SwingConstants.CENTER);
		lblVoiceType.setFont(new Font("Dialog", Font.BOLD, 20));
		lblVoiceType.setBounds(151, 170, 187, 44);
		panel.add(lblVoiceType);
		
		JButton btnClearStats = new JButton("Clear Stats");
		btnClearStats.setBounds(221, 226, 117, 25);
		panel.add(btnClearStats);
		
		JSlider musicVolumeSlider = new JSlider();
		musicVolumeSlider.setBounds(417, 81, 200, 16);
		panel.add(musicVolumeSlider);
		
		JSlider soundVolumeSlider = new JSlider();
		soundVolumeSlider.setBounds(417, 121, 200, 16);
		panel.add(soundVolumeSlider);
		
		JSlider voiceVolumeSlider = new JSlider();
		voiceVolumeSlider.setBounds(417, 177, 200, 16);
		panel.add(voiceVolumeSlider);
		
		JComboBox voiceTypeComboBox = new JComboBox();
		voiceTypeComboBox.setBounds(429, 245, 208, 44);
		panel.add(voiceTypeComboBox);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(12, 464, 117, 25);
		add(btnBack);

	}
}
