package com.nortexdev.quizapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.HashMap;

public class GameController {
	public static boolean GAME_IS_ON = false;
	public static HashMap<String, String> GAME_QUESTIONS = new HashMap<>();
	public static String CURRENT_QUESTION;
	public static String CURRENT_ANSWER;

	private static int QUESTION_INDEX = 0;

	private final MainController viewController;

	public GameController(MainController viewController) {
		this.viewController = viewController;
		System.out.println("GameController created.");
		populateQuestionsFromFile("questions.txt");
		this.startGame();
	}


	public void populateQuestionsFromFile(String fileName) {
		try {
			InputStream is = MainApplication.class.getResourceAsStream(fileName);
			if (is == null) {
				System.out.println("Questions file not found.");
				return;
			}
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line;
			while ((line = br.readLine()) != null) {
				GAME_QUESTIONS.put(line.split("\\|")[0], line.split("\\|")[1]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void startGame() {
		viewController.appendLog("Nowa gra.");
		GAME_IS_ON = true;
		this.askCurrentQuestion();
	}

	public void askCurrentQuestion() {
		String[] questions = GAME_QUESTIONS.keySet().toArray(new String[0]);
		String[] answers = GAME_QUESTIONS.values().toArray(new String[0]);
		if (QUESTION_INDEX >= questions.length) {
			viewController.appendLog("\nOdpowiedziano już na wszystkie pytania. Koniec gry.");
			GAME_IS_ON = false;
			return;
		}
		CURRENT_QUESTION = questions[QUESTION_INDEX];
		CURRENT_ANSWER = answers[QUESTION_INDEX];
		viewController.appendLog("\nNr " + (QUESTION_INDEX + 1) + "): " + questions[QUESTION_INDEX]);
	}

	public void askNext() {
		if (!GAME_IS_ON) return;
		QUESTION_INDEX++;
		this.askCurrentQuestion();
	}

	public void attempt(ClientAnswer answer, InetAddress inetAddress) {
		if (!GAME_IS_ON) return;
		if (GameController.CURRENT_ANSWER.equalsIgnoreCase(answer.getAnswer())) {
			viewController.appendLog("%s (%s) odpowiedział poprawnie.".formatted(answer.getNick(), inetAddress));
			this.askNext();
			ConnectionController.clearQueue();
		} else {
			viewController.appendLog("Nadeszła odpowiedź błędna.");
		}
	}
}
