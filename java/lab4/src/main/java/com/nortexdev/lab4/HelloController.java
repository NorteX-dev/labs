package com.nortexdev.lab4;

import com.nortexdev.lab4.enums.PropertyFieldType;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class HelloController {
	@FXML
	private TextArea logBox;

	@FXML
	private TextField classPath;

	@FXML
	private VBox propertiesContainer;

	private ReflectionManager reflectionManager;

	public void appendLog(String log) {
		logBox.appendText(log + "\n");
	}

	@FXML
	private void initialize() {
		logBox.setEditable(false);
		logBox.setPromptText("Empty...");
		classPath.setPromptText("Enter a class path...");
		reflectionManager = new ReflectionManager(this);
	}

	@FXML
	public void onCreateObject() {
		reflectionManager.createObject();
	}

	@FXML
	public void onSaveChanges() {
		reflectionManager.updateObject();
	}

	public TextField getClassPath() {
		return classPath;
	}

	public ObservableList<Node> getPropertyFields() {
		return propertiesContainer.getChildren();
	}

	public void clearPropertyFields() {
		getPropertyFields().clear();
	}

	public void createPropertyField(String name, String defaultValue, PropertyFieldType fieldType) {
		HBox hBox = new HBox();
		hBox.setPadding(new Insets(5));
		if (fieldType.equals(PropertyFieldType.TextField)) {
			// Text field
			TextField textField = new TextField();
			textField.setText(defaultValue);
			textField.setPrefWidth(propertiesContainer.getWidth() / 2);

			Label label = new Label("<- " + name);
			label.setPadding(new Insets(0, 0, 0, 5));

			hBox.getChildren().add(textField);
			hBox.getChildren().add(label);
		} else if (fieldType.equals(PropertyFieldType.TextArea)) {
			// Text area
			TextArea textArea = new TextArea();
			textArea.setText(defaultValue);
			textArea.setPrefWidth(propertiesContainer.getWidth() / 2);

			Label label = new Label("<- " + name);
			label.setPadding(new Insets(0, 0, 0, 5));

			hBox.getChildren().add(textArea);
			hBox.getChildren().add(label);
		} else {
			throw new RuntimeException("Called createPropertyField() with unknown field type");
		}
		propertiesContainer.getChildren().add(hBox);
	}
}
