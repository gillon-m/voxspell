package voxspell.main;

import spellingAid.Festival;

public class Settings {
	private static Settings _settings = new Settings();
	public static String currentSpellingList;
	public static String currentCategory;
	public static int musicVolume;
	public static int soundVolume;
	public static int voiceVolume;
	public static String voiceType;
	public static boolean isReviewMode;
	
	private Settings(){
		currentSpellingList = "NZCER-spelling-lists";
		currentCategory = "Level 1";
		musicVolume=0;
		soundVolume=0;
		voiceVolume=0;
		voiceType=Festival.AMERICAN;
		isReviewMode=false;
	}
	
	public static Settings getInstance(){
		return _settings;
	}
}
