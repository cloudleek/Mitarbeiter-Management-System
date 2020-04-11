package de.management.controller;
// JFoenix Imports
import com.jfoenix.controls.JFXButton;
// JavaFX Imports
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
// Utility Imports
import java.net.URL;
import java.util.ResourceBundle;
// Anwendung Imports
import de.management.entity.*;

/**
 * FXML Controller Klasse für das MitarbeiterDashboard Dashboard
 */
public class MitarbeiterDashboard implements Initializable {
    // //
    // Instanzvariablen
    // //
    private Mitarbeiter angemeldeterMitarbeiter;

    @FXML private Label anwesenheitInfo;
    @FXML private Label datumLabel;
    @FXML private Label uhrzeitLabel;
    @FXML private Label anwesenheitStatusLabel;
    @FXML private JFXButton anwesenheitButton;

    @FXML private Label bezahlungInfo;
    @FXML private Label stufeLabel;
    @FXML private Label betragLabel;
    @FXML private Label bankleitzahlLabel;
    @FXML private Label kontoNummerLabel;

    @FXML private Label datenblattInfo;
    @FXML private Label nameLabel;
    @FXML private Label geburtsdatumLabel;
    @FXML private Label positionLabel;
    @FXML private Label anschriftStrasseLabel;
    @FXML private Label anschriftOrtLabel;
    @FXML private Label mitarbeiterNrLabel;

    // //
    // Public Methoden
    // //

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void initialisiereDaten(Mitarbeiter mitarbeiter) {
        this.angemeldeterMitarbeiter = mitarbeiter;
        // Erstes Info-Panel
        this.drawSystemDatum();
        this.drawSystemUhrzeit();
        // Zweites Info-Panel
        this.drawBezahlungStufe();
        this.drawBezahlungBetrag();
        this.drawBankLeitZahl();
        this.drawKontoNummer();
        // Drittes Info-Panel
        this.drawName();
        this.drawGeburtsdatum();
        this.drawPosition();
        this.drawAnschrift();
        this.drawMitarbeiterKennziffer();
    }
    // 1.
    public void drawSystemDatum() {
        datumLabel.setText(java.time.LocalDate.now().toString().replace("-","."));
    }

    public void drawSystemUhrzeit() {
        uhrzeitLabel.setText(java.time.LocalTime.now().toString());
    }

    public void toggleAnwesenheit() {
        String aktuellerStatus = anwesenheitStatusLabel.getText();
        anwesenheitStatusLabel.setText((aktuellerStatus.equals("Anwesend") ? "Abwesend" : "Anwesend"));
    }
    // 2.
    public void drawBezahlungStufe() {
        stufeLabel.setText(angemeldeterMitarbeiter.getBezahlung().getStufe());
    }

    public void drawBezahlungBetrag() {
        betragLabel.setText(Double.toString(angemeldeterMitarbeiter.getBezahlung().getBetrag()));
        bezahlungInfo.setText(Double.toString(angemeldeterMitarbeiter.getBezahlung().getBetrag()));
    }

    public void drawBankLeitZahl() {
        bankleitzahlLabel.setText(angemeldeterMitarbeiter.getBankverbindung().getBankLeitZahl());
    }

    public void drawKontoNummer() {
        kontoNummerLabel.setText(angemeldeterMitarbeiter.getBankverbindung().getKontoNummer());
    }
    // 3-
    public void drawName() {
        nameLabel.setText(angemeldeterMitarbeiter.getFormatiertName());
    }

    public void drawGeburtsdatum() {
        geburtsdatumLabel.setText(angemeldeterMitarbeiter.getGeburtsDatum());
    }

    public void drawPosition() {
        datenblattInfo.setText(angemeldeterMitarbeiter.getPosition());
        positionLabel.setText(angemeldeterMitarbeiter.getPosition());
    }

    public void drawAnschrift() {
        anschriftStrasseLabel.setText(angemeldeterMitarbeiter.getAdresse().getStrasseFormatiert());
        anschriftOrtLabel.setText(angemeldeterMitarbeiter.getAdresse().getOrtFormatiert());
    }

    public void drawMitarbeiterKennziffer() {
        mitarbeiterNrLabel.setText(Integer.toString(angemeldeterMitarbeiter.getMitarbeiter_id()));
    }

}
