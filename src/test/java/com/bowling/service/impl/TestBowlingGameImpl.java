package com.bowling.service.impl;

import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;
import com.bowling.service.BowlingGame;

public class TestBowlingGameImpl {

    BowlingGame bowlingGame = new BowlingGameImpl();
    
    @Test
    public void getPinFallsWhenPinsAreNotSame_thenReturnTrue() {
    	 // given
    	String[] pintFalls1 = {"10","7","3","9","0","10","0","8","8","2","F","6","10","10","10","8","1"};
        String[] pintFalls2 = {"5","5","10","9","F","10","10","10","7","3","0","10","3","4","10","5","4"};

    	//when
    	String resultPinFalls1 = bowlingGame.getPinFallsPlayer(pintFalls1);
    	String resultPinFalls2 = bowlingGame.getPinFallsPlayer(pintFalls2);

    	String expectedWithOutSpace1 ="X7/90X088/F6XXX81"; 
    	String expectedWithOutSpace2 ="5/X9FXXX7/0/34X54"; 
    	assertTrue(resultPinFalls1.replace(" ","").replace("|","").equals(expectedWithOutSpace1));
    	assertTrue(resultPinFalls2.replace(" ","").replace("|","").equals(expectedWithOutSpace2));
    }
    @Test
    public void getPinFallsWhenAllPinsAreF_thenReturnTrue() {
    	 // given
    	String[] pintFalls = {"F","F","F","F","F","F","F","F","F","F","F","F","F","F","F","F","F","F","F","F","F"};
       
    	//when
    	String resultPinFalls = bowlingGame.getPinFallsPlayer(pintFalls);
    	String expectedWithOutSpace ="FFFFFFFFFFFFFFFFFFFFF"; 
    	assertTrue(resultPinFalls.replace(" ","").replace("|","").equals(expectedWithOutSpace));
    }
    
    @Test
    public void getPinFallsWhenAllPinsAreZero_thenReturnTrue() {
    	 // given
    	String[] pintFalls = {"0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"};
        
    	//when
    	String resultPinFalls = bowlingGame.getPinFallsPlayer(pintFalls);

    	String expectedWithOutSpace ="000000000000000000000"; 

    	assertTrue(resultPinFalls.replace(" ","").replace("|","").equals(expectedWithOutSpace));
    }
    @Test
    public void getPinFallsWhenAllPinsAreThen_thenReturnTrue() {
    	 // given
    	String[] pintFalls = {"10","10","10","10","10","10","10","10","10","10","10","10"};
        
    	//when
    	String resultPinFalls = bowlingGame.getPinFallsPlayer(pintFalls);

    	String expectedWithOutSpace ="XXXXXXXXXXXX"; 

    	assertTrue(resultPinFalls.replace(" ","").replace("|","").equals(expectedWithOutSpace));
    }
    @Test
    public void getScoresPlayerWhenHaveRandomValues() {
    	 // given
    	int[] pintFalls = {10,7,3,9,0,10,0,8,8,2,0,6,10,10,10,8,1};
        
    	//when
    	String resultScorePlayer = bowlingGame.getScoresPlayer(pintFalls);
        String[] resultScorePlayer1 = resultScorePlayer.replace("|","").split(" ");

    	int[] result = new int[10];
    	int[] expectedResult = {20,39,48,66,74,84,90,120,148,167};
    	int counter = 0;
    	for(int i=0;i<resultScorePlayer1.length;i++) {
    		if(resultScorePlayer1[i].equals(""))
    			continue;
    		result[counter] = new Integer(resultScorePlayer1[i]);
    		counter++;    	
    	}
    	
    	Assert.assertArrayEquals( expectedResult, result);

    }
    @Test
    public void getScoresPlayerWhenValuesAreThen() {
    	 // given
    	int[] pintFalls = {10,10,10,10,10,10,10,10,10,10,10,10};
        
    	//when
    	String resultScorePlayer = bowlingGame.getScoresPlayer(pintFalls);
        String[] resultScorePlayer1 = resultScorePlayer.replace("|","").split(" ");

    	int[] result = new int[10];
    	int[] expectedResult = {30,60,90,120,150,180,210,240,270,300};
    	int counter = 0;
    	for(int i=0;i<resultScorePlayer1.length;i++) {
    		if(resultScorePlayer1[i].equals(""))
    			continue;
    		result[counter] = new Integer(resultScorePlayer1[i]);
    		counter++;    	
    	}
    	
    	Assert.assertArrayEquals( expectedResult, result);

    }
    
}
