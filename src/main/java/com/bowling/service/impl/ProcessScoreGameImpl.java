package com.bowling.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bowling.service.GameValidator;
import com.bowling.service.ProcessScoreGame;
import com.bowling.service.ReportGenerator;

@Service
public class ProcessScoreGameImpl implements ProcessScoreGame{
    
	@Autowired
	GameValidator gameValidator;
	
	@Autowired
	ReportGenerator reportGenerator;
	
	private int maxLengthPlayerName;
	
	@Override
	public void processBowlingScoreGame(String nameFile) {
		try {
			Map<String,List<String>> mapPlayers = getDataFromFile(nameFile);
			gameValidator.validateInputPlayers(mapPlayers); 
			reportGenerator.generateReport(mapPlayers, maxLengthPlayerName);
		}catch(IOException e) {
			System.out.println("Error to process income data");
	    }catch(NumberFormatException e) {
		     System.out.println("Error : Input different than F should be numeric");
	         e.printStackTrace();
	    }catch(Exception e) {
		     System.out.println("Error to process the Bowling Game");
		     e.printStackTrace();
	    }
	}

	
	private Map<String,List<String>> getDataFromFile(String nameFile) throws IOException {
		Map<String,List<String>> mapPlayers = new HashMap<String,List<String>>();
		BufferedReader br = null;
        File file = new File(nameFile);
			
	    br = new BufferedReader(new FileReader(file)); 
  
		String st; 
			
		int indexBlanc = 0;
		int maxLengthName = 0;
		String score = "";
		while ((st = br.readLine()) != null) {
			indexBlanc = st.indexOf(" ");
			score = st.substring(indexBlanc+1);
			if(indexBlanc > maxLengthName)
				maxLengthName = indexBlanc;
				
			mapPlayers.computeIfAbsent(st.substring(0,indexBlanc),  k -> new ArrayList<>()).add(score);
		}
		maxLengthPlayerName = maxLengthName;
		
		try {
			if(br!=null) br.close();
		}catch(Exception e) {}
		
		return mapPlayers;
	}
	

}
