package de.management.controller;
// JavaFX Imports
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import de.management.database.DBZugriff;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

// Utility Imports
import java.net.URL;

// SQL Imports
import java.util.ResourceBundle;

/**
 * Steuerungselement fuer die Anmeldung eines Benutzers.
 */
public class Anmeldung implements Initializable {
    // Instanzvariablen
    @FXML // FX:ID Referenzen fuer die benoetigten Elemente.
    private JFXTextField loginNameField, loginPasswordField;
    private JFXButton loginButton;


    // Public Methoden
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Wird der Button bataetigt, so wird der Input validiert.
        loginButton.setOnAction(e -> {
            inputValidieren();
        });

        // Wird keine Benutzerkennung oder kein Passwort angegeben, wird der Button abgeschaltet.
        loginButton.disableProperty().bind(Bindings.createBooleanBinding(
                () -> loginNameField.getText().isEmpty() || loginPasswordField.getText().isEmpty(),
                loginNameField.textProperty(), loginPasswordField.textProperty()
        ));
    }

    /**
     * Ueberprueft, ob die eingegebenen Logindaten in der Kombination in der Datenbank hinterlegt sind.
     */
    public void inputValidieren() {
        if(!DBZugriff.validateBenutzerkennung(loginNameField.getText())) {
            // TODO: 29/03/2020 Fehlermeldung: Falscher Benutzername
        } else {
            if(!DBZugriff.validateLogin(loginNameField.getText(), loginPasswordField.getText())) {
                // TODO: 29/03/2020 Fehlermeldung: Falsches Passwort
            } else {
                // TODO: 29/03/2020 Benutzer weiterleiten: Abhaengig von der Position des Mitarbeiters
            }
        }
    }

}
