package com.bowling.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bowling.service.BowlingGame;
import com.bowling.service.ReportGenerator;
import com.bowling.util.Constant;
import com.bowling.util.GameUtil;

@Service
public class ReportGeneratorImpl implements ReportGenerator{

	@Autowired
	BowlingGame bowlingGame;
	
	@Override
	public void generateReport(Map<String, List<String>> mapPlayers, int maxLengthName) {
		
		System.out.println(GameUtil.getHeader(maxLengthName+1));
		String pinfalls,score;
		if(maxLengthName>8) {
			pinfalls = "Pinfalls  "+GameUtil.addSpace(maxLengthName-8);
			score    = "Score  "+GameUtil.addSpace(maxLengthName-5);
		}else {
			pinfalls = "Pinfalls  ";
			score    = "Score     ";
		}
		String[] scoresValues;
		int[] scores;
		for(Map.Entry<String,List<String>> entry:mapPlayers.entrySet()) {
			scoresValues = new String[entry.getValue().size()];
			scoresValues = entry.getValue().toArray(scoresValues);
		     
		     System.out.println(entry.getKey());
			 System.out.println(pinfalls+bowlingGame.getPinFallsPlayer(scoresValues));

			 scores = entry.getValue().stream()
					         .mapToInt(a->{if(a.equals(Constant.Fault)) return 0 ;else return new Integer(a);})
					         .toArray();

			 System.out.println(score   +bowlingGame.getScoresPlayer(scores));
		}
		
	}

}
