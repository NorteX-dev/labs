package com.nortexdev.javalab7;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.util.ArrayList;


public class AppController {
	public static ArrayList<Patient> patients = new ArrayList<>();

	@FXML
	public TextField imie;
	@FXML
	public TextField nazwisko;
	@FXML
	public TextField wiek;
	@FXML
	public ComboBox sortingBox;

	public void initialize() {
		sortingBox.getItems().addAll("First name", "Last name", "Age", "Adding Order");
		sortingBox.setValue("Adding Order");
	}

	@FXML
	public void onAddClick() {
		String firstName = imie.getText();
		if (!firstName.matches("[A-ZĄĆĘŁŃÓŚŹŻ][a-ząćęłńóśźż]*")) {
			Utils.showErrorAlert("First name must start with capital letter and contain only letters.");
			return;
		}
		String lastName = nazwisko.getText();
		if (!lastName.matches("[A-ZĄĆĘŁŃÓŚŹŻ][a-ząćęłńóśźż]*")) {
			Utils.showErrorAlert("Last name must start with capital letter and contain only letters.");
			return;
		}
		if (!wiek.getText().matches("[0-9]*")) {
			Utils.showErrorAlert("Age must be a number.");
			return;
		}
		int age = Integer.parseInt(wiek.getText());
		if (age < 0 || age > 150) {
			Utils.showErrorAlert("Age must be between 0 and 150.");
			return;
		}

		Patient patient = new Patient(patients.size() + 1, firstName, lastName, age);
		patients.add(patient);

		Utils.showSuccessAlert("Added new patient.");
	}

	@FXML
	public void onPrintClick() {
		Patient.printAll(patients, sortingBox.getValue().toString());
	}
}
