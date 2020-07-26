package com.bowling.service.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.bowling.service.GameValidator;

@RunWith(SpringRunner.class)
public class TestGameValidatorImpl {

    @TestConfiguration
    static class GameValidatorImplTestContextConfiguration {
        @Bean
        public GameValidator gameValidator() {
            return new GameValidatorImpl();
        }
    }

    @Autowired
    GameValidator gameValidator;
    
    @Test(expected = NumberFormatException.class)
    public void whenFindLettersOthersThanF_thenReturnError() {
    	String[] scores = {"A","-1","F","10","5","12"};
    	gameValidator.validateInputList(scores);
    }
    
    @Test
    public void whenFindNegativeOrBiggerThanThen_thenReturnFalse() {
      	 // given
       	String[] scores = {"10","7","3","9","0","10","0","8","-8","2","F","6","15","10","10","8","1"};
    	//when
    	boolean isValidInputList = gameValidator.validateInputList(scores);
    	assertFalse(isValidInputList);
    }
    @Test
    public void whenFindPositiveOrLessThanThen_thenReturnTrue() {
    	 // given
    	String[] scores = {"10","7","3","9","0","10","0","8","8","2","F","6","10","10","10","8","1"};

    	//when
    	boolean isValidInputList = gameValidator.validateInputList(scores);
    	assertTrue(isValidInputList);
    }
    @Test
    public void whenFindIncompleteValues_thenReturnFalse() {
      	 // given
       	String[] scores = {"10","7","3","9","0","10","0","8","8","2","F","6","10","8","1"};
    	//when
    	boolean isValidInputList = gameValidator.validateInputList(scores);
    	assertFalse(isValidInputList);
    }
    @Test
    public void whenFindExtraValues_thenReturnFalse() {
   	    // given
   	    String[] scores = {"10","7","3","9","0","10","0","8","8","2","F","6","10","10","10","8","1","10"};
    	//when
    	boolean isValidInputList = gameValidator.validateInputList(scores);
    	assertFalse(isValidInputList);
    }
}
