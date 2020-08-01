package com.bowling.service.impl;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import com.bowling.service.GameValidator;
import com.bowling.service.ProcessFile;
import com.bowling.service.ProcessScoreGame;
import com.bowling.service.ReportGenerator;

public class ProcessScoreGameImpl implements ProcessScoreGame{

	 /**
	    * Performs the calculation of score and pinfalls of all players, 
	    * to then print the results.  
	    *
	    * @param  nameFile  Name of the file to be processed
	    */
	@Override
	public void processBowlingScoreGame(String nameFile) {
		try {
			ProcessFile processFile = new ProcessFileImpl();
			Map<String,List<String>> mapPlayers = processFile.readFile(nameFile);
			
			int maxLengthPlayerName;
			maxLengthPlayerName = mapPlayers.keySet().stream().max(Comparator.comparing(a->a.length())).get().length();
			
			GameValidator gameValidator = new GameValidatorImpl();
			gameValidator.validateInputPlayers(mapPlayers); 
			
			ReportGenerator reportGenerator = new ReportGeneratorImpl();
			reportGenerator.generateReport(mapPlayers, maxLengthPlayerName);
		}catch(IOException e) {
			System.out.println("Error to process income data");
	    }catch(NumberFormatException e) {
		     System.out.println("Error : Input different than F should be numeric");
	    }catch(Exception e) {
		     System.out.println("Error to process the Bowling Game");
	    }
	}


}
