package program.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.converter.IntegerStringConverter;
import program.Exceptions.InvalidAgeException;
import program.Person;
import program.Validering;

import java.io.*;
import java.text.Format;
import java.text.ParsePosition;
import java.util.EventListener;
import java.util.function.UnaryOperator;
import java.util.logging.Level;

public class MainAppController {
    @FXML
    private TextField navn;
   @FXML
    private TextField alder;
   @FXML
    private TextField fodselsdag;
    @FXML
    private TextField fodselsmnd;
    @FXML
    private TextField fodselsar;
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
    private MenuItem validerInput;

    @FXML
    public void initialize(){
        alder.textProperty().addListener((observableValue, oldValue, newValue) -> {
            System.out.println(observableValue + ", " + oldValue + ", " + newValue);
            if(newValue.equals("") || newValue.matches("[1-9][0-9]")) alder.setText("0");
        });
    }

    @FXML
    public void TrykketLagreFil(ActionEvent actionEvent) throws FileNotFoundException, IOException, ClassNotFoundException {
        File nyFil = new File("test.txt");
        /*try{
            PrintWriter output = new PrintWriter(nyFil);
            output.println("Zandra Max");
            output.println(20);
            output.close();
        }catch (IOException ex){
            System.out.println("Noe gikk feil");
        }*/
        //Serialisering
        String hei = "heihei";
        FileOutputStream fileOutput = new FileOutputStream(nyFil);
        ObjectOutputStream output = new ObjectOutputStream(fileOutput);
        output.writeObject(hei);
        output.close();
        fileOutput.close();
/*
        //de-serialisering
        FileInputStream fileInput = new FileInputStream(nyFil);
        ObjectInputStream input = new ObjectInputStream(fileInput);
        String hei2 = "heihallo";
        input.readObject();
*/
    }

    @FXML
    public void TrykketApneFil(ActionEvent actionEvent) {
        //vet ikke om dette fungerer, lurer på om vi kanskje skal bruke scanner()?
        FileChooser filValg = new FileChooser();
        filValg.setTitle("Velg én eller flere *.txt-filer");
        filValg.getExtensionFilters().add(new ExtensionFilter("Text files", "*.txt"));
        filValg.showOpenDialog(null);
    }

    @FXML
    public void TrykkValiderInput(ActionEvent actionEvent) throws InvalidAgeException {
        Person person = new Person(navn.getText(), Integer.parseInt(alder.getText()),
                Integer.parseInt(fodselsdag.getText()), Integer.parseInt(fodselsmnd.getText()),
                Integer.parseInt(fodselsar.getText()), epost.getText(), nummer.getText());

        String feilmld = Validering.validerPerson(person);
        Alert alert = new Alert(Alert.AlertType.WARNING, feilmld);
        alert.show();

    }
}
