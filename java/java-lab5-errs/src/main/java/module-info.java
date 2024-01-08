module com.nortexdev.javalab5errs {
	requires javafx.controls;
	requires javafx.fxml;


	opens com.nortexdev.javalab5errs to javafx.fxml;
	exports com.nortexdev.javalab5errs;
	exports com.nortexdev.javalab5errs.exceptions;
	opens com.nortexdev.javalab5errs.exceptions to javafx.fxml;
	exports com.nortexdev.javalab5errs.util;
	opens com.nortexdev.javalab5errs.util to javafx.fxml;
}
