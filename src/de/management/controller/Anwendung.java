package de.management.controller;

// JavaFx Imports
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
// Utility Imports
import java.net.URL;

public class Anwendung extends Application {
    // Instanzvariablen
    @FXML // URL der loginFXML Datei
    private final URL loginFXML = getClass().getResource("../view/views/login.fxml");

    Scene scene;
    Stage stage;

    // Public Methoden

    /**
     * Einstiegspunkt fuer die JavaFX Anwendung.
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // FXML Datei
        Parent root = FXMLLoader.load(this.loginFXML);
        // Szene in HD-Aufloesung erstellen
        scene = new Scene(root, 1280, 720);
        stage = primaryStage;
        // Fensterattribute festlegen
        primaryStage.setScene(scene);
        primaryStage.setOnCloseRequest(windowEvent -> closeWindow());
        primaryStage.setTitle("Login");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    // Private Methoden
    private void closeWindow() {
        // TODO: 30/03/2020 Bestaetigungsfenster 
    }
}
