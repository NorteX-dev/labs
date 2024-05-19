package com.nortexdev.lab4;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionManager {
	private final HelloController controller;
	private Object currentObject;

	public ReflectionManager(HelloController controller) {
		this.controller = controller;
	}

	private static void showErrorAlert(String headerText, String contextText) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText(headerText);
		alert.setContentText(contextText);
		alert.showAndWait();
	}

	public void createObject() {
		String classPath = controller.getClassPath().getText();

		if (classPath.isEmpty()) {
			showErrorAlert("Class path is empty", "Please enter a class path");
			return;
		}

		controller.clearPropertyFields();

		try {
			Class<?> clazz = Class.forName(classPath);
			Object object = clazz.newInstance();
			Field[] fields = object.getClass().getDeclaredFields();

			currentObject = object;

			for (Field field : fields) {
				String methodName = "get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
				Object value = currentObject.getClass().getMethod(methodName).invoke(currentObject);

				PropertyFieldType fieldType = field.getName().contains("text") ? PropertyFieldType.TextArea : PropertyFieldType.TextField;
				controller.createPropertyField(field.getName(), String.valueOf(value), fieldType);
			}
			controller.appendLog("Created " + currentObject.toString());
		} catch (ClassNotFoundException | InstantiationException e) {
			showErrorAlert("Error while creating class", "Please enter a valid class path");
			controller.clearPropertyFields();
		} catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
			controller.clearPropertyFields();
			throw new RuntimeException(e);
		}
	}

	public void updateObject() {
		if (currentObject == null) {
			showErrorAlert("No object created", "Please create an object first");
			return;
		}

		Field[] fields = currentObject.getClass().getDeclaredFields();
		int i = 0;

		for (Node node : controller.getPropertyFields()) {
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
						if (Validators.validateInt(value)) {
							method.invoke(currentObject, Integer.parseInt(value));
						} else {
							controller.appendLog("Invalid value for " + name + " (int)");
							i++;
							continue;
						}
					} else if (fieldType.equals(Double.class) || fieldType.equals(double.class)) {
						if (Validators.validateDouble(value)) {
							method.invoke(currentObject, Double.parseDouble(value));
						} else {
							controller.appendLog("Invalid value for " + name + " (double)");
							i++;
							continue;
						}
					} else if (fieldType.equals(Boolean.class) || fieldType.equals(boolean.class)) {
						if (value.equals("true") || value.equals("false")) {

							method.invoke(currentObject, Boolean.parseBoolean(value));
						} else {
							controller.appendLog("Invalid value for " + name + " (boolean)");
							i++;
							continue;
						}
					} else if (fieldType.equals(Character.class) || fieldType.equals(char.class)) {
						if (value.length() == 1) {
							method.invoke(currentObject, value.charAt(0));
						} else {
							controller.appendLog("Invalid value for " + name + " (char)");
							i++;
							continue;
						}
					} else if (fieldType.equals(Long.class) || fieldType.equals(long.class)) {
						if (Validators.validateLong(value)) {
							method.invoke(currentObject, Long.parseLong(value));
						} else {
							controller.appendLog("Invalid value for " + name + " (long)");
							i++;
							continue;
						}
					} else if (fieldType.equals(Float.class) || fieldType.equals(float.class)) {
						if (Validators.validateFloat(value)) {
							method.invoke(currentObject, Float.parseFloat(value));
						} else {
							controller.appendLog("Invalid value for " + name + " (float)");
							i++;
							continue;
						}
					} else if (fieldType.equals(Short.class) || fieldType.equals(short.class)) {
						if (Validators.validateShort(value)) {
							method.invoke(currentObject, Short.parseShort(value));
						} else {
							controller.appendLog("Invalid value for " + name + " (short)");
							i++;
							continue;
						}
					} else if (fieldType.equals(Byte.class) || fieldType.equals(byte.class)) {
						if (Validators.validateBytes(value)) {
							method.invoke(currentObject, Byte.parseByte(value));
						} else {
							controller.appendLog("Invalid value for " + name + " (byte)");
							i++;
							continue;
						}
					} else {
						controller.appendLog("Invalid value for " + name + " (unknown type)");
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
						controller.appendLog("Invalid value for " + name + " (String)");
						i++;
						continue;
					}
				} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
					e.printStackTrace();
				}
				i++;
			}
		}
		controller.appendLog("Set " + currentObject.toString());
	}
}
