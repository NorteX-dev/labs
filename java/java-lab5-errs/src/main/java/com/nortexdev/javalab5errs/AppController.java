package com.nortexdev.javalab5errs;

import com.nortexdev.javalab5errs.util.Validator;
import com.nortexdev.javalab5errs.exceptions.EmailValidationException;
import com.nortexdev.javalab5errs.exceptions.PasswordValidationException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class AppController {
	@FXML
	public TextField emailField;
	@FXML
	public PasswordField passwordField;
	@FXML
	public Button submitBtn;

	private static void showAlert(AlertType error, String title, String err) {
		Alert alert = new Alert(error);
		alert.setTitle(title);
		alert.setHeaderText(title);
		alert.setContentText(err);
		alert.showAndWait();
	}

	@FXML
	protected void onSubmitBtnClick() {
		try {
			String email = emailField.getText();
			String password = passwordField.getText();
			Validator.validateEmailOrThrow(email);
			Validator.validatePasswordOrThrow(password);
			showAlert(AlertType.INFORMATION, "Success", "Registration successful.");
		} catch (EmailValidationException err) {
			showAlert(AlertType.ERROR, "Error while validating email", err.getMessage());
		} catch (PasswordValidationException err) {
			showAlert(AlertType.ERROR, "Error while validating password", err.getMessage());
		}
	}
}
