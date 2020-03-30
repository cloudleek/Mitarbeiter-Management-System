package de.management.view.controller;

// JavaFx Imports
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

// Utility Imports
import java.net.URL;

public class LoginView extends Application{
    // Instanzvariablen
    @FXML // URL der FXML Datei
    private URL dateiPfad = getClass().getResource("../views/login.fxml");
    Scene scene;

    // Public Methoden
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(this.dateiPfad);

        scene = new Scene(root, 1280, 720);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setOnCloseRequest(windowEvent -> closeWindow());
        primaryStage.setTitle("Login");
        primaryStage.setResizable(false);
    }

    // Private Methoden
    private void closeWindow() {
        // TODO: 30/03/2020 Bestaetigungsfenster 
    }
}
