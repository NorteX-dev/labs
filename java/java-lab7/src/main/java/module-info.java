module com.nortexdev.javalab7 {
	requires javafx.controls;
	requires javafx.fxml;


	opens com.nortexdev.javalab7 to javafx.fxml;
	exports com.nortexdev.javalab7;
}
