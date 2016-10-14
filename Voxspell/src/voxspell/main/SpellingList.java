package voxspell.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Random;

public class SpellingList {
	public static final String ALL_WORDS = "All Words";
	public static final String NO_CATEGORIES = "NO CATEGORIES";
	private ArrayList<String> _spellingLists;
	private ArrayList<String> _categories;
	private String _listsLocation = "SpellingLists/";
	private ArrayList<String> _wordList;
	private String _allSpellingListsPath=null;

	public SpellingList(){
		//Set path of spelling lists
		try {
			_allSpellingListsPath = VoxspellFrame.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}
		_allSpellingListsPath = _allSpellingListsPath.replace("Voxspell_prototype.jar", "");
		_allSpellingListsPath = _allSpellingListsPath+_listsLocation;
		//System.out.println(_allSpellingListsPath);
		//System.out.println(getLists());
		//System.out.println(getCategories("NZCER-spelling-lists"));
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
	 * Logic based of code by bhuang3 in stack overflow.
	 * @return ArrayList of all spelling lists available
	 */
	public ArrayList<String> getLists(){
		_spellingLists = new ArrayList<String>();
		File directory = new File(_allSpellingListsPath);
		for(String file: directory.list()){
			//System.out.println("file: "+file);
			if(new File(_allSpellingListsPath+file).isDirectory()){
				_spellingLists.add(file);
			}
		}
		return _spellingLists;
	}

	/**
	 * Returns the categories in a spelling list
	 * @param spellingList
	 * @return
	 */
	public ArrayList<String> getCategories(String spellingList){
		spellingList = _allSpellingListsPath+spellingList+"/"+spellingList+".txt";
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

	/*//creates the list of possible words that can be used for review quiz
	private void createReviewWordList(){
		try {
			_wordList = new ArrayList<String>();
			BufferedReader br = new BufferedReader(new FileReader(_currentSpellingList));
			String word;
			while((word = br.readLine())!= null){
				word = word.trim();
				if(word.length()>0){
					_wordList.add(word);
				}
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/

	//creates the list of possible words that can be used for spelling quiz
	public void createWordList() {
		try {
			_wordList = new ArrayList<String>();
			BufferedReader br = new BufferedReader(new FileReader(_allSpellingListsPath+Settings.currentSpellingList+"/"+Settings.currentSpellingList+".txt"));
			String line;
			//find position of categories in txt file
			while((line = br.readLine())!=null){
				if(line.charAt(0)!='%'){
					_wordList.add(line);
					break;
				}
				if(line.equals("%"+Settings.currentCategory)){
					break;
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