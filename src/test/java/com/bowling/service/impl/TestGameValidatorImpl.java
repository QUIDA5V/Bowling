package com.bowling.service.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import com.bowling.service.GameValidator;

public class TestGameValidatorImpl {

    GameValidator gameValidator = new GameValidatorImpl();
    
    @Test(expected = NumberFormatException.class)
    public void whenFindLettersOthersThanF_thenReturnError() {
    	String[] pintFalls = {"A","-1","F","10","5","12"};
    	gameValidator.validateInputList(pintFalls);
    }
    
    @Test
    public void whenFindNegativeOrBiggerThanThen_thenReturnFalse() {
      	 // given
       	String[] pintFalls = {"10","7","3","9","0","10","0","8","-8","2","F","6","15","10","10","8","1"};
    	//when
    	boolean isValidInputList = gameValidator.validateInputList(pintFalls);
    	assertFalse(isValidInputList);
    }
    @Test
    public void whenFindPositiveOrLessThanThen_thenReturnTrue() {
    	 // given
    	String[] pintFalls = {"10","7","3","9","0","10","0","8","8","2","F","6","10","10","10","8","1"};

    	//when
    	boolean isValidInputList = gameValidator.validateInputList(pintFalls);
    	assertTrue(isValidInputList);
    }
    @Test
    public void whenFindIncompleteValues_thenReturnFalse() {
      	 // given
       	String[] pintFalls = {"10","7","3","9","0","10","0","8","8","2","F","6","10","8","1"};
    	//when
    	boolean isValidInputList = gameValidator.validateInputList(pintFalls);
    	assertFalse(isValidInputList);
    }
    @Test
    public void whenFindExtraValues_thenReturnFalse() {
   	    // given
   	    String[] pintFalls = {"10","7","3","9","0","10","0","8","8","2","F","6","10","10","10","8","1","10"};
    	//when
    	boolean isValidInputList = gameValidator.validateInputList(pintFalls);
    	assertFalse(isValidInputList);
    }
}
