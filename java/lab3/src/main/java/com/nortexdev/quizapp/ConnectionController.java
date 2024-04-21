package com.nortexdev.quizapp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ConnectionController {
	public static final int PORT = 6677;
	public static final BlockingQueue<Socket> SOCKET_QUEUE = new LinkedBlockingQueue<>();

	private final GameController gameController;

	public ConnectionController(GameController gameController) {
		this.gameController = gameController;
		new Thread(this::createProducer).start();
		new Thread(this::createConsumer).start();
	}

	public void createProducer() {
		try {
			ServerSocket serverSocket = new ServerSocket(ConnectionController.PORT);
			System.out.println("Listening at " + ConnectionController.PORT + ".");
			while (true) {
				Socket socket = serverSocket.accept();
				SOCKET_QUEUE.put(socket);
			}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			System.out.println("Server socket producer failed.");
		}
	}

	public void createConsumer() {
		while (true) {
			try {
				Socket socket = SOCKET_QUEUE.take();
				ObjectInputStream dataIn = new ObjectInputStream(socket.getInputStream());
				ClientAnswer answer = (ClientAnswer) dataIn.readObject();
				gameController.attempt(answer, socket.getInetAddress());
				dataIn.close();
			} catch (InterruptedException | IOException | ClassNotFoundException e) {
				e.printStackTrace();
				System.out.println("Server socket consumer failed.");
			}
		}
	}

	public static void clearQueue() {
		SOCKET_QUEUE.clear();
	}
}
