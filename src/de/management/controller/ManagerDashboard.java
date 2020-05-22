package de.management.controller;

import com.jfoenix.controls.JFXTreeTableView;
import de.management.entity.Abteilung;
import de.management.entity.Mitarbeiter;
// JavaFX Imports
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ManagerDashboard {
    /* Sidebar */
    @FXML private Label mitarbeiterName;
    @FXML private Label mitarbeiterPosition;
    /* Info Panel 01 */
    @FXML private Label firstInfoContentTitle;
    @FXML private Label firstInfoContentInfo;
    /* Info Panel 02 */
    @FXML private Label secondInfoContentTitle;
    @FXML private Label secondInfoContentInfo;
    /* Info Panel 03 */
    @FXML private Label thirdInfoContentTitle;
    @FXML private Label thirdInfoContentInfo;
    /* Info Panel 04 */
    @FXML private Label fourthInfoContentTitle;
    @FXML private Label fourthInfoContentInfo;
    /* Datenblatt */
    @FXML private Label nameContent;
    @FXML private Label geburtsdatumContent;
    @FXML private Label positionContent;
    @FXML private Label anschriftStrasse;
    @FXML private Label anschriftOrt;
    @FXML private Label kennzifferContent;
    /* Bezahlung */
    @FXML private Label bezahlungStufeContent;
    @FXML private Label bezahlungBetragContent;
    @FXML private Label bankLeitZahlContent;
    @FXML private Label kontoNummerContent;
    /* Abteilung */
    @FXML private Label kennungContent;
    @FXML private Label funktionContent;
    @FXML private Label managerContent;
    /* Mitarbeiter */
    @FXML private JFXTreeTableView treeTableView;

    public void initialisiereDaten(Mitarbeiter mitarbeiter, Abteilung abteilung) {
        /* Sidebar */
        this.mitarbeiterName.setText(mitarbeiter.getFormatiertName());
        this.mitarbeiterPosition.setText("Manager");
        /* Info Panel 01 */
        this.firstInfoContentTitle.setText("Manager");
        this.firstInfoContentInfo.setText("Abteilung: " + abteilung.getKennung());
        /* Info Panel 02 */
        this.secondInfoContentTitle.setText(Double.toString(mitarbeiter.getBezahlung().getBetrag()));
        /* Info Panel 03 */
        this.thirdInfoContentTitle.setText(abteilung.getFunktion());
        this.thirdInfoContentInfo.setText(abteilung.getKennung());
        /* Info Panel 04 */
        this.fourthInfoContentTitle.setText(Integer.toString(abteilung.getMitarbeiterList().size()));

        /* Datenblatt */
        this.nameContent.setText(mitarbeiter.getFormatiertName());
        this.geburtsdatumContent.setText(mitarbeiter.getGeburtsDatum());
        this.positionContent.setText("Manager");
        this.anschriftStrasse.setText(mitarbeiter.getAdresse().getStrasse() + " " + mitarbeiter.getAdresse().getHausNr());
        this.anschriftOrt.setText(mitarbeiter.getAdresse().getOrtFormatiert());
        this.kennzifferContent.setText(Integer.toString(mitarbeiter.getMitarbeiter_id()));
        /* Bezahlung */
        this.bezahlungStufeContent.setText(mitarbeiter.getBezahlung().getStufe());
        this.bezahlungBetragContent.setText(Double.toString(mitarbeiter.getBezahlung().getBetrag()));
        this.bankLeitZahlContent.setText(mitarbeiter.getBankverbindung().getBankLeitZahl());
        this.kontoNummerContent.setText(mitarbeiter.getBankverbindung().getKontoNummer());
        /* Abteilung */
        this.kennungContent.setText(abteilung.getKennung());
        this.funktionContent.setText(abteilung.getFunktion());
        this.managerContent.setText(mitarbeiter.getFormatiertName());
    }
}
