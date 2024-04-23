package com.nortexdev.lab4;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.lang.reflect.*;

public class HelloController {
	// UI properties
	@FXML
	private TextArea logBox;

	@FXML
	private TextField classPath;

	@FXML
	private VBox propertiesContainer;

	// Regular properties
	private Object currentObject;

	// UI methods
	@FXML
	private void initialize() {
		logBox.setEditable(false);
		logBox.setPromptText("Empty...");
		classPath.setPromptText("Enter a class path...");
		// TODO: DEV ONLY
		classPath.setText("com.nortexdev.lab4.Song");
	}

	@FXML
	public void onCreateObject() {
		String classPath = this.classPath.getText();

		if (classPath.isEmpty()) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Class path is empty");
			alert.setContentText("Please enter a class path");
			alert.showAndWait();
			return;
		}

		this.clearProperties();

		try {
			Class<?> clazz = Class.forName(classPath);
			Object object = clazz.newInstance();
			Field[] fields = object.getClass().getDeclaredFields();

			currentObject = object;

			for (Field field : fields) {
				String name = "get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
				Object value = currentObject.getClass().getMethod(name).invoke(currentObject);

				createPropertyField(field.getName(), String.valueOf(value), field.getName().contains("text"));
			}
			appendLog("Created " + currentObject.toString());
		} catch (ClassNotFoundException | InstantiationException e) {
			e.printStackTrace();
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Error while creating class");
			alert.setContentText("Please enter a valid class path");
			alert.showAndWait();
			clearProperties();
		} catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
			clearProperties();
			throw new RuntimeException(e);
		}
	}

	private void clearProperties() {
		propertiesContainer.getChildren().clear();
	}

	@FXML
	public void onSaveChanges() {
		if (currentObject == null) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("No object created");
			alert.setContentText("Please create an object first");
			alert.showAndWait();
			return;
		}

		Field[] fields = currentObject.getClass().getDeclaredFields();
		int i = 0;

		for (Node node : propertiesContainer.getChildren()) {
			HBox hbox = (HBox) node;
			Node firstNodeOfHBox = hbox.getChildren().getFirst();

			Class<?> fieldType = fields[i].getType();
			if (firstNodeOfHBox instanceof TextField textField) {
				String name = fields[i].getName();
				String value = textField.getText();

				try {
					String setterMethodName = "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
					Method method = currentObject.getClass().getMethod(setterMethodName, fieldType);

					// TYPE VALIDATION
					if (fieldType.equals(String.class)) {
						method.invoke(currentObject, value);
					} else if (fieldType.equals(Integer.class) || fieldType.equals(int.class)) {
						if (validateInt(value)) method.invoke(currentObject, Integer.parseInt(value));
						else {
							appendLog("Invalid value for " + name + " (int)");
							i++;
							continue;
						}
					} else if (fieldType.equals(Double.class) || fieldType.equals(double.class)) {
						if (validateDouble(value)) method.invoke(currentObject, Double.parseDouble(value));
						else {
							appendLog("Invalid value for " + name + " (double)");
							i++;
							continue;
						}
					} else if (fieldType.equals(Boolean.class) || fieldType.equals(boolean.class)) {
						if (value.equals("true") || value.equals("false"))
							method.invoke(currentObject, Boolean.parseBoolean(value));
						else {
							appendLog("Invalid value for " + name + " (boolean)");
							i++;
							continue;
						}
					} else if (fieldType.equals(Character.class) || fieldType.equals(char.class)) {
						if (value.length() == 1) method.invoke(currentObject, value.charAt(0));
						else {
							appendLog("Invalid value for " + name + " (char)");
							i++;
							continue;
						}
					} else if (fieldType.equals(Long.class) || fieldType.equals(long.class)) {
						if (validateLong(value)) method.invoke(currentObject, Long.parseLong(value));
						else {
							appendLog("Invalid value for " + name + " (long)");
							i++;
							continue;
						}
					} else if (fieldType.equals(Float.class) || fieldType.equals(float.class)) {
						if (validateFloat(value)) method.invoke(currentObject, Float.parseFloat(value));
						else {
							appendLog("Invalid value for " + name + " (float)");
							i++;
							continue;
						}
					} else if (fieldType.equals(Short.class) || fieldType.equals(short.class)) {
						if (validateShort(value)) method.invoke(currentObject, Short.parseShort(value));
						else {
							appendLog("Invalid value for " + name + " (short)");
							i++;
							continue;
						}
					} else if (fieldType.equals(Byte.class) || fieldType.equals(byte.class)) {
						if (validateBytes(value)) method.invoke(currentObject, Byte.parseByte(value));
						else {
							appendLog("Invalid value for " + name + " (byte)");
							i++;
							continue;
						}
					} else {
						appendLog("Invalid value for " + name + " (String)");
						i++;
						continue;
					}
					// END TYPE VALIDATION
				} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
					e.printStackTrace();
				}
				i++;
			} else if (firstNodeOfHBox instanceof TextArea textArea) {
				String name = fields[i].getName();
				String value = textArea.getText();

				try {
					String setterMethodName = "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
					Method method = currentObject.getClass().getMethod(setterMethodName, fieldType);
					// Text area will only ever handle string types.
					if (fieldType.equals(String.class)) {
						method.invoke(currentObject, value);
					} else {
						appendLog("Invalid value for " + name + " (String)");
						i++;
						continue;
					}
				} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
					e.printStackTrace();
				}
				i++;
			}
		}
		appendLog("Set " + currentObject.toString());
	}


	private boolean validateInt(String value) {
		return value.matches("\\d+");
	}

	private boolean validateDouble(String value) {
		return value.matches("\\d+(\\.\\d+)?");
	}

	private boolean validateShort(String value) {
		return value.matches("\\d+");
	}

	private boolean validateLong(String value) {
		return value.matches("\\d+");
	}

	private boolean validateFloat(String value) {
		return value.matches("\\d+(\\.\\d+)?");
	}

	private boolean validateBytes(String value) {
		return value.matches("[01]");
	}

	private void appendLog(String log) {
		logBox.appendText(log + "\n");
	}

	private void createPropertyField(String name, String defaultValue, boolean isTextArea) {
		HBox hBox = new HBox();
		hBox.setPadding(new Insets(5));
		if (!isTextArea) {
			// Text field
			TextField textField = new TextField();
			textField.setText(defaultValue);
			textField.setPrefWidth(propertiesContainer.getWidth() / 2);

			Label label = new Label("<- " + name);
			label.setPadding(new Insets(0, 0, 0, 5));

			hBox.getChildren().add(textField);
			hBox.getChildren().add(label);
		} else {
			// Text area
			TextArea textArea = new TextArea();
			textArea.setText(defaultValue);
			textArea.setPrefWidth(propertiesContainer.getWidth() / 2);

			Label label = new Label("<- " + name);
			label.setPadding(new Insets(0, 0, 0, 5));

			hBox.getChildren().add(textArea);
			hBox.getChildren().add(label);
		}
		propertiesContainer.getChildren().add(hBox);
	}

}
