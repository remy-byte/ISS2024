package ro.iss2024.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ro.iss2024.domain.Bug;
import ro.iss2024.domain.StatusBug;
import ro.iss2024.service.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ProgramatorController {
    public TableView tableBug;
    public TableColumn id;
    public TableColumn name;
    public TableColumn descriere;
    public TableColumn status;
    
    Service service;
    
    ObservableList<Bug> bugs = FXCollections.observableArrayList();

    public void handleUpdate(ActionEvent actionEvent) {

        Bug bug = (Bug) tableBug.getSelectionModel().getSelectedItem();
        if (bug == null) {
            return;
        }

        bug.setStatusBug(StatusBug.CLOSED);

        service.updateBug(bug);
        initModel();
        populateBugs();

    }

    public void filterByStatus(ActionEvent actionEvent) {
        String status = "OPEN";
        Iterable<Bug> bugs = service.getAllBugs();
        List<Bug> bugList = StreamSupport.stream(bugs.spliterator(), false)
                .collect(Collectors.toList());

        bugList = bugList.stream()
                .filter(bug -> bug.getStatusBug().toString().equals(status))
                .collect(Collectors.toList());

        this.bugs.setAll(bugList);
    }

    public void setService(Service service) {
        this.service = service;
        initModel();
        populateBugs();
    }

    private void populateBugs() {

        tableBug.setItems(bugs);
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        descriere.setCellValueFactory(new PropertyValueFactory<>("description"));
        status.setCellValueFactory(new PropertyValueFactory<>("StatusBug"));

        tableBug.setItems(bugs);
    }

    private void initModel() {

        Iterable<Bug> bugs = service.getAllBugs();
        List<Bug> bugsList = StreamSupport.stream(bugs.spliterator(), false)
                .collect(Collectors.toList());

        this.bugs.setAll(bugsList);
        
    }
}
