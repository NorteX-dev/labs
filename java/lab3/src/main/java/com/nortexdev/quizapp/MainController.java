package com.nortexdev.quizapp;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class MainController {
	@FXML
	private TextArea logBox;

	@FXML
	public void initialize() {
		logBox.setEditable(false);
		GameController gameController = new GameController(this);
		new ConnectionController(gameController);
	}

	public void appendLog(String str) {
		logBox.appendText(str + "\n");
	}
}
