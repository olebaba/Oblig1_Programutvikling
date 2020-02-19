package program.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
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
   private DatePicker dato;
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
        //unngar error i validering:
        giveValue(alder);
        giveValue(nummer);

        //kun mulig å sette alder til 0-99
        alder.textProperty().addListener((observableValue, oldValue, newValue) -> {
            if(!newValue.matches("\\d|\\d{2}|^$")) alder.setText(oldValue);
        });

        dato.focusedProperty().addListener((observableValue, aBoolean, t1) -> {
            if(!t1) System.out.println(dato.getValue());
        });

    }

    private void giveValue(TextField textField){ //sjekker om textField har mista fokus
        textField.focusedProperty().addListener((observableValue, aBoolean, t1) -> {
            Focus(t1, textField);
        });
    }

    private void Focus(boolean value, TextField textField){ //legger inn 0 i textField om den er tom og har mista fokus
        if(!value && textField.getText().equals("")) textField.setText("0");
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
        Alert alert;

        try {
            Person person = new Person(navn.getText(), Integer.parseInt(alder.getText()),
                    dato.getValue().getDayOfMonth(), dato.getValue().getMonthValue(),
                    dato.getValue().getYear(), epost.getText(), nummer.getText());

            String feilmld = Validering.validerPerson(person);

            if(!feilmld.isEmpty()) {
                alert = new Alert(Alert.AlertType.WARNING, feilmld);
            }else {
                alert = new Alert(Alert.AlertType.CONFIRMATION, person.toString());
            }
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.show();
        }catch (Exception e){
            alert = new Alert(Alert.AlertType.WARNING, "Fyll inn info! ");
            alert.show();
            System.out.println(e);
        }



    }
}
