module com.nortexdev.lab8 {
	requires javafx.controls;
	requires javafx.fxml;


	opens com.nortexdev.lab8 to javafx.fxml;
	exports com.nortexdev.lab8;
}
