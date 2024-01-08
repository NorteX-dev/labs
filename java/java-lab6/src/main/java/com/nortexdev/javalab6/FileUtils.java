package com.nortexdev.javalab6;

import java.io.*;

public class FileUtils {
	// Metoda zapisu do pliku
	public static void write(String path, String content) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(path));
		writer.write(content);
		writer.close();
	}

	// Metoda odczytu z pliku
	public static String read(String path) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(path));
		// Z racji, że reader zwraca każdą linijkę osobno, należy
		// je połączyć w jeden String. Robię to za pomocą StringBuilder'a.
		StringBuilder stringBuilder = new StringBuilder();
		String line;
		while ((line = reader.readLine()) != null) {
			// Dodaję do string builder'a linię.
			stringBuilder.append(line).append("\n");
		}
		reader.close();
		// Zwracam wynik StringBuilder'a.
		return stringBuilder.toString();
	}
}
