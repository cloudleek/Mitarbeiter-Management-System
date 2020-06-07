package de.management.controller;
// JavaFX Imports
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
// Weitere Imports
import de.management.database.DBZugriff;
import de.management.entity.Mitarbeiter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Authentifizierung implements Initializable {

    private Mitarbeiter mitarbeiter;
    private String dashboard_pfad;

    @FXML
    private JFXTextField loginNameField, loginPasswordField;
    @FXML
    private JFXButton loginButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    private void handleLoginEvent(ActionEvent actionEvent) {
        String benutzername = loginNameField.getText();
        String passwort = loginPasswordField.getText();

        boolean validerBenutzername = DBZugriff.validiereBenutzerkennung(benutzername);

        if(validerBenutzername) {
            // Passwort überprüfen
            boolean validesPasswort = DBZugriff.validiereAnmeldung(benutzername, passwort);

            if(validesPasswort) {
                closeWindow();
                loadMitarbeiter(benutzername, passwort);
                loadDashboardPfad();
                loadDashboard();
            } else {
                System.out.println("Fehlerhaftes Passwort!");
            }
        } else {
            System.out.println("Fehlerhafter Benutzername!");
        }
    }

    private void closeWindow() {
        ((Stage) loginButton.getScene().getWindow()).close();
    }

    private void loadMitarbeiter(String benutzername, String passwort) {
        int login_id = DBZugriff.loadLoginId(benutzername, passwort);
        this.mitarbeiter = DBZugriff.loadMitarbeiter(login_id);
    }

    private void loadDashboardPfad() {
        String mitarbeiter_position = this.mitarbeiter.getPosition().toLowerCase();
        this.dashboard_pfad = "../view/views/" + mitarbeiter_position + "Dashboard.fxml";
    }

    private void loadDashboard() {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(this.dashboard_pfad));
            Stage stage = new Stage();
            stage.setTitle("Dashboard");
            stage.setScene(new Scene(parent, 1280, 720));
            stage.sizeToScene();
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
