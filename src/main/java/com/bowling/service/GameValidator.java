package com.bowling.service;

import java.util.List;
import java.util.Map;

public interface GameValidator {

	boolean validateInputPlayers(Map<String,List<String>> mapPlayers);
	boolean validateInputList(String[] scores);
}
