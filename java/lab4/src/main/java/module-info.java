module com.nortexdev.lab4 {
	requires javafx.controls;
	requires javafx.fxml;


	opens com.nortexdev.lab4 to javafx.fxml;
	exports com.nortexdev.lab4;
	exports com.nortexdev.lab4.classes;
	opens com.nortexdev.lab4.classes to javafx.fxml;
	exports com.nortexdev.lab4.enums;
	opens com.nortexdev.lab4.enums to javafx.fxml;
	exports com.nortexdev.lab4.utils;
	opens com.nortexdev.lab4.utils to javafx.fxml;
}
