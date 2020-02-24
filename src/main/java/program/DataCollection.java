package program;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

public class DataCollection {

    private ObservableList<Person> list = FXCollections.observableArrayList();

    public void attachTableView(TableView<Person> tv) {
        tv.setItems(list);
    }

    public void addElement(Person obj) {
        list.add(obj);
    }

    public ObservableList<Person> getList(){
        return list;
    }

}