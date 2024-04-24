package ro.iss2024;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ro.iss2024.controller.Login;
import ro.iss2024.domain.EntitateCompanie;
import ro.iss2024.repository.BugRepository;
import ro.iss2024.repository.UserRepository;
import ro.iss2024.service.Service;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    private static SessionFactory instantiateSessionFactory() {
        SessionFactory sessionFactory = null;
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy(registry);
        }
        return sessionFactory;
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        SessionFactory sessionFactory = instantiateSessionFactory();
        UserRepository userRepository = new UserRepository(sessionFactory);
        BugRepository bugRepository = new BugRepository(sessionFactory);

        Iterable<EntitateCompanie> users = userRepository.getAllUsers();
        for (EntitateCompanie user : users) {
            System.out.println(user.getPassword());
        }

        Service service = new Service(userRepository, bugRepository); //service

        EntitateCompanie user = service.login("valycl", "123");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));
        Parent root = loader.load();
        primaryStage.setScene(new Scene(root));
        Login ctrl = loader.getController();
        ctrl.setService(service);
        primaryStage.setTitle("ro.iss2024.controller.Login");
        primaryStage.show();



    }
}