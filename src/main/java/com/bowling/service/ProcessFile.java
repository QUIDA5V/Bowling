package com.bowling.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ProcessFile {

	Map<String,List<String>> readFile(String fileName) throws IOException ;
}
