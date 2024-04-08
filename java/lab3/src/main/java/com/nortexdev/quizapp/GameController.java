package com.nortexdev.quizapp;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class GameController {
	private final MainController viewController;

	// Game state vars
	private static boolean GAME_IS_ON = false;
	private static HashMap<String, String> GAME_QUESTIONS = new HashMap<>();
	private static final AtomicInteger QUESTION_INDEX = new AtomicInteger(0);

	public GameController(MainController viewController) {
		this.viewController = viewController;
		System.out.println("GameController created.");
		GAME_QUESTIONS = Util.getQuestionsFromFile("questions.txt");
		if (GAME_QUESTIONS == null) {
			throw new RuntimeException("Failed to load questions.");
		}
		this.startGame();
	}


	private static String[] getQuestionsArray() {
		return GAME_QUESTIONS.keySet().toArray(new String[0]);
	}

	private static String[] getAnswersArray() {
		return GAME_QUESTIONS.values().toArray(new String[0]);
	}

	public void startGame() {
		viewController.appendLog("Nowa gra.");
		GAME_IS_ON = true;
		this.askCurrentQuestion();
	}

	public void askCurrentQuestion() {
		String[] questions = getQuestionsArray();
		String[] answers = getAnswersArray();

		int i = QUESTION_INDEX.get();
		viewController.appendLog("\nNr %s): %s".formatted(i + 1, questions[i]));
	}


	public void askNext() {
		if (!GAME_IS_ON) return;
		String[] questions = getQuestionsArray();
		QUESTION_INDEX.incrementAndGet();
		if (QUESTION_INDEX.get() >= questions.length) {
			viewController.appendLog("\nOdpowiedziano już na wszystkie pytania. Koniec gry.");
			GAME_IS_ON = false;
			return;
		}
		this.askCurrentQuestion();
	}

	public void attempt(ClientAnswer answer, InetAddress inetAddress) {
		if (!GAME_IS_ON) return;
		
		String[] questions = getQuestionsArray();
		int i = QUESTION_INDEX.get();
		String correctAnswer = GAME_QUESTIONS.get(questions[i]);

		if (correctAnswer.equalsIgnoreCase(answer.getAnswer())) {
			viewController.appendLog("%s (%s) odpowiedział poprawnie.".formatted(answer.getNick(), inetAddress));
			this.askNext();
			ConnectionController.clearQueue();
		} else {
			viewController.appendLog("Nadeszła odpowiedź błędna.");
		}
	}
}
