package com.nortexdev.lab8;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.nio.file.Files;
import java.nio.file.Paths;

public class HelloController {
	private static int IMAGE_INDEX = 1;
	private static int totalImageCount;

	@FXML
	private Label statusText;

	@FXML
	private ImageView imageView;

	@FXML
	private void initialize() {
		try {
			// Zlicza pliki w `/images` i ustawia TOTAL_IMAGE_COUNT na tę wartość.
			totalImageCount = (int) Files.list(Paths.get(getClass().getResource("/com/nortexdev/lab8/images/").toURI()))
				.count();
		} catch (Exception e) {
			e.printStackTrace();
		}

		updatePicture();
	}

	@FXML
	protected void previousButtonTrigger() {
		// Przycisk cofnięcia o 1 zdjęcie.
		IMAGE_INDEX--;
		if (IMAGE_INDEX <= 0) {
			IMAGE_INDEX = totalImageCount;
		}
		updatePicture();
	}


	@FXML
	protected void nextButtonTrigger() {
		// Przycisk przejścia do następnego zdjęcia.
		IMAGE_INDEX++;
		if (IMAGE_INDEX > totalImageCount) {
			IMAGE_INDEX = 1;
		}
		updatePicture();
	}

	private void updatePicture() {
		// Aktualizuje wyświetlany obrazek.
		String path = "/com/nortexdev/lab8/images/" + IMAGE_INDEX + ".jpg";
		Image image = new Image(getClass().getResourceAsStream(path));
		imageView.setImage(image);
		updateStatusText();

	}

	private void updateStatusText() {
		// Aktualizuje tekst statusu.
		statusText.setText("Zdjęcie %d z %d.".formatted(IMAGE_INDEX, totalImageCount));
	}
}
