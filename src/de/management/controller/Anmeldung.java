package de.management.controller;

// JFoenix Imports
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
// JavaFX Imports
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
// Anwendung Imports
import de.management.database.DBZugriff;
import de.management.entity.Mitarbeiter;
// Utility Imports
import java.io.IOException;
import java.net.URL;
// SQL Imports
import java.util.ResourceBundle;

/**
 * Steuerungselement fuer die Anmeldung eines Benutzers.
 */
public class Anmeldung implements Initializable {
    // Instanzvariablen
    @FXML // FX:ID der Inputfelder
    private JFXTextField loginNameField, loginPasswordField;
    @FXML // FX:ID der Loginschaltflaeche
    private JFXButton loginButton;

    // Public Methoden
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Wird keine Benutzerkennung oder kein Passwort angegeben, wird der Button abgeschaltet.
        loginButton.disableProperty().bind(Bindings.createBooleanBinding(
                () -> loginNameField.getText().isEmpty() || loginPasswordField.getText().isEmpty(),
                loginNameField.textProperty(), loginPasswordField.textProperty()
        ));
    }

    /**
     * Ueberprueft, ob die eingegebenen Logindaten in der Kombination in der Datenbank hinterlegt sind.
     *
     * @param event ActionEvent, wenn der Benutzer den Button anklickt.
     */
    public void inputValidieren(ActionEvent event) throws IOException {
        if(!DBZugriff.validiereBenutzerkennung(loginNameField.getText())) {
            // TODO: Fehlerdialog einfuegen
            System.out.println("Falscher Benutzername!");
        } else {
            if(!DBZugriff.validiereAnmeldung(loginNameField.getText(), loginPasswordField.getText())) {
                // TODO: Fehlerdialog einfuegen
                System.out.println("Falsches Passwort!");
            } else {
                // Szene des Dashboards laden
                FXMLLoader fxmlLoader = new FXMLLoader();

                // Mitarbeiter und speziell dessen Position laden
                int loggedInMitarbeiter_id = DBZugriff.loadLoginId(loginNameField.getText(), loginPasswordField.getText());
                Mitarbeiter loggedInMitarbeiter = DBZugriff.loadMitarbeiter(loggedInMitarbeiter_id);
                String position = loggedInMitarbeiter.getPosition();

                // Respektive FXML Datei des Dashboards laden
                if(position.equals("Mitarbeiter")) {
                    fxmlLoader.setLocation(getClass().getResource("../view/views/mitarbeiterDashboard.fxml"));
                } else if (position.equals("Manager")) {
                    fxmlLoader.setLocation(getClass().getResource("../view/views/managerDashboard.fxml"));
                } else {
                    fxmlLoader.setLocation(getClass().getResource("../view/views/adminDashboard.fxml"));
                }

                // Parent der Szene laden
                Parent dashboardViewParent = fxmlLoader.load();

                // Respektive Steuerungskomponente (Dashboard Steuerung)laden
                if(position.equals("Mitarbeiter")) {
                    MitarbeiterDashboard mitarbeiterDashboard = fxmlLoader.getController();
                    mitarbeiterDashboard.initialisiereDaten(loggedInMitarbeiter);
                } else if (position.equals("Manager")) {
                    ManagerDashboard managerDashboard = fxmlLoader.getController();
                    managerDashboard.initialisiereDaten(loggedInMitarbeiter);
                } else {
                    AdminDashboard adminDashboard = fxmlLoader.getController();
                    adminDashboard.initialisiereDaten(loggedInMitarbeiter);
                }

                // Aus dem Dahboard eine Szene erstellen
                Scene dashboardSzene = new Scene(dashboardViewParent);

                // Die aktuell angezeigte Stage laden
                Stage fenster = (Stage) ((Node) event.getSource()).getScene().getWindow();

                // Szenenwechsel: Dashboard als neue Szene festlegen
                fenster.setScene(dashboardSzene);
                fenster.centerOnScreen();
                fenster.show();
            }
        }
    }

}
