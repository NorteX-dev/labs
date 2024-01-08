package com.nortexdev.javalab6;

import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;

// Klasa odpowiadająca za wyświetlanie dialogów odczytu i zapisu.
public class DialogUtils {
	// Zwraca instancje FileChooser.
	private static FileChooser createFileChooser(String title) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle(title);
		fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("Text Files", "*.txt"),
				new FileChooser.ExtensionFilter("All Files", "*.*")
		);
		return fileChooser;
	}

	// Pokazuje dialog zapisu.
	public static String promptForSavePath(Window window) {
		FileChooser fileChooser = DialogUtils.createFileChooser("Save file");
		File file = fileChooser.showSaveDialog(window);
		return file == null ? null : file.getAbsolutePath();
	}

	// Pokazuje dialog otwarcia pliku.
	public static String promptForOpenPath(Window window) {
		FileChooser fileChooser = DialogUtils.createFileChooser("Open file");
		File file = fileChooser.showOpenDialog(window);
		return file == null ? null : file.getAbsolutePath();
	}
}
