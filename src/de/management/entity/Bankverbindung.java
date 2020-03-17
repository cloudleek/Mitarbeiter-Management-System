package de.management.entity;

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
}
