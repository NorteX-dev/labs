package com.nortexdev.quizapp;


import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ConnectionController {
	public static final String SERVER_HOST = "127.0.0.1";
	public static final int SERVER_PORT = 6677;
	private Socket socket = null;

	public void send(ClientAnswer clientAnswer) {
		new Thread(() -> {
			try {
				socket = new Socket();
				socket.connect(new InetSocketAddress(SERVER_HOST, SERVER_PORT));
				ObjectOutputStream dataOut = new ObjectOutputStream(socket.getOutputStream());
				dataOut.writeObject(clientAnswer);
				dataOut.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}).start();
	}
}
