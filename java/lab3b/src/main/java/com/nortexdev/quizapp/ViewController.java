package com.nortexdev.quizapp;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ViewController {
	public static final int port = 6677;
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
		Answer answer = new Answer(nickInput.getText(), answerInput.getText());
		new Thread(() -> {
			try {
				Socket socket = new Socket();
				socket.connect(new InetSocketAddress("127.0.0.1", port));
				ObjectOutputStream dataOut = new ObjectOutputStream(socket.getOutputStream());
				dataOut.writeObject(answer);
				dataOut.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}).start();
	}
}
