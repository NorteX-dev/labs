module com.nortexdev.lab4 {
	requires javafx.controls;
	requires javafx.fxml;


	opens com.nortexdev.lab4 to javafx.fxml;
	exports com.nortexdev.lab4;
}
