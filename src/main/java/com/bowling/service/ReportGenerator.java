package com.bowling.service;

import java.util.List;
import java.util.Map;

public interface ReportGenerator {

	void generateReport(Map<String,List<String>> mapPlayers,int maxLengthName);
}
