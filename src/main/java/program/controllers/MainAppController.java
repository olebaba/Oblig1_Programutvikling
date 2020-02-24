package program.controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import program.DataCollection;
import program.Exceptions.*;
import program.Person;
import program.Validering;

import java.io.*;
import java.time.LocalDate;

public class MainAppController {
    @FXML
    private TextField navn;
   @FXML
   private DatePicker dato;
   @FXML
    private TextField epost;
   @FXML
    private TextField telefonnummer;

    @FXML
    private AnchorPane anchorpane;
    @FXML
    private MenuItem apneFil;
    @FXML
    private MenuItem lagreFil;
    @FXML
    private MenuItem validerInput;

    @FXML
    private TableColumn<DataCollection, String> navnCol;
    @FXML
    private TableColumn<DataCollection, String> alderCol;
    @FXML
    private TableColumn<DataCollection, String> datoCol;
    @FXML
    private TableColumn<DataCollection, String> nummerCol;
    @FXML
    private TableColumn<DataCollection, String> epostCol;

    @FXML private TableView<String> tableView;

    DataCollection collection = new DataCollection();

    @FXML
    public void initialize(){

        navnCol.setCellValueFactory(new PropertyValueFactory<DataCollection, String>("navn"));
        alderCol.setCellValueFactory(new PropertyValueFactory<DataCollection, String>("alder"));
        datoCol.setCellValueFactory(new PropertyValueFactory<DataCollection, String>("dato"));
        epostCol.setCellValueFactory(new PropertyValueFactory<DataCollection, String>("epost"));
        nummerCol.setCellValueFactory(new PropertyValueFactory<DataCollection, String>("telefonnummer"));

        collection.attachTableView(tableView);

        dato.focusedProperty().addListener((observableValue, aBoolean, t1) -> {
            if(dato.getValue() != null) {
                if (dato.getValue().getYear() > LocalDate.now().getYear()) dato.setValue(LocalDate.now());
            }
        });

        telefonnummer.textProperty().addListener((observableValue, s, t1) -> {
            if(!t1.matches("\\d|\\d{2}|\\d{3}|\\d{4}|\\d{5}|\\d{6}|\\d{7}|\\d{8}|^$")) telefonnummer.setText(s);
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

    public Person LagPerson() throws InvalidDateException, InvalidEmailException, InvalidTlfException,
            InvalidNameException, InvalidAgeException {

        return new Person(navn.getText(), dato.getValue(), epost.getText(), telefonnummer.getText());
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
        dato.setValue(null);
        telefonnummer.setText("");
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
