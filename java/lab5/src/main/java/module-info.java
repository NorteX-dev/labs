module com.nortexdev.lab5 {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.desktop;


	opens com.nortexdev.lab5 to javafx.fxml;
	exports com.nortexdev.lab5;
	exports com.nortexdev.lab5.annotations;
	opens com.nortexdev.lab5.annotations to javafx.fxml;
}
