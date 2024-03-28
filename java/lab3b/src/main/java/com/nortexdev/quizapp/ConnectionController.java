package com.nortexdev.quizapp;


import javafx.scene.control.Alert;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ConnectionController {
	public static final String SERVER_HOST = "127.0.0.1";
	public static final int SERVER_PORT = 6677;
	private Socket socket = null;

	private final MainController viewController;

	public ConnectionController(MainController viewController) {
		this.viewController = viewController;
	}

	public void send(ClientAnswer clientAnswer) {
		new Thread(() -> {
			try {
				socket = new Socket();
				socket.connect(new InetSocketAddress(SERVER_HOST, SERVER_PORT));
				ObjectOutputStream dataOut = new ObjectOutputStream(socket.getOutputStream());
				dataOut.writeObject(clientAnswer);
				System.out.println("Sent " + clientAnswer.toString());
				dataOut.close();
			} catch (IOException e) {
				e.printStackTrace();
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Błąd");
				alert.setHeaderText("Błąd połączenia");
				alert.setContentText("Nie udało się wysłać odpowiedzi. Upewnij się że serwer jest uruchomiony.");
				alert.showAndWait();
			}
		}).start();
	}
}
