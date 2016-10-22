package voxspell.fileManagement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import voxspell.main.Settings;

public class SpellingList {
	public static final String DEFAULT_LIST = "NZCER-spelling-lists";
	public static final String NO_CATEGORIES = "NO CATEGORIES";
	public static final String ALL_CATEGORIES = "ALL CATEGORIES";
	private ArrayList<String> _spellingLists;
	private ArrayList<String> _categories;
	private ArrayList<String> _wordList;

	public SpellingList(){
	}

	/**
	 * Sets the spelling list and category for the quiz
	 * @param spellingList
	 * @param category
	 */
	public void setQuizSettings(String spellingList, String category){
		Settings.currentSpellingList = spellingList;
		Settings.currentCategory = category;
	}
	/**
	 * Returns a list of all spelling lists.
	 * Logic based of code by bhuang3 from StackOverflow.
	 * @return ArrayList of all spelling lists available
	 */
	public ArrayList<String> getLists(){
		_spellingLists = new ArrayList<String>();
		File directory = new File(Settings.spellingListLocation);
		for(String file: directory.list()){
			//check if not directory
			if(!(new File(Settings.spellingListLocation+file).isDirectory())){
				//check if not a hidden file
				if(file.charAt(0)!='.'){
					//check if not temp file
					if(file.charAt(file.length()-1)!='~'){
						int extensionIndex = file.indexOf('.');//remove file extension
						_spellingLists.add(file.substring(0, extensionIndex)); //add to spelling lists list
					}
				}
			}
		}
		return _spellingLists;
	}

	/**
	 * Returns the categories in a spelling list from a specified spelling list
	 * @param spellingList
	 * @return
	 */
	public ArrayList<String> getCategories(String spellingList){
		if(spellingList==null){
			return _categories;
		}
		spellingList = Settings.spellingListLocation+spellingList+".txt";
		try {
			_categories = new ArrayList<String>();
			BufferedReader br = new BufferedReader(new FileReader(spellingList));
			String line;
			while((line = br.readLine())!=null){//find where categories are
				if(line.charAt(0)=='%'){
					_categories.add(line.substring(1));
				}
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return _categories;
	}
	/**
	 * Returns the categories in a spelling list from a specified spelling path
	 * @param spellingListPath
	 * @return
	 */
	public ArrayList<String> getCategoriesFromPath(String spellingListPath){
		if(spellingListPath==null){
			return _categories;
		}
		try {
			_categories = new ArrayList<String>();
			BufferedReader br = new BufferedReader(new FileReader(spellingListPath));
			String line;
			while((line = br.readLine())!=null){//find where categories are
				if(line.charAt(0)=='%'){
					_categories.add(line.substring(1));
				}
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return _categories;
	}
	/**
	 * Returns the size of the current word list
	 * @return
	 */
	public int size(){
		return _wordList.size();
	}

	//sets the word being tested
	public String getWord() {
		Random r = new Random();
		int pos = r.nextInt(_wordList.size());
		String word = _wordList.get(pos);
		_wordList.remove(pos);
		return word;
	}

	//creates the list of possible words that can be used for spelling quiz
	public void createWordList() {
		try {
			_wordList = new ArrayList<String>();
			BufferedReader br = new BufferedReader(new FileReader(Settings.spellingListLocation+Settings.currentSpellingList+".txt"));
			String line = br.readLine();
			//find position of category
			if(line.charAt(0)!='%'){
				_wordList.add(line);
			}else{
				while(line!=null){
					if(line.equals("%"+Settings.currentCategory)){
						break;
					}
					line=br.readLine();
				}
			}
			//get words in category
			while((line = br.readLine())!= null){
				if(line.charAt(0)=='%'){
					break;
				}
				else{
					line = line.trim();
					if(line.length()>0){
						_wordList.add(line);
					}
				}
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}