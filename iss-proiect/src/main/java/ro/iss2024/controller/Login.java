package ro.iss2024.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ro.iss2024.domain.EntitateCompanie;
import ro.iss2024.domain.RoleUser;
import ro.iss2024.service.Service;

import java.io.IOException;
import java.util.Objects;

public class Login {

    public TextField username;
    public TextField password;
    Service service;

    public Login () {
    }

    public void setService(Service service) {
        this.service = service;
    }


    public void login(ActionEvent actionEvent) throws IOException {
        String user = username.getText();
        String pass = password.getText();
        EntitateCompanie entity = service.login(user, pass);
    //    System.out.println("Trying to login with user: " + user + " and password: " + pass + "and role: " + entity.getRole());
        if (entity != null) {
            if (entity.getRole().equals(RoleUser.TESTER)) {
                Stage newStage=new Stage();
                FXMLLoader loader = new FXMLLoader(TesterController.class.getResource("/tester-view.fxml"));
                Parent root=loader.load();
                newStage.setScene(new Scene(root));

                TesterController testerController = loader.getController();

                testerController.setService(service);
                newStage.show();
            } else if(Objects.equals(entity.getRole(), RoleUser.PROGRAMATOR)){
                Stage newStage=new Stage();
                FXMLLoader loader = new FXMLLoader(TesterController.class.getResource("/programator-view.fxml"));
                Parent root=loader.load();
                newStage.setScene(new Scene(root));

                ProgramatorController ctrl = loader.getController();

                ctrl.setService(service);

                newStage.show();
            }
         else if (Objects.equals(entity.getRole(), RoleUser.CEO)) {
                System.out.println("CEO");
                Stage newStage = new Stage();
                FXMLLoader loader = new FXMLLoader(TesterController.class.getResource("/ceo-view.fxml"));
                Parent root = loader.load();
                newStage.setScene(new Scene(root));
                CEOController ctrl = loader.getController();
                ctrl.setService(service);
                newStage.show();
            }
        } else {
            System.out.println("Invalid login");
        }
    }
}
