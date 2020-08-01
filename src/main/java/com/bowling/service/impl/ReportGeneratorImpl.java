package com.bowling.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.bowling.service.BowlingGame;
import com.bowling.service.ReportGenerator;
import com.bowling.util.Constant;
import com.bowling.util.GameUtil;

public class ReportGeneratorImpl implements ReportGenerator{

    /**
	  * Generate and print the report of Bowling ,containing the Header,
	  * Player's name , pintfalls and score 
	  *
	  * @param  mapPlayers  Map containing Players and their pintfalls
	  * @param  maxLengthName Integer representing the maxLength of the Player's name
	  * @return 
	  */
	@Override
	public void generateReport(Map<String, List<String>> mapPlayers, int maxLengthName) {
		BowlingGame bowlingGame = new BowlingGameImpl();
		int rows =  mapPlayers.size()*3+1;
		List<String> reportList = new ArrayList<String>(rows);
		
		reportList.add(GameUtil.getHeader(maxLengthName+1));
		
		String pinfalls,score;
		if(maxLengthName>8) {
			pinfalls = "Pinfalls  "+GameUtil.addSpace(maxLengthName-8)+Constant.Tab;
			score    = "Score  "+GameUtil.addSpace(maxLengthName-5);
		}else {
			pinfalls = "Pinfalls  |";
			score    = "Score     ";
		}
		
		String[] scoresValues;
		int[] scores;
		
		for(Map.Entry<String,List<String>> entry:mapPlayers.entrySet()) {
			scoresValues = new String[entry.getValue().size()];
			scoresValues = entry.getValue().toArray(scoresValues);
		     
			reportList.add(entry.getKey());    
			reportList.add(pinfalls+bowlingGame.getPinFallsPlayer(scoresValues));

			 scores = entry.getValue().stream()
					         .mapToInt(a->{if(a.equals(Constant.Fault)) return 0 ;else return new Integer(a);})
					         .toArray();

			reportList.add(score   +bowlingGame.getScoresPlayer(scores));
		}
		
		reportList.forEach(a->System.out.println(a));
	}

}
