package de.management.entity;

/**
 * Modelliert eine Bezahlungsstufe mit Bezeichnung und Betrag.
 */
public class Bezahlung {
    // Instanzvariablen
    private String stufe;
    private double betrag;
    private int bezahlung_id;

    // Konstruktor
    public Bezahlung(String stufe, double betrag, int bezahlung_id) {
        this.stufe = stufe;
        this.betrag = betrag;
        this.bezahlung_id = bezahlung_id;
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

    public int getBezahlung_id() {
        return bezahlung_id;
    }

    public void setBezahlung_id(int bezahlung_id) {
        this.bezahlung_id = bezahlung_id;
    }
}
