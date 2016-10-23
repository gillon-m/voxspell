package voxspell.media.audio;

import java.awt.EventQueue;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.BooleanControl;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import voxspell.main.Settings;
import sun.audio.*;

/**
 * Responsible for playing the background music
 * @author Gillon Manalastas
 *
 */
public class Music {
	private final String _song = "VoxspellMain.wav";
	private final int _songLength=38370;
	private Thread _thread;

	public Music(){
		_thread = new Thread(){
			public void run(){
				while(true){
					InputStream in;
					try {
						in = new FileInputStream(new File(Settings.musicLocation+_song));
						AudioStream audioStream = new AudioStream(in);
						AudioPlayer.player.start(audioStream);
						Thread.sleep(_songLength);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		_thread.start();
	}
}
