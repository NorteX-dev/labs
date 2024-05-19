package com.nortexdev.lab5;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class UIController {
	@FXML
	private TextField textField;

	@FXML
	private ImageView iconImg;

	@FXML
	private void onType() {
		ValidatedClass validatedClass = new ValidatedClass();
		Tooltip tooltip = new Tooltip();
		try {
			validatedClass.setField(textField.getText());
			tooltip.hide();
			Tooltip.uninstall(iconImg, tooltip);
			setIcon(Icon.Success);
		} catch (Exception e) {
			tooltip.setText(e.getMessage());
			Tooltip.install(iconImg, tooltip);
			setIcon(Icon.Error);
		}
	}

	private void setIcon(Icon iconType) {
		if (iconType.equals(Icon.Success)) {
			iconImg.setImage(new Image(getClass().getResourceAsStream("success.png")));
		} else {
			iconImg.setImage(new Image(getClass().getResourceAsStream("error.png")));
		}
	}
}
