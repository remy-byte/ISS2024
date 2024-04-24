package ro.iss2024.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import ro.iss2024.domain.EntitateCompanie;
import ro.iss2024.domain.RoleUser;
import ro.iss2024.service.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class CEOController {


    public ChoiceBox choiceBox;
    public TextField password_field;
    public TextField username_field;
    public TableColumn role;
    public TableColumn username;
    public TableView<EntitateCompanie> tableUseri;
    Service service = null;

    String selected = null;

    ObservableList<EntitateCompanie> model = FXCollections.observableArrayList();

    public void setService(Service service) {
        this.service = service;
        initModel();
        populateUsers();

    }

    private void populateUsers() {
        username.setCellValueFactory(new PropertyValueFactory<EntitateCompanie, String>("username"));
        role.setCellValueFactory(new PropertyValueFactory<EntitateCompanie, String>("role"));

        tableUseri.setItems(model);
    }

    public void initModel(){
        System.out.println("CEO Controller initialized");

        Iterable<EntitateCompanie> useri = service.getAllUsers();
        System.out.println(useri.spliterator().getExactSizeIfKnown());
        List<EntitateCompanie> userList = StreamSupport.stream(useri.spliterator(), false)
                .collect(Collectors.toList());

        model.setAll(userList);

        choiceBox.getItems().addAll("CEO", "TESTER", "PROGRAMATOR");

        choiceBox.addEventFilter(ActionEvent.ACTION, event -> {
            selected = choiceBox.getSelectionModel().getSelectedItem().toString();
        });


    }


    public void handleAdd(ActionEvent actionEvent) {

        String username = username_field.getText();
        String password = password_field.getText();

        if (selected == null) {
            System.out.println("Please select a role");
            return;
        }
        EntitateCompanie user = new EntitateCompanie(username, password, RoleUser.valueOf(selected));
        this.service.addUser(user);
        initModel();
        populateUsers();
    }

    public void handleDelete(ActionEvent actionEvent) {
        EntitateCompanie user = tableUseri.getSelectionModel().getSelectedItem();
        if (user == null) {
            System.out.println("Please select a user");
            return;
        }
        service.deleteUser(user);
        initModel();
        populateUsers();
    }
}
