module com.nortexdev.javalab6 {
	requires javafx.controls;
	requires javafx.fxml;


	opens com.nortexdev.javalab6 to javafx.fxml;
	exports com.nortexdev.javalab6;
}
