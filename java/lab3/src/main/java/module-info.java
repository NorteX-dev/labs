module com.nortexdev.quizapp {
	requires javafx.controls;
	requires javafx.fxml;


	opens com.nortexdev.quizapp to javafx.fxml;
	exports com.nortexdev.quizapp;
}
