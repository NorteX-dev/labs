package com.nortexdev.quizapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
	@Override
	public void start(Stage stage) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main-view.fxml"));
		Scene scene = new Scene(fxmlLoader.load(), 320, 240);
		stage.setTitle("Quiz - Klient");
		stage.setScene(scene);
		stage.setWidth(450);
		stage.setHeight(250);
		stage.setResizable(false);
		stage.show();
	}

	public static void main(String[] args) {
		launch();
	}
}
