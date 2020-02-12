package program.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

import java.io.File;

public class MainAppController {

   /* @FXML
    private Label label;

    public void initialize() {
        // TODO
        label.setText("heiiii");
    }*/
    @FXML
    private MenuItem apneFil;
    @FXML
    private MenuItem lagreFil;

    public void TrykketLagreFil(ActionEvent actionEvent) {


    }


    public void TrykketApneFil(ActionEvent actionEvent) {
        FileChooser filValg = new FileChooser();
        filValg.getExtensionFilters().add(new ExtensionFilter("Text files", "*.txt"));
        filValg.showOpenDialog(null);
    }
}
