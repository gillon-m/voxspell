package voxspell.media.audio;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import voxspell.main.Settings;

public class SoundEffect {
	private final String _effectLocation = Settings.soundEffectsLocation;
	private final String CLICK = "Click.wav";

	public void playClick(){
		new Thread(){
			public void run(){
				InputStream in;
				try {
					in = new FileInputStream(new File(_effectLocation+CLICK));
					AudioStream audioStream = new AudioStream(in);
					AudioPlayer.player.start(audioStream);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}
}
