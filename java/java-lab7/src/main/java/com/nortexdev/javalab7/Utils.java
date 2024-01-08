package com.nortexdev.javalab7;

import javafx.scene.control.Alert;

public class Utils {
	public static void showSuccessAlert(String text) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Success");
		alert.setHeaderText(null);
		alert.setContentText(text);
		alert.showAndWait();
	}

	public static void showErrorAlert(String text) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText(null);
		alert.setContentText(text);
		alert.showAndWait();
	}
}
