package voxspell.fileManagement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;

import voxspell.main.Settings;

public class Statistics {
	private String _statsPath = Settings.spellingListLocation+"."+Settings.currentSpellingList+"-stats";
	private String _wordListPath = Settings.spellingListLocation+Settings.currentSpellingList+".txt";
	public Statistics(){
		//create necessary files on startup
		File statsFile = new File(_statsPath);
		if(!statsFile.exists()){
			createStatsFile();
		}
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
			// TODO Auto-generated catch block
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
	 * Returns the accuracy ratings of all the quiz levels
	 * @return
	 */
	public ArrayList<String> getAccuracyRating(){
		ArrayList<String> correctnessRatio = new ArrayList<String>();
		//read accuracy file
		try { 
			BufferedReader inputFile = new BufferedReader(new FileReader(Settings.spellingListLocation+".accuracy"));
			String line;
			while((line = inputFile.readLine())!=null){
				correctnessRatio.add(line);
			}
			inputFile.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		//get accuracy ratings
		ArrayList<String> accuracyRatings = new ArrayList<String>();
		for(String r: correctnessRatio){
			String[] ratio = r.split("-");
			//if total attempts is 0, make value "-"
			if(ratio[1].equals("0")){
				accuracyRatings.add("-");
			}

			else{
				//calculate accuracy rating
				double rating = Double.parseDouble(ratio[0]+".0") / Double.parseDouble(ratio[1]+".0")*100;
				rating = Math.round(rating*100.0)/100.0;
				accuracyRatings.add(rating+"%");
			}
		}
		return accuracyRatings;
	}

	/**
	 * Checks if the word is a line in the specified source file
	 * @param word
	 * @param source
	 * @return
	 * @throws IOException
	 */
	private boolean isInFile(String word, String source) throws IOException{
		ArrayList<String> wordsList = new ArrayList<String>();
		BufferedReader inputFile = new BufferedReader(new FileReader(source));
		String line;
		while((line = inputFile.readLine())!=null){
			line=line.trim();
			wordsList.add(line);
		}
		inputFile.close();
		return wordsList.contains(word);
	}

	/**
	 * Removes a line in a file that is the specified word 
	 * @param word
	 * @param fileToUpdate
	 * @throws IOException
	 */
	private void updateQuizzedWords(String word, String fileToUpdate) throws IOException{
		ArrayList<String> tempWordsList = new ArrayList<String>();
		BufferedReader inputFile = new BufferedReader(new FileReader(Settings.spellingListLocation+fileToUpdate));
		String line;
		while((line = inputFile.readLine())!=null){
			line=line.trim();
			if(!line.equalsIgnoreCase(word)){
				tempWordsList.add(line);
			}
		}
		inputFile.close();
		PrintWriter outputFile = new PrintWriter(new FileWriter(Settings.spellingListLocation+fileToUpdate, false));
		for(String s: tempWordsList){
			outputFile.println(s);
		}
		outputFile.close();
	}

	/**
	 * Clears all files that store quiz statistics
	 */
	public void clearStats() {
	}

	/**
	 * Returns the statistics of all attempted words in the order of attempt
	 * @return
	 */
	public String getStats() {
		String stats = "";
		try {
			stats+="(in order of attempt)\n";
			stats+="----------------------------------------------\n";
			stats+="word-mastered-faulted-failed\n";
			stats+="----------------------------------------------\n";
			BufferedReader inputFile = new BufferedReader(new FileReader(Settings.spellingListLocation+".stats"));
			String line;
			while((line=inputFile.readLine())!= null){
				stats+=line+"\n";
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return stats;
	}
}
