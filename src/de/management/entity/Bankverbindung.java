package de.management.entity;

/**
 * Modelliert die Bankverbindung einer Person mit Bankleitzahl und Kontonummer.
 */
public class Bankverbindung {
    // Instanzvariablen
    private String bankLeitZahl;
    private String kontoNummer;

    // Konstruktor
    public Bankverbindung(String bankLeitZahl, String kontoNummer) {
        this.bankLeitZahl = bankLeitZahl;
        this.kontoNummer = kontoNummer;
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
}
