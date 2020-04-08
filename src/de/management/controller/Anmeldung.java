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
// Application Imports
import de.management.database.DBZugriff;
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
        if(!DBZugriff.validateBenutzerkennung(loginNameField.getText())) {
            // TODO: 29/03/2020 Fehlermeldung: Falscher Benutzername
            System.out.println("Falscher Benutzername!");
        } else {
            if(!DBZugriff.validateLogin(loginNameField.getText(), loginPasswordField.getText())) {
                // TODO: 29/03/2020 Fehlermeldung: Falsches Passwort
                System.out.println("Falsches Passwort!");
            } else { // Benutzer weiterleiten
                // Szene des Dashboards laden
                Parent dashboardViewParent = FXMLLoader.load(getClass().getResource("../view/views/mitarbeiterDashboard.fxml"));
                Scene dashboardSzene = new Scene(dashboardViewParent);
                // Stage Informationen der aktuellen Stage
                Stage fenster = (Stage) ((Node) event.getSource()).getScene().getWindow();
                fenster.setScene(dashboardSzene);
                fenster.centerOnScreen();
                fenster.show();
            }
        }
    }

}
