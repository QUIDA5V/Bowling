package com.bowling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bowling.service.ProcessScoreGame;

@SpringBootApplication
public class ApplicationGame  implements CommandLineRunner{

	@Autowired
	ProcessScoreGame processScoreGame;
	
	public static void main(String[] args) {
		SpringApplication.run(ApplicationGame.class, args);
	}
    @Override
    public void run(String... args) {	
       processScoreGame.processBowlingScoreGame(args[0]);
    }
}
