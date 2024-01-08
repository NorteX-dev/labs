package com.nortexdev.javalab6;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;

import java.io.*;

public class HelloController {
	// Odn. do pola tekstowego.
	@FXML
	private TextArea textArea;

	// Metoda pomocnicza do otwierania alert'ów.
	private static void showAlert(AlertType type, String title, String content) {
		Alert alert = new Alert(type);
		alert.setTitle(title);
		alert.setContentText(content);
		alert.showAndWait();
	}

	// Metoda wywoływana po naciśnięciu przycisku "Zapis do pliku".
	@FXML
	protected void onSave() {
		// Jeżeli pusty, wyświetl błąd.
		if (textArea.getText().isEmpty()) {
			showAlert(AlertType.ERROR, "Error saving", "Text area is empty.");
			return;
		}

		// Otwiera dialog otwarcia pliku
		String path = DialogUtils.promptForSavePath(textArea.getScene().getWindow());
//		String path = "tekst.txt";
		// Jeżeli dialog został anulowany przez użytkownika path będzie null.
		if (path == null) return;

		try {
			// Zapisz do pliku
			FileUtils.write(path, textArea.getText());
			showAlert(AlertType.INFORMATION, "File saved", "Saved file to: " + path);
		} catch (IOException e) {
			// IOException - błąd przy zapisywaniu pliku.
			showAlert(AlertType.ERROR, "Error writing to file", "Couldn't write to file.");
		}
	}

	// Metoda wywoływana po naciśnięciu przycisku "Odczyt z pliku".
	@FXML
	protected void onOpen() {
		// Otwiera dialog otwarcia pliku
		String path = DialogUtils.promptForOpenPath(textArea.getScene().getWindow());
//		String path = "tekst.txt";
		if (path == null) return;

		try {
			// Metoda read() odczytuje cały plik i zwraca String.
			String contents = FileUtils.read(path);
			textArea.setText(contents);
			showAlert(AlertType.INFORMATION, "File loaded", "Loaded file successfully.");
		} catch (IOException e) {
			showAlert(AlertType.ERROR, "Error opening file", "Couldn't open the file.");
		}
	}
}
