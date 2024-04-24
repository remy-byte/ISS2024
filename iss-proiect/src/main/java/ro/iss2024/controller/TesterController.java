package ro.iss2024.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import ro.iss2024.domain.Bug;
import ro.iss2024.domain.StatusBug;
import ro.iss2024.service.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class TesterController {

    public TableView tableBugs;
    public TableColumn id;
    public TableColumn nume;
    public TableColumn description;
    public TableColumn status;
    public TextField nume_field;
    public TextArea descriere_area;

    ObservableList<Bug> model = FXCollections.observableArrayList();
    Service service = null;
    public void setService(Service service) {
        this.service = service;
        initModel();
        populateBugs();
    }

    private void populateBugs() {

        nume.setCellValueFactory(new PropertyValueFactory<Bug, String>("name"));
        description.setCellValueFactory(new PropertyValueFactory<Bug, String>("description"));
        status.setCellValueFactory(new PropertyValueFactory<Bug, String>("StatusBug"));
        id.setCellValueFactory(new PropertyValueFactory<Bug, Integer>("id"));

        tableBugs.setItems(model);

    }

    public void initModel(){

        Iterable<Bug> bugs = service.getAllBugs();

        List<Bug> bugList = StreamSupport.stream(bugs.spliterator(), false)
                .collect(Collectors.toList());

        model.setAll(bugList);
    }


    public void handleAdd(ActionEvent actionEvent) {

        String nume = nume_field.getText();
        String descriere = descriere_area.getText();
        Bug bug = new Bug(nume, descriere, StatusBug.OPEN);
        service.addBug(bug);
        initModel();
        populateBugs();

    }
}
