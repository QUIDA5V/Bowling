package com.bowling;

import com.bowling.service.ProcessScoreGame;
import com.bowling.service.impl.ProcessScoreGameImpl;

public class Application {

	public static void main(String[] args) {
		ProcessScoreGame processScoreGame = new ProcessScoreGameImpl();
		processScoreGame.processBowlingScoreGame(args[0]);
	}

}
