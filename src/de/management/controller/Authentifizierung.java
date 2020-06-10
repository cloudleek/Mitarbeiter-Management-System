package de.management.controller;
// JavaFX Imports
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import de.management.entity.Abteilung;
import javafx.beans.binding.Bindings;
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
        loginButton.disableProperty().bind(Bindings.createBooleanBinding(
                () -> loginNameField.getText().isEmpty() || loginPasswordField.getText().isEmpty(),
                loginNameField.textProperty(), loginPasswordField.textProperty()
        ));
    }

    @FXML
    private void handleLoginEvent(ActionEvent actionEvent) {
        String benutzername = loginNameField.getText();
        String passwort = loginPasswordField.getText();

        boolean validerBenutzername = DBZugriff.validiereBenutzerkennung(benutzername);

        if(validerBenutzername) {
            boolean validesPasswort = DBZugriff.validiereAnmeldung(benutzername, passwort);

            if(validesPasswort) {
                closeWindow();
                loadMitarbeiter(benutzername, passwort);
                loadDashboardPfad();
                loadDashboard();
            } else {
                // TODO: 10/06/2020 Fehlermeldung einfügen
                System.out.println("Fehlerhaftes Passwort!");
            }
        } else {
            // TODO: 10/06/2020 Fehlermeldung einfügen
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
            FXMLLoader fxmlLoader = new FXMLLoader();
            // URL des Dashboards festlegen
            URL dashboard_url = getClass().getResource(this.dashboard_pfad);
            fxmlLoader.setLocation(dashboard_url);
            // Parent und Stage laden
            Parent parent = fxmlLoader.load();
            Stage stage = new Stage();
            // Dashboard initialisieren
            String mitarbeiter_position = this.mitarbeiter.getPosition();
            if(mitarbeiter_position.equals("Mitarbeiter")) {
                MitarbeiterDashboard mitarbeiterDashboard = fxmlLoader.getController();
                mitarbeiterDashboard.initialisiereDaten(this.mitarbeiter);
            } else if (mitarbeiter_position.equals("Manager")) {
                ManagerDashboard managerDashboard = fxmlLoader.getController();
                managerDashboard.initialisiereDaten(this.mitarbeiter);
            } else {
                AdminDashboard adminDashboard = fxmlLoader.getController();
                adminDashboard.initialisiereDaten(this.mitarbeiter);
            }
            // Stage Informationen festlegen
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
