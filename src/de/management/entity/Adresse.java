package de.management.entity;

public class Adresse {
    // Instanzvariablen
    private int addresse_id;
    private String strasse, hausNr, ort, plz;

    // Konstruktor
    public Adresse(int addresse_id, String strasse, String hausNr, String ort, String plz) {
        this.addresse_id = addresse_id;
        this.strasse = strasse;
        this.hausNr = hausNr;
        this.ort = ort;
        this.plz = plz;
    }

    // Getter und Setter
    public int getAddresse_id() {
        return addresse_id;
    }

    public void setAddresse_id(int addresse_id) {
        this.addresse_id = addresse_id;
    }

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public String getHausNr() {
        return hausNr;
    }

    public void setHausNr(String hausNr) {
        this.hausNr = hausNr;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public String getPlz() {
        return plz;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }
}
