package spellingAid;

public class Festival{
	/**
	 * Credit goes to Alex Yu for Festival logic. Code is slightly altered but mostly unchanged.
	 */
	private String _voice;
	private boolean _apostropheExist = false;
	private boolean _rehear = false;
	public static final String AMERICAN = "kal_diphone";
	public static final String BRITISH = "rab_diphone";
	public static final String NEWZEALAND = "akl_nz_jdt_diphone";

	public Festival(String voice) {
		changeVoice(voice);
	}
	/**
	 * Checks if apostrophe exists, and alerts the user that 
	 * this word contains an apostrophe if it does
	 * @param word
	 */
	public void speakWord(String word) {
		_apostropheExist = doesApostropheExist(word);
		if(_apostropheExist){
			word =  word + ". With an apostrophe.";
		} else {
			word = word;
		}
		speakIt(word);
	}
	/**
	 * speakWord method with rehear parameter
	 * @param word
	 * @param rehear
	 */
	public void speakWord(String word, boolean rehear){
		_rehear = rehear;
		speakWord(word);
	}
	/**
	 * Festival speaks out individual letter
	 * @param word
	 */
	public void speakLetter(String word) {
		String temp = "";
		for(int i = 0; i < word.length(); i++){
			if(word.charAt(i) == '\''){
				temp += "apostrophe ";
			} else {
				temp += word.charAt(i) + " ";
			}
		}
		_rehear = true;
		word = temp;
		speakIt(word);
	}
	/**
	 * Passes the word onto festival worker without any modification
	 * @param word
	 */
	public void speakIt(String word) {
		FestivalWork fw = new FestivalWork();
		fw.setWordAndVoice(word, _voice,_rehear);
		fw.execute();
		_rehear = false;
	}
	
	public boolean doesApostropheExist(String word){
		if(word.contains("'")){
			return true;
		}
		return false;
	}
	/**
	 * Change the wanted voice into what festival understands
	 * @param voice
	 */
	public void changeVoice(String voice){
		_voice=voice;
	}
	
	public String getVoice(){
		return _voice;
	}

}
