package com.nortexdev.javalab7;

import java.util.ArrayList;

public class Patient {
	private final int id;
	private final String firstName;
	private final String lastName;
	private final int age;

	public Patient(int id, String firstName, String lastName, int age) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
	}

	public static void printAll(ArrayList<Patient> patients, String sortMode) {
		if (sortMode.equals("Age")) {
			patients.sort((o1, o2) -> o1.age - o2.age);
		} else if (sortMode.equals("Last name")) {
			patients.sort((o1, o2) -> o1.lastName.compareTo(o2.lastName));
		} else if (sortMode.equals("First name")) {
			patients.sort((o1, o2) -> o1.firstName.compareTo(o2.firstName));
		} else if (sortMode.equals("Adding Order")) {
			patients.sort((o1, o2) -> o1.id - o2.id);
		} else {
			Utils.showErrorAlert("Unknown sort mode.");
			return;
		}

		if (patients.isEmpty()) {
			Utils.showErrorAlert("There are no patients.");
			return;
		}
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (Patient patient : patients) {
			sb.append("{");
			sb.append("\"firstName\": \"").append(patient.firstName).append("\", ");
			sb.append("\"lastName\": \"").append(patient.lastName).append("\", ");
			sb.append("\"age\": ").append(patient.age);
			sb.append("}, ");
		}
		sb.delete(sb.length() - 2, sb.length());
		sb.append("]");
		System.out.println(sb);
		Utils.showSuccessAlert(sb.toString());
	}
}
