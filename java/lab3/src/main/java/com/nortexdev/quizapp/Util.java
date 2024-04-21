package com.nortexdev.quizapp;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Util {
	@SneakyThrows
	public static HashMap<String, String> getQuestionsFromFile(String fileName) {
		HashMap<String, String> intermediateQuestions = new HashMap<>();
		InputStream is = MainApplication.class.getResourceAsStream(fileName);
		if (is == null) return null;
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String line;
		while ((line = br.readLine()) != null) {
			String[] parts = line.split("\\|");
			intermediateQuestions.put(parts[0], parts[1]);
		}
		br.close();
		return intermediateQuestions;
	}
}
