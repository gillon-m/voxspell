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

import voxspell.main.Settings;

public class Statistics {
	private String _statsPath = Settings.spellingListLocation+"."+Settings.currentSpellingList+"-stats";
	private String _wordListPath = Settings.spellingListLocation+Settings.currentSpellingList+".txt";

	private HashMap<Double, String> _accuracyMap;
	private double _overallCategoryAccuracy;
	public Statistics(){
		//create necessary files on startup
		File statsFile = new File(_statsPath);
		if(!statsFile.exists()){
			createStatsFile();
		}
	}
	public void doesFileExist(String filePath){

	}
	/**
	 * Create word statistics file
	 */
	public void createStatsFile(){
		try {
			PrintWriter statsFile = new PrintWriter(new FileWriter(_statsPath, true));
			BufferedReader wordListFile = new BufferedReader(new FileReader(_wordListPath));
			String wordLine;
			while((wordLine = wordListFile.readLine())!=null){
				statsFile.println(wordLine);
				if(wordLine.charAt(0)!='%'){
					statsFile.println("0");
					statsFile.println("0");
				}
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
	public void updateWordStatistics(String word, boolean isCorrect){

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
	 * Maps words in a specified category to accuracy ratings
	 * @param spellingList
	 * @param category
	 * @return
	 */
	public void mapCategoryAccuracy(String spellingList, String category){
		_accuracyMap = new HashMap<Double, String>();
		if(spellingList!=null&&category!=null){
			double totalNAttempts=0;
			double totalNCorrect=0;
			String statsLocation = Settings.spellingListLocation+"."+spellingList+"-stats";
			ArrayList<String> tempLines = new ArrayList<String>(); //store lines thats are wanted temporarily
			//read stats
			try {
				BufferedReader inputFile = new BufferedReader(new FileReader(statsLocation));
				String line;
				//if all categories, get all words
				if(category.equals(SpellingList.ALL_CATEGORIES)){
					while((line=inputFile.readLine())!=null){
						if(line.charAt(0)!='%'){
							tempLines.add(line);
						}
					}
				//else find category location
				}else{
					line=inputFile.readLine();
					//get category location
					if(line.charAt(0)!='%'){
						tempLines.add(line);
					} else{
						while(line!=null){
							if(line.equals("%"+category)){
								break;
							}
							line = inputFile.readLine();
						}
					}
					while((line=inputFile.readLine())!=null){
						if(line.charAt(0)=='%'){
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
						_accuracyMap.put(accuracy, word);
					}
				}
				//calculate overall accuracy rating for the category
				if(totalNAttempts>0){
					_overallCategoryAccuracy = (totalNCorrect / totalNAttempts) *100;
				}
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
		ArrayList<String> bestWords = new ArrayList<String>();
		ArrayList<Double> accuracyRatings = new ArrayList<Double>(_accuracyMap.keySet());
		Collections.sort(accuracyRatings); //sort ratings
		Collections.reverse(accuracyRatings);//reverse to get best spelled
		for(int i = 0; i < accuracyRatings.size() && i < n; i++){
			bestWords.add(_accuracyMap.get(accuracyRatings.get(i)));
		}
		return bestWords;
	}

	/**
	 * Returns an ArrayList of the nth worst spelled words from worst spelled to best spelled
	 * @param n
	 * @return
	 */
	public ArrayList<String> getWorstWords(int n){
		ArrayList<String> worstWords = new ArrayList<String>();
		ArrayList<Double> accuracyRatings = new ArrayList<Double>(_accuracyMap.keySet());
		Collections.sort(accuracyRatings); //sort ratings
		for(int i = 0; i < accuracyRatings.size() && i < n; i++){
			worstWords.add(_accuracyMap.get(accuracyRatings.get(i)));
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
}