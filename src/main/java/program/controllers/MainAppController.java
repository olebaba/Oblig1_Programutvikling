package program.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Window;
import program.Validering;

import java.io.*;

public class MainAppController {
    @FXML
    private TextField navn;
   @FXML
    private TextField alder;
   @FXML
    private TextField fodselsdato;
   @FXML
    private TextField epost;
   @FXML
    private TextField nummer;


    @FXML
    private AnchorPane anchorpane;
    @FXML
    private MenuItem apneFil;
    @FXML
    private MenuItem lagreFil;
    @FXML
    public void TrykketLagreFil(ActionEvent actionEvent) throws FileNotFoundException, IOException, ClassNotFoundException {
        //Validering.validerAlder(Integer.parseInt(alder.getText()));
            File nyFil = new File("test.txt");
            try {
                PrintWriter output = new PrintWriter(nyFil);
                output.println(navn.getText());
                output.println(alder.getText());
                output.close();
            } catch (IOException ex) {
                System.out.println("Noe gikk feil");
            }
    }
    @FXML
    public void TrykketApneFil(ActionEvent actionEvent) {
        //vet ikke om dette fungerer, lurer på om vi kanskje skal bruke scanner()?
        FileChooser filValg = new FileChooser();
        filValg.setTitle("Velg én eller flere *.txt-filer");
        filValg.getExtensionFilters().add(new ExtensionFilter("Text files", "*.txt"));
        filValg.showOpenDialog(null);
    }
}
