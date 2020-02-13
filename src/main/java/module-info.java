
module program {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.junit.jupiter.api;

    opens program.controllers to javafx.fxml;
    exports program;

    exports program.controllers;
    opens program to javafx.graphics;
}