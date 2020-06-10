package de.management.controller;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import de.management.database.DBZugriff;
import de.management.entity.Abteilung;
import de.management.entity.Mitarbeiter;
// JavaFX Imports
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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
    private Abteilung abteilung;
    /* Mitarbeiter */
    @FXML private JFXTreeTableView<?> treeTableView;
    private Mitarbeiter mitarbeiter;


    public void initialisiereDaten(Mitarbeiter mitarbeiter) {
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
        /* Mitarbeiter der Abteilung */
        JFXTreeTableColumn<Person, Integer> mitarbeiterKennziffer = new JFXTreeTableColumn<>("#");
    }

    private void initializeAbteilung() {
        this.loadAbteilung();

        this.kennungContent.setText(this.abteilung.getKennung());
        this.funktionContent.setText(this.abteilung.getFunktion());
        this.managerContent.setText(this.mitarbeiter.getFormatiertName());
    }

    private void loadAbteilung() {
        int abteilung_id = DBZugriff.loadAbteilungId(this.mitarbeiter.getMitarbeiter_id());
        this.abteilung = DBZugriff.loadAbteilung(abteilung_id);
    }

    class Person extends RecursiveTreeObject<Person> {
        IntegerProperty kennziffer;
        StringProperty vorname;
        StringProperty nachname;
        StringProperty geburtsdatum;
        StringProperty position;

        public Person(int kennziffer, String vorname, String nachname, String geburtsdatum, String positon) {
            this.kennziffer = new SimpleIntegerProperty(kennziffer);
            this.vorname = new SimpleStringProperty(vorname);
            this.nachname = new SimpleStringProperty(nachname);
            this.geburtsdatum = new SimpleStringProperty(geburtsdatum);
            this.position = new SimpleStringProperty(positon);

        }
    }
}
