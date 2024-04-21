package com.nortexdev.quizapp;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class ViewController {
	private ConnectionController connectionController;


	@FXML
	private TextField answerInput;

	@FXML
	private TextField nickInput;

	@FXML
	public void initialize() {
		connectionController = new ConnectionController();
	}

	@FXML
	private void onSendAnswerBtnClick() {
		assert connectionController != null;
		if (nickInput.getText().isEmpty() || answerInput.getText().isEmpty()) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Błąd");
			alert.setHeaderText("Nie podano wszystkich danych");
			alert.setContentText("Wprowadź nick i odpowiedź.");
			alert.showAndWait();
			return;
		}
		ClientAnswer answer = new ClientAnswer(nickInput.getText(), answerInput.getText());
		connectionController.send(answer);
	}
}
