package com.bowling.service.impl;

import java.util.function.BiPredicate;
import java.util.function.Predicate;
import com.bowling.service.BowlingGame;
import com.bowling.util.Constant;
import com.bowling.util.GameUtil;

public class BowlingGameImpl implements BowlingGame{

    Predicate<Integer> isThen = a -> a == 10;
    Predicate<String> isFault = a -> a.equals(Constant.Fault);
    BiPredicate<Integer,Integer> sumIsThen = (a,b) -> a+b == 10;
    Predicate<String> isValueThen = a -> a.equals("10");
    BiPredicate<String,String> sumValuesIsThen = (a,b) ->{ a = isFault.test(a)?"0":a;
                                                           b = isFault.test(b)?"0":b;
    	                                                   return new Integer(a)+new Integer(b) == 10;
                                                         };

    /**
      * Formats pintFalls to be printed in the PintFalls Section
      *
      * @param  pintFalls  Array containing the pintFalls of one Player
      * @return PintFalls formatted to be printed
      */                                                         
    @Override                                                     
	public String getPinFallsPlayer(String[] pintFalls) {

	     boolean isNewTab = true;
	     StringBuilder sbResultScore = new StringBuilder();
	     int counterTab = 0;
	     
	     for(int i=0;i<pintFalls.length-2;i++) {
	    	 if(counterTab == 9) {
	    		 sbResultScore.append(Constant.Space);
	    		 sbResultScore.append(isValueThen.test(pintFalls[i])?Constant.Strike:pintFalls[i]);
	    		 sbResultScore.append(Constant.Space);
	    		 sbResultScore.append(isValueThen.test(pintFalls[i+1])?Constant.Strike:pintFalls[i+1]);
	    		 sbResultScore.append(Constant.Space);
	    		 sbResultScore.append(isValueThen.test(pintFalls[i+2])?Constant.Strike:pintFalls[i+2]);

	    		 break;
	    	 }
	    	 if(isNewTab) {
	    		 if(isFault.test(pintFalls[i]) || !isThen.test(new Integer(pintFalls[i]))) {
	    			 sbResultScore.append(Constant.ThreeSpace+pintFalls[i]+Constant.Space);
	    			 isNewTab = false;
	    			 counterTab--;	    			 
	    		 }else{
	    			 sbResultScore.append(Constant.ThreeSpace+Constant.Strike+Constant.SeparatorTab);
	    			 isNewTab = true;
	    		 }
	    	 }else if(sumValuesIsThen.test(pintFalls[i], pintFalls[i-1])) {
   			          sbResultScore.append(Constant.SpareTab);
   			          isNewTab = true;
	    	 }else {
   			          sbResultScore.append(pintFalls[i]+Constant.Space+Constant.Tab);
   			          isNewTab = true;
	    	 }
	    	
	    	 counterTab++;

	     }
	     
		return sbResultScore.toString();
	}
	
    /**
     * Calculate the scores based in the pintFalls in the Score Section
     *
     * @param  pintFalls  Array containing the pintFalls of one Player
     * @return Scores to be printed
     */ 
    @Override 
	public String getScoresPlayer(int[] pintFalls) {
		
	    boolean isNewTab = true;
	    int counterTab = 0;
	    int sum = 0;
	    StringBuilder sbScoresPlayer = new StringBuilder();

	    for(int i=0;i<pintFalls.length-2;i++) {
	    	if(isThen.test(counterTab)) {
	    		sum = sum+pintFalls[i]+pintFalls[i+1]+pintFalls[i+2];
	    		sbScoresPlayer.append(sum);
	    		break;
	    	}
	    	if(isNewTab) {
		    	if(isThen.test(pintFalls[i])) {
		    		sum = sum+pintFalls[i]+pintFalls[i+1]+pintFalls[i+2];
		    		isNewTab = true;
		    	}else {
		    		isNewTab = false;
		    		counterTab--;
		    	}	
	    	}else if(sumIsThen.test(pintFalls[i], pintFalls[i-1])){
	    		sum = sum+pintFalls[i-1]+pintFalls[i]+pintFalls[i+1];
	    		isNewTab = true;
	    	}else {
	    		sum = sum+pintFalls[i-1]+pintFalls[i];
	    		isNewTab = true;
	    	}
	    	counterTab++;
	    	
	    	if(isNewTab) { 
	    		sbScoresPlayer.append(Constant.Tab+sum);
	    		sbScoresPlayer.append(GameUtil.addSpace(7-(sum+"").length()));
	    	}

	    } 
		return sbScoresPlayer.toString();
	}
}
