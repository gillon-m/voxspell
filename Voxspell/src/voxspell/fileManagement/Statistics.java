package voxspell.fileManagement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

import voxspell.main.Settings;

public class Statistics {
	private String _statsPath = Settings.spellingListLocation+"."+Settings.currentSpellingList+"-stats";
	private String _wordListPath = Settings.spellingListLocation+Settings.currentSpellingList+".txt";
	private static final String ACCURACY_HEADING = "<accuracies>";
	private SpellingList _spellingList = new SpellingList();

	private HashMap<Double, LinkedList<String>> _accuracyMap;
	private double _overallCategoryAccuracy;
	public Statistics(){
		//create necessary files on startup
		doesFileExist();
	}
	/**
	 * Checks if stats file exists. Creates stats file if it doesn't exist
	 */
	public void doesFileExist(){
		File statsFile = new File(_statsPath);
		if(!statsFile.exists()&&Settings.currentSpellingList!=null){
			createStatsFile(_wordListPath,_statsPath);
		}
	}
	public void doesFileExist(String wordList){
		File statsFile = new File(Settings.spellingListLocation+"."+wordList+"-stats");
		if(!statsFile.exists()&&wordList!=null){
			createStatsFile(Settings.spellingListLocation+wordList+".txt",Settings.spellingListLocation+"."+wordList+"-stats");
		}
	}
	/**
	 *  Create word statistics file
	 * @param wordListPath
	 * @param statsPath
	 */
	public void createStatsFile(String wordListPath, String statsPath){
		try {
			PrintWriter statsFile = new PrintWriter(new FileWriter(statsPath, true));
			BufferedReader wordListFile = new BufferedReader(new FileReader(wordListPath));
			statsFile.println("0"); //overall streak
			String wordLine;
			while((wordLine = wordListFile.readLine())!=null){
				statsFile.println(wordLine);
				if(wordLine.charAt(0)=='%'){
					statsFile.println("0");//category streak
				}
				if(wordLine.charAt(0)!='%'){
					statsFile.println("0");//nCorrect
					statsFile.println("0");//nAttempts
				}
			}
			statsFile.println(ACCURACY_HEADING);//begin creating past accuracies
			for(String category : _spellingList.getCategoriesFromPath(wordListPath)){
				statsFile.println("%"+category);
			}
			statsFile.close();
			wordListFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Update the accuracy rating of the specified level depending if whether or not the attempt was correct
	 * @param word
	 * @param isCorrect
	 */
	public void updateWordAccuracy(String word, boolean isCorrect){
		doesFileExist();
		//update statistics
		ArrayList<String> statsTemp = new ArrayList<String>();//store the contents of the stats file
		try {
			//copy contents of file into temporary list and update values
			BufferedReader inputFile = new BufferedReader(new FileReader(_statsPath));
			String line;
			while((line = inputFile.readLine())!=null){
				statsTemp.add(line);
				if(line.equals(word)){// if word found
					line=inputFile.readLine();
					if(isCorrect){
						int nCorrect = Integer.parseInt(line);
						line = nCorrect+1+"";
					}
					statsTemp.add(line);
					line=inputFile.readLine();
					int nAttempts = Integer.parseInt(line);
					line = nAttempts+1+"";
					statsTemp.add(line);
				}
			}
			inputFile.close();
			//rewrite stats file
			PrintWriter outputFile = new PrintWriter(new FileWriter(_statsPath, false));
			for(String output: statsTemp){
				outputFile.println(output);
			}
			outputFile.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Updates the accuracy history in the appropriate stats file
	 */
	public void updateAccuracyHistory(){
		String spellingList = Settings.currentSpellingList;
		String category = Settings.currentCategory;
		ArrayList<String> accuraciesTemp = new ArrayList<String>();//store the contents of the stats file
		try {
			//copy contents of file into temporary list and update values
			BufferedReader inputFile = new BufferedReader(new FileReader(_statsPath));
			String line;
			while((line = inputFile.readLine())!=null){
				accuraciesTemp.add(line);
				if(line.equals(ACCURACY_HEADING)){
					break;
				}
			}
			//add overall accuracy
			calculateStatistics(spellingList, SpellingList.ALL_CATEGORIES);
			accuraciesTemp.add(_overallCategoryAccuracy+"");
			//find position of category
			while((line=inputFile.readLine())!=null){
				accuraciesTemp.add(line);
				if(line.equals("%"+category)){
					break;
				}
			}
			if(!category.equals(SpellingList.NO_CATEGORIES)){
				//update category accuracy
				calculateStatistics(spellingList, category);
				accuraciesTemp.add(_overallCategoryAccuracy+"");
				//add rest of the file
				while((line=inputFile.readLine())!=null){
					accuraciesTemp.add(line);
				}
			}
			inputFile.close();
			//rewrite stats file
			PrintWriter outputFile = new PrintWriter(new FileWriter(_statsPath, false));
			for(String output: accuraciesTemp){
				outputFile.println(output);
			}
			outputFile.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Updates the spelling streak in the specified category
	 * @param streak
	 */
	public void updateSpellingStreak(int streak){
		doesFileExist();
		//update statistics
		ArrayList<String> statsTemp = new ArrayList<String>();//store the contents of the stats file
		try {
			BufferedReader inputFile = new BufferedReader(new FileReader(_statsPath));
			String line=inputFile.readLine();
			//update overall streak
			if(streak>Integer.parseInt(line)){
				statsTemp.add(streak+"");
			}else{
				statsTemp.add(line);
			}
			//update category streak
			while((line=inputFile.readLine())!=null){
				statsTemp.add(line);
				if(line.equals("%"+Settings.currentCategory)){
					line=inputFile.readLine();
					if(streak>Integer.parseInt(line)){
						statsTemp.add(streak+"");
					}else{
						statsTemp.add(line);
					}
				}
				if(line.equals(ACCURACY_HEADING)){
					break;
				}
			}
			//get rest of file content
			while((line=inputFile.readLine())!=null){
				statsTemp.add(line);
			}
			inputFile.close();
			//rewrite stats file
			PrintWriter outputFile = new PrintWriter(new FileWriter(_statsPath, false));
			for(String output: statsTemp){
				outputFile.println(output);
			}
			outputFile.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}


	/**
	 * Maps words in a specified category to accuracy ratings.
	 * Calculates accuracies
	 * @param spellingList
	 * @param category
	 * @return
	 */
	public void calculateStatistics(String spellingList, String category){
		doesFileExist(spellingList);
		_accuracyMap = new HashMap<Double, LinkedList<String>>();
		if(spellingList!=null&&category!=null){
			double totalNAttempts=0;
			double totalNCorrect=0;
			String statsLocation = Settings.spellingListLocation+"."+spellingList+"-stats";
			ArrayList<String> tempLines = new ArrayList<String>(); //store lines thats are wanted temporarily
			//read stats
			try {
				BufferedReader inputFile = new BufferedReader(new FileReader(statsLocation));
				inputFile.readLine(); //skip overallstreakNumber
				String line;
				//if all categories, get all words
				if(category.equals(SpellingList.ALL_CATEGORIES)){
					while((line=inputFile.readLine())!=null){
						if(line.charAt(0)=='%'){
							inputFile.readLine();//skip streak number
						}
						if(line.charAt(0)!='%'){
							tempLines.add(line);
						}
						if(line.equals(ACCURACY_HEADING)){
							break;
						}
					}
					//else find category location
				}else{
					line=inputFile.readLine();
					if(line.charAt(0)!='%'){
						tempLines.add(line);
					} else{
						while(line!=null){
							if(line.equals("%"+category)){
								inputFile.readLine(); //skip streak number
								break;
							}
							line = inputFile.readLine();
						}
					}
					while((line=inputFile.readLine())!=null){
						if(line.charAt(0)=='%'||line.equals(ACCURACY_HEADING)){
							break;
						}
						tempLines.add(line);
					}
				}
				inputFile.close();
				//calculate accuracy ratings
				for(int i = 0; i < tempLines.size()-2; i+=3){
					String word = tempLines.get(i);
					double nCorrect = Double.parseDouble(tempLines.get(i+1));
					totalNCorrect+=nCorrect;
					double nAttempts = Double.parseDouble(tempLines.get(i+2));
					totalNAttempts+=nAttempts;
					if(nAttempts > 0){
						double accuracy = ((nCorrect*1.0)/(nAttempts*1.0))*100;
						if(!_accuracyMap.containsKey(accuracy)){
							_accuracyMap.put(accuracy, new LinkedList<String>());
						}
						_accuracyMap.get(accuracy).add(word);
					}
				}
				//calculate overall accuracy rating for the category
				if(totalNAttempts>0){
					_overallCategoryAccuracy = (totalNCorrect / totalNAttempts) *100;
				}
				else{
					_overallCategoryAccuracy = 0;
				}
				//for(double a : _accuracyMap.keySet()){
				//	System.out.println(_accuracyMap.get(a)+" : "+a);
				//}
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}

	/**
	 * Returns an ArrayList of the nth best spelled words from best spelled to worst spelled
	 * @param n
	 * @return
	 */
	public ArrayList<String> getBestWords(int n){
		doesFileExist();
		ArrayList<String> bestWords = new ArrayList<String>();
		ArrayList<Double> accuracyRatings = new ArrayList<Double>(_accuracyMap.keySet());
		Collections.sort(accuracyRatings); //sort ratings
		Collections.reverse(accuracyRatings);//reverse to get best spelled
		int nCount=0;
		for(double rating : accuracyRatings){
			for(String word : _accuracyMap.get(rating)){
				double roundedRating = Math.round(rating*100.0)/100.0;
				bestWords.add(word+" ("+roundedRating+ "% accuracy)");
				nCount++;
				if(nCount>=n){
					break;
				}
			}
		}
		return bestWords;
	}
	/**
	 * Returns an ArrayList of the nth worst spelled words from worst spelled to best spelled with accuracies
	 * @param n
	 * @return
	 */
	public ArrayList<String> getWorstWordsWithAccuracy(int n){
		doesFileExist();
		ArrayList<String> worstWords = new ArrayList<String>();
		ArrayList<Double> accuracyRatings = new ArrayList<Double>(_accuracyMap.keySet());
		Collections.sort(accuracyRatings); //sort ratings
		int nCount=0;
		for(double rating : accuracyRatings){
			for(String word : _accuracyMap.get(rating)){
				double roundedRating = Math.round(rating*100.0)/100.0;
				worstWords.add(word+" ("+roundedRating+ "% accuracy)");
				nCount++;
				if(nCount>=n){
					break;
				}
			}
		}
		return worstWords;
	}

	/**
	 * Returns an ArrayList of the nth worst spelled words from worst spelled to best spelled
	 * @param n
	 * @return
	 */
	public ArrayList<String> getWorstWords(int n){
		doesFileExist();
		ArrayList<String> worstWords = new ArrayList<String>();
		ArrayList<Double> accuracyRatings = new ArrayList<Double>(_accuracyMap.keySet());
		Collections.sort(accuracyRatings); //sort ratings
		int nCount=0;
		for(double rating : accuracyRatings){
			for(String word : _accuracyMap.get(rating)){
				double roundedRating = Math.round(rating*100.0)/100.0;
				worstWords.add(word);
				nCount++;
				if(nCount>=n){
					break;
				}
			}
		}
		return worstWords;
	}

	/**
	 * Returns the overall accuracy for the specified category
	 * @return overallCategoryAccuracy
	 */
	public double getOverallCategoryAccuracy(){
		return Math.round(_overallCategoryAccuracy*100.0)/100.0;
	}

	/**
	 * Returns the longest spelling streak for the specified spelling list and category
	 * @param spellingList
	 * @param category
	 * @return the longest streak. -1 if error
	 */
	public int getStreak(String spellingList, String category){
		doesFileExist(spellingList);
		if(spellingList!=null&&category!=null){
			String statsLocation = Settings.spellingListLocation+"."+spellingList+"-stats";
			//read stats
			try {
				BufferedReader inputFile = new BufferedReader(new FileReader(statsLocation));
				String line=inputFile.readLine();
				if(category.equals(SpellingList.ALL_CATEGORIES)){
					return Integer.parseInt(line);
				}
				else{
					while((line=inputFile.readLine())!=null){
						if(line.equals("%"+category)){
							return Integer.parseInt(inputFile.readLine());
						}
					}
				}
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		return -1;
	}
	/**
	 * Returns an array list of the acurracy history of the specified spelling list at the specified category from least recent to most recent
	 * @param spellingList
	 * @param category
	 * @return array list of the accuracy history
	 */
	public ArrayList<Integer> getAccuracyHistory(String spellingList, String category) {
		doesFileExist(spellingList);
		String filePath = Settings.spellingListLocation+"."+spellingList+"-stats";
		ArrayList<Integer> accuracyHistory = new ArrayList<Integer>();
		if(spellingList!=null && category!=null){
			try{
				BufferedReader inputFile = new BufferedReader(new FileReader(filePath));
				String line;
				while((line=inputFile.readLine())!=null){//go to ACCURACY_HEADING
					if(line.equals(ACCURACY_HEADING)){
						break;
					}
				}
				if(category.equals(SpellingList.ALL_CATEGORIES)){ //if all categories
					while((line=inputFile.readLine())!=null){
						if(line.charAt(0)=='%'){
							break;
						}
						accuracyHistory.add((int)Double.parseDouble(line));
					}
				}
				else{//if specific category
					while((line=inputFile.readLine())!=null){//find category location
						if(line.equals("%"+category)){
							break;
						}
					}
					while((line=inputFile.readLine())!=null){
						if(line.charAt(0)=='%'){
							break;
						}
						accuracyHistory.add((int)Double.parseDouble(line));
					}
				}
				Collections.reverse(accuracyHistory);
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		return accuracyHistory;
	}
	/**
	 * Clears all spelling list statistics
	 */
	public void clearStatistics() {
		File directory = new File(Settings.spellingListLocation);
		for(String fileName: directory.list()){
			File file = new File(Settings.spellingListLocation+fileName);
			//check if not directory
			if(!file.isDirectory()){
				//check if a hidden file (aka stats file)
				if(fileName.charAt(0)=='.'){
					file.delete();
				}
			}
		}
	}

	/**
	 * Returns the most recent accuracy rating for the specified category in the specified list
	 * @param spellingList
	 * @param category
	 * @return
	 */
	public double getRecentAccuracy(String spellingList, String category){
		double accuracy=-1;
		if(spellingList!=null && category!=null){
			try{
				BufferedReader inputFile = new BufferedReader(new FileReader(Settings.spellingListLocation+"."+spellingList+"-stats"));
				String line;
				while((line=inputFile.readLine())!=null){//go to ACCURACY_HEADING
					if(line.equals(ACCURACY_HEADING)){
						if(category.equals(SpellingList.ALL_CATEGORIES)){
							accuracy = Double.parseDouble(inputFile.readLine());
							return Math.round(accuracy*100.0)/100.0;
						}
						break;
					}
				}
				if(category.equals(SpellingList.NO_CATEGORIES)){
					accuracy = Double.parseDouble(inputFile.readLine());
					return Math.round(accuracy*100.0)/100.0;
				}
				else{
					while((line=inputFile.readLine())!=null){//go to category
						if(line.equals("%"+category)){
							accuracy = Double.parseDouble(inputFile.readLine());
							return Math.round(accuracy*100.0)/100.0;
						}
					}
				}
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		return accuracy;
	}
}