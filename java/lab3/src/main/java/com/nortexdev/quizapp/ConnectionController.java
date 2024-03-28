package com.nortexdev.quizapp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ConnectionController {
	public static final int PORT = 6677;
	public static final BlockingQueue<Socket> SOCKET_QUEUE = new LinkedBlockingQueue<>();

	private static ServerSocket serverSocket;

	private final MainController viewController;
	private final GameController gameController;

	public ConnectionController(MainController viewController, GameController gameController) {
		this.viewController = viewController;
		this.gameController = gameController;
		System.out.println("ConnectionController created.");
		new Thread(this::createProducer).start();
		new Thread(this::createConsumer).start();
	}

	public void createProducer() {
		System.out.println("ConnectionController.connect()");
		try {
			serverSocket = new ServerSocket(ConnectionController.PORT);
			System.out.println("Listening at " + ConnectionController.PORT + ".");
			while (true) {
				Socket socket = serverSocket.accept();
				String clientSocketIP = socket.getInetAddress().toString();
				int clientSocketPort = socket.getPort();
				System.out.println("[IP: " + clientSocketIP + ", Port: " + clientSocketPort + "] " + "Putting into queue");
				SOCKET_QUEUE.put(socket);
			}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			System.out.println("ServerSocket creation failed.");
		}
	}

	public void createConsumer() {
		while (true) {
			System.out.println("ConnectionController.handleConnections()");
			try {
				Socket socket = SOCKET_QUEUE.take();
				handleMessage(socket);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void handleMessage(Socket socket) {
		System.out.println("ConnectionController.handleMessage(): Handling message");
		try {
			ObjectInputStream dataIn = new ObjectInputStream(socket.getInputStream());
			ClientAnswer answer = (ClientAnswer) dataIn.readObject();
			System.out.println(answer);
			gameController.attempt(answer, socket.getInetAddress());
			dataIn.close();
		} catch (IOException | ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	public static void clearQueue() {
		SOCKET_QUEUE.clear();
	}
}
