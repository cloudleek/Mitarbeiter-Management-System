package de.management.entity;

/**
 * Modelliert eine Bezahlungsstufe mit Bezeichnung und Betrag.
 */
public class Bezahlung {
    // Instanzvariablen
    private String stufe;
    private double betrag;

    // Konstruktor
    public Bezahlung(String stufe, double betrag) {
        this.stufe = stufe;
        this.betrag = betrag;
    }

    // Getter und Setter
    public String getStufe() {
        return stufe;
    }

    public void setStufe(String stufe) {
        this.stufe = stufe;
    }

    public double getBetrag() {
        return betrag;
    }

    public void setBetrag(double betrag) {
        this.betrag = betrag;
    }
}
