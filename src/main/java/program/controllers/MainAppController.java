package program.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
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
import java.io.File;

import java.io.*;
import java.time.LocalDate;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

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
    private ChoiceBox<String> filtrer;
   @FXML
   private TextField sok;

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

    @FXML private TableView<Person> tableView;

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
        List<Person> liste = collection.getList();
        File nyFil = new File("personer.txt");
        Writer fileWriter = new FileWriter(nyFil, true);
        for(Person p : liste){
            fileWriter.write("\n" + p.toString());
        }

        fileWriter.close();


    }
    @FXML
    public void TrykketApneFil(ActionEvent actionEvent) throws IOException, ClassNotFoundException {

        FileChooser filValg = new FileChooser();
        filValg.setTitle("Velg en *.txt-fil");
        filValg.getExtensionFilters().add(new ExtensionFilter("Text files", "*.txt"));
        File valgtFil = filValg.showOpenDialog(null);

        Scanner scan = new Scanner(valgtFil);

        String[][] personer = new String[100][15];
        int personnr = 0;
       while(scan.hasNext()){
            String setning = scan.nextLine();
            personer[personnr] = setning.split("(,)|(=)|(')");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate datoen = LocalDate.parse(personer[personnr][7],formatter);
           Person person = new Person(personer[personnr][2], datoen , personer[personnr][10], personer[personnr][14]);
                   collection.addElement(person);
           personnr++;
       }

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

    public void findMatch(String s){
        String regex = sok.getText();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(s);

        //if (matcher.matches()) return true;
    }

    public void filtrer(ActionEvent actionEvent) {



        if(filtrer.getValue().equals("Navn")){
            ObservableList<Person> people = collection.getList().stream().filter(x -> x.getNavn().toLowerCase().
                    contentEquals(sok.getText().toLowerCase())).
                    collect(Collectors.toCollection(FXCollections::observableArrayList));
            tableView.setItems(people);
        }
        if(filtrer.getValue().equals("Alder")){
            ObservableList<Person> people = collection.getList().stream().filter(x -> Integer.parseInt(sok.getText())
                    == ( x.getAlder())).collect(Collectors.toCollection(FXCollections::observableArrayList));
            tableView.setItems(people);
        }
        if(filtrer.getValue().equals("Epost")){
            ObservableList<Person> people = collection.getList().stream().filter(x -> sok.getText().toLowerCase().
                    contains(x.getEpost().toLowerCase())).
                    collect(Collectors.toCollection(FXCollections::observableArrayList));
            tableView.setItems(people);
        }
        if(filtrer.getValue().equals("Nummer")){
            ObservableList<Person> people = collection.getList().stream().filter(x -> sok.getText().toLowerCase().
                    matches(x.getTelefonnummer().toLowerCase())).
                    collect(Collectors.toCollection(FXCollections::observableArrayList));
            tableView.setItems(people);
        }

        if(sok.getText().isEmpty()){
            tableView.setItems(collection.getList());
        }
    }
}
