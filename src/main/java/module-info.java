module program {
    requires javafx.controls;
    requires javafx.fxml;

    opens program.controllers to javafx.fxml;
    exports program;

    exports program.controllers;
    opens program to javafx.graphics;
}