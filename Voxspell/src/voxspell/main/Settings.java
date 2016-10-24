package voxspell.main;

import java.net.URISyntaxException;

import voxspell.media.audio.voice.Festival;

/**
 * Contains the settings and file paths for Voxspell
 * @author Gillon Manalastas
 *
 */
public class Settings {
	private static Settings _settings = new Settings();
	public static String currentSpellingList;
	public static String currentCategory;
	public static int musicVolume;
	public static int soundVolume;
	public static int voiceVolume;
	public static String voiceType;
	public static boolean isReviewMode;
	public static String spellingListLocation;
	public static String voxspellDirectory;
	public static String musicLocation;
	public static String mainBackgroundLocation; 
	public static String soundEffectsLocation;
	public static String videoLocation;
	
	private Settings(){
		currentSpellingList = "NZCER-spelling-lists";
		currentCategory = "Level 1";
		musicVolume=0;
		soundVolume=0;
		voiceVolume=0;
		voiceType=Festival.AMERICAN;
		isReviewMode=false;
		
		//set list location
		try {
			String voxspellDirectory = VoxspellFrame.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
			voxspellDirectory = voxspellDirectory.replace("Voxspell_prototype.jar", "");
			this.voxspellDirectory = voxspellDirectory;
			musicLocation=voxspellDirectory+".media/audio/music/";
			soundEffectsLocation=voxspellDirectory+".media/audio/effects/";
			spellingListLocation = voxspellDirectory+".SpellingLists/";
			mainBackgroundLocation = voxspellDirectory+".media/images/MainBackground.jpg";
			videoLocation=voxspellDirectory+".media/videos/";
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}
		
	}
	
	public static Settings getInstance(){
		return _settings;
	}
}
