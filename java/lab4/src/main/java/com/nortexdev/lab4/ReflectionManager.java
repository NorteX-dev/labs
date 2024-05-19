package com.nortexdev.lab4;

import com.nortexdev.lab4.enums.PropertyFieldType;
import com.nortexdev.lab4.utils.Validators;
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
			Object object = clazz.getDeclaredConstructor().newInstance();
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
			controller.clearPropertyFields();
			showErrorAlert("Error while creating class", "Please enter a valid class path");
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
			String name = fields[i].getName();
			String value = null;

			if (firstNodeOfHBox instanceof TextField textField) {
				value = textField.getText();
			} else if (firstNodeOfHBox instanceof TextArea textArea) {
				value = textArea.getText();
			}
			//	                       set    T                                    est
			String setterMethodName = "set" + name.substring(0, 1).toUpperCase() + name.substring(1);

			try {
				Method method = currentObject.getClass().getMethod(setterMethodName, fieldType);

				try {
					trySetProperty(fieldType, method, name, value);
				} catch (InvalidValueException | InvocationTargetException | IllegalAccessException err) {
					controller.appendLog(err.getMessage());
					i++;
					continue;
				}
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
			i++;
		}

		controller.appendLog("Set " + currentObject.toString());
	}

	public void trySetProperty(Class<?> fieldType, Method method, String name, String stringifiedValue) throws InvalidValueException, InvocationTargetException, IllegalAccessException {
		if (fieldType.equals(String.class)) {
			method.invoke(currentObject, stringifiedValue);
		} else if (fieldType.equals(Integer.class) || fieldType.equals(int.class)) {
			if (Validators.validateInt(stringifiedValue)) {
				method.invoke(currentObject, Integer.parseInt(stringifiedValue));
			} else {
				throw new InvalidValueException("Invalid value for " + name + " (int)");
			}
		} else if (fieldType.equals(Double.class) || fieldType.equals(double.class)) {
			if (Validators.validateDouble(stringifiedValue)) {
				method.invoke(currentObject, Double.parseDouble(stringifiedValue));
			} else {
				throw new InvalidValueException("Invalid value for " + name + " (double)");
			}
		} else if (fieldType.equals(Boolean.class) || fieldType.equals(boolean.class)) {
			if (stringifiedValue.equals("true") || stringifiedValue.equals("false")) {
				method.invoke(currentObject, Boolean.parseBoolean(stringifiedValue));
			} else {
				throw new InvalidValueException("Invalid value for " + name + " (boolean)");
			}
		} else if (fieldType.equals(Character.class) || fieldType.equals(char.class)) {
			if (stringifiedValue.length() == 1) {
				method.invoke(currentObject, stringifiedValue.charAt(0));
			} else {
				throw new InvalidValueException("Invalid value for " + name + " (char)");
			}
		} else if (fieldType.equals(Long.class) || fieldType.equals(long.class)) {
			if (Validators.validateLong(stringifiedValue)) {
				method.invoke(currentObject, Long.parseLong(stringifiedValue));
			} else {
				throw new InvalidValueException("Invalid value for " + name + " (long)");
			}
		} else if (fieldType.equals(Float.class) || fieldType.equals(float.class)) {
			if (Validators.validateFloat(stringifiedValue)) {
				method.invoke(currentObject, Float.parseFloat(stringifiedValue));
			} else {
				throw new InvalidValueException("Invalid value for " + name + " (float)");
			}
		} else if (fieldType.equals(Short.class) || fieldType.equals(short.class)) {
			if (Validators.validateShort(stringifiedValue)) {
				method.invoke(currentObject, Short.parseShort(stringifiedValue));
			} else {
				throw new InvalidValueException("Invalid value for " + name + " (short)");
			}
		} else if (fieldType.equals(Byte.class) || fieldType.equals(byte.class)) {
			if (Validators.validateBytes(stringifiedValue)) {
				method.invoke(currentObject, Byte.parseByte(stringifiedValue));
			} else {
				throw new InvalidValueException("Invalid value for " + name + " (byte)");
			}
		}
	}
}
