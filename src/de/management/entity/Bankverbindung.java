package de.management.entity;

/**
 * Modelliert die Bankverbindung einer Person mit Bankleitzahl und Kontonummer.
 */
public class Bankverbindung {
    // Instanzvariablen
    private String bankLeitZahl;
    private String kontoNummer;
    private int bankverbindung_id;

    // Konstruktor
    public Bankverbindung(String bankLeitZahl, String kontoNummer, int bankverbindung_id) {
        this.bankLeitZahl = bankLeitZahl;
        this.kontoNummer = kontoNummer;
        this.bankverbindung_id = bankverbindung_id;
    }

    // Getter und Setter
    public String getBankLeitZahl() {
        return bankLeitZahl;
    }

    public void setBankLeitZahl(String bankLeitZahl) {
        this.bankLeitZahl = bankLeitZahl;
    }

    public String getKontoNummer() {
        return kontoNummer;
    }

    public void setKontoNummer(String kontoNummer) {
        this.kontoNummer = kontoNummer;
    }

    public int getBankverbindung_id() {
        return bankverbindung_id;
    }

    public void setBankverbindung_id(int bankverbindung_id) {
        this.bankverbindung_id = bankverbindung_id;
    }
}
