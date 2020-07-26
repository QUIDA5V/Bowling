package com.bowling.service.impl;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import com.bowling.service.BowlingGame;
import com.bowling.util.Constant;
import com.bowling.util.GameUtil;

@Service
public class BowlingGameImpl implements BowlingGame{

    Predicate<Integer> isThen = a -> a == 10;
    Predicate<String> isFault = a -> a.equals(Constant.Fault);
    BiPredicate<Integer,Integer> sumIsThen = (a,b) -> a+b == 10;
    Predicate<String> isValueThen = a -> a.equals("10");
    BiPredicate<String,String> sumValuesIsThen = (a,b) ->{ a = isFault.test(a)?"0":a;
                                                           b = isFault.test(b)?"0":b;
    	                                                   return new Integer(a)+new Integer(b) == 10;
                                                         };
    @Override                                                     
	public String getPinFallsPlayer(String[] scores) {

	     boolean isNewTab = true;
	     StringBuilder sbResultScore = new StringBuilder();
	     int counterTab = 0;
	     
	     for(int i=0;i<scores.length-2;i++) {
	    	 if(counterTab == 9) {
	    		 sbResultScore.append(isValueThen.test(scores[i])?Constant.Strike:scores[i])
	    		              .append(Constant.TwoSpace);
	    		 sbResultScore.append(isValueThen.test(scores[i+1])?Constant.Strike:scores[i+1])
	    		              .append(Constant.TwoSpace);
	    		 sbResultScore.append(isValueThen.test(scores[i+2])?Constant.Strike:scores[i+2])
	    		              .append(Constant.TwoSpace);
	    		 break;
	    	 }
	    	 if(isNewTab) {
	    		 if(isFault.test(scores[i]) || !isThen.test(new Integer(scores[i]))) {
	    			 sbResultScore.append(scores[i]);
	    			 isNewTab = false;
	    			 counterTab--;	    			 
	    		 }else{
	    			 sbResultScore.append(Constant.ThreeSpace+Constant.Strike);
	    			 isNewTab = true;
	    		 }
	    	 }else if(sumValuesIsThen.test(scores[i], scores[i-1])) {
   			          sbResultScore.append(Constant.Spare);
   			          isNewTab = true;
	    	 }else {
   			          sbResultScore.append(scores[i]);
   			          isNewTab = true;
	    	 }
	    	 sbResultScore.append(Constant.TwoSpace);
	    	 counterTab++;

	     }
	     
		return sbResultScore.toString();
	}
	
    @Override 
	public String getScoresPlayer(int[] scores) {
		
	    boolean isNewTab = true;
	    int counterTab = 0;
	    int sum = 0;
	    StringBuilder sbScoresPlayer = new StringBuilder();

	    for(int i=0;i<scores.length-2;i++) {
	    	if(isThen.test(counterTab)) {
	    		sum = sum+scores[i]+scores[i+1]+scores[i+2];
	    		sbScoresPlayer.append(sum);
	    		break;
	    	}
	    	if(isNewTab) {
		    	if(isThen.test(scores[i])) {
		    		sum = sum+scores[i]+scores[i+1]+scores[i+2];
		    		isNewTab = true;
		    	}else {
		    		isNewTab = false;
		    		counterTab--;
		    	}	
	    	}else if(sumIsThen.test(scores[i], scores[i-1])){
	    		sum = sum+scores[i-1]+scores[i]+scores[i+1];
	    		isNewTab = true;
	    	}else {
	    		sum = sum+scores[i-1]+scores[i];
	    		isNewTab = true;
	    	}
	    	counterTab++;
	    	
	    	if(isNewTab) { 
	    		sbScoresPlayer.append(sum);
	    		sbScoresPlayer.append(GameUtil.addSpace(6-(sum+"").length()));
	    	}

	    } 
		return sbScoresPlayer.toString();
	}
}
