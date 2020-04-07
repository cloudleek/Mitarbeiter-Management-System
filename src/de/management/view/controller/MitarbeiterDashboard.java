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

public class MitarbeiterDashboard extends Application {
    // Instanzvariablen
    @FXML // URL der FXML Datei
    private URL dateiPfad = getClass().getResource("../views/mitarbeiterDashboard.fxml");
    Scene szene;

    // Public Methoden
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(this.dateiPfad);

        szene = new Scene(root, 1920, 1080);
        primaryStage.setScene(szene);
        primaryStage.show();
        primaryStage.setOnCloseRequest(windowEvent -> closeWindow());
        primaryStage.setTitle("Mitarbeiter Managent - Dashboard Mitarbeiter");
        primaryStage.setResizable(false);
    }

    // Private Methoden
    private void closeWindow() {
        // Bestaetigungsfenster
    }
}
