package voxspell.media.audio;

import java.awt.EventQueue;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import voxspell.main.Settings;
import sun.audio.*;

/**
 * Responsible for playing the music. Thanks to ProgramingKnowledge Youtube channel
 * @author Gillon Manalastas
 *
 */
public class Music {
	private String _song = "VoxspellMain.wav";
	private final int _songLength=38370;

	public Music(){
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
	public static void main(String[] args){
		new Music();
		System.out.println("Hi");
	}
}
