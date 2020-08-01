package com.bowling.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.bowling.service.ProcessFile;

public class ProcessFileImpl implements ProcessFile{
	
    /**
	  * Read a fileName and create a Map of player and list of their pintfalls
	  *
	  * @param  fileName  nameFile  Name of the file to be processed
	  * @return Map containing Players and their pintfalls
	  */
	@Override
	public Map<String, List<String>> readFile(String fileName) throws IOException {
		Map<String,List<String>> mapPlayers = new LinkedHashMap<String,List<String>>();
		BufferedReader br = null;
        File file = new File(fileName);
			
	    br = new BufferedReader(new FileReader(file)); 
  
		String st; 
		int indexBlanc = 0;
		String score = "";
		while ((st = br.readLine()) != null) {
			indexBlanc = st.indexOf(" ");
			score = st.substring(indexBlanc+1);
	
			mapPlayers.computeIfAbsent(st.substring(0,indexBlanc),  k -> new ArrayList<>()).add(score);
		}

		try {
			if(br!=null) br.close();
		}catch(Exception e) {}
		
		return mapPlayers;
	}

}
