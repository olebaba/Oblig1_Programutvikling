package program.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import program.DataCollection;
import program.Exceptions.*;
import program.Person;
import program.Validering;
import java.io.File;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

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
    private TableView tableView;

    DataCollection collection = new DataCollection();

    @FXML
    public void initialize(){

        //binder collection til tableview
        collection.attachTableView(tableView);

        //kun mulig å sette alder til 0-99
        alder.textProperty().addListener((observableValue, oldValue, newValue) -> {
            if(!newValue.matches("\\d|\\d{2}|^$")) alder.setText(oldValue);
        });

        dato.focusedProperty().addListener((observableValue, aBoolean, t1) -> {
            if(dato.getValue() != null) {
                if (dato.getValue().getYear() > LocalDate.now().getYear()) dato.setValue(LocalDate.now());
            }
        });

        nummer.textProperty().addListener((observableValue, s, t1) -> {
            if(!t1.matches("\\d|\\d{2}|\\d{3}|\\d{4}|\\d{5}|\\d{6}|\\d{7}|\\d{8}|^$")) nummer.setText(s);
        });

    }


    @FXML
    public void TrykketLagreFil(ActionEvent actionEvent) throws FileNotFoundException, IOException, ClassNotFoundException {
        //Serialisering
        File nyFil = new File("personer.txt");
        Person person = LagPerson();
        FileOutputStream fileOutput = new FileOutputStream(nyFil);
        ObjectOutputStream output = new ObjectOutputStream(fileOutput);
        System.out.println(person.toString());
        output.writeObject(person);

        output.close();
        fileOutput.close();
    }

    @FXML
    public void TrykketApneFil(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        FileChooser filValg = new FileChooser();
        filValg.setTitle("Velg en *.txt-fil");
        filValg.getExtensionFilters().add(new ExtensionFilter("Text files", "*.txt"));
        filValg.showOpenDialog(null);

        FileInputStream fileInput = new FileInputStream(String.valueOf(filValg));
        ObjectInputStream input = new ObjectInputStream(fileInput);
        
        input.readObject();

    }

    public Person LagPerson() throws InvalidDateException, InvalidEmailException, InvalidTlfException,
            InvalidNameException, InvalidAgeException {

        return new Person(navn.getText(), Integer.parseInt(alder.getText()),
                dato.getValue().getDayOfMonth(), dato.getValue().getMonthValue(),
                dato.getValue().getYear(), epost.getText(), nummer.getText());
    }

    public boolean Valider() {
        Alert alert;

        try {
            Person person = LagPerson();

            String feilmld = Validering.validerPerson(person);

            if(!feilmld.isEmpty()) {
                alert = new Alert(Alert.AlertType.WARNING, feilmld);
            }else {
                alert = new Alert(Alert.AlertType.CONFIRMATION, person.toString());
            }
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.show();
            return true;

        }catch (Exception e){
            alert = new Alert(Alert.AlertType.WARNING, "Fyll inn info! ");
            alert.show();
            System.out.println(e);

            return false;
        }
    }

    private void resetTxtFields() {
        navn.setText("");
        alder.setText("");
        dato.setValue(null);
        nummer.setText("");
        epost.setText("");
    }

    public void trykkRegistrer(ActionEvent actionEvent) throws InvalidDateException, InvalidEmailException,
            InvalidTlfException, InvalidNameException, InvalidAgeException {

        if(Valider()) {

            collection.addElement(LagPerson());
            resetTxtFields();
        }
    }
}
