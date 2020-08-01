package com.bowling.service.impl;

import java.util.List;
import java.util.Map;
import com.bowling.service.GameValidator;
import com.bowling.util.Constant;

public class GameValidatorImpl implements GameValidator{
    
    /**
	  * Validate if the input of Player's pintfalls are all valid
	  *
	  * @param  mapPlayers  Map containing Players and their pintfalls
	  * @return boolean representing if the input is valid or not
	  */
	@Override
	public boolean validateInputPlayers(Map<String,List<String>> mapPlayers) {
		String[] scores;
		
		for(Map.Entry<String,List<String>> entry:mapPlayers.entrySet()) {
		     scores = new String[entry.getValue().size()];
		     scores = entry.getValue().toArray(scores);

		     if(!validateInputList(scores)) {
		    	 System.out.println("Error : Nro of Throws not valid for player:"+entry.getKey());
		    	 break;
		     }
		}


		return false;
	}
	
	@Override
	public boolean validateInputList(String[] scores) {
	    int doubleNroOfThrows = 0;
	    boolean isValidList = false;
	    
		for(int i=0;i<scores.length;i++) {
           
			
			if(doubleNroOfThrows == 18) {
				if(i+3 == scores.length && isValidValue(scores[i])&&
						isValidValue(scores[i+1]) && isValidValue(scores[i+2]))
					isValidList = true;
				
				break;	
			}
			
			if(scores[i].equals(Constant.Fault)) {
				doubleNroOfThrows++;
			}else if(new Integer(scores[i])>=0 && new Integer(scores[i])<10) {
				doubleNroOfThrows++;
		    }else if(new Integer(scores[i]) == 10){
				doubleNroOfThrows = doubleNroOfThrows+2;
		    }else {
		    	System.out.println(scores[i] + " Is not Valid Number");
		    	break;	
		    }
		}
		
		return isValidList;
	}
	private boolean isValidValue(String s) {
		 if(s.equals(Constant.Fault)) {
			 return true;
		 }
		 if(new Integer(s)>=0 && new Integer(s)<=10) {
			 return true;
		 }
		 return false; 
	}
	
}
