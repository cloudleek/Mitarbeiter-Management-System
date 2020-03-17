package de.management.entity;

public class Mitarbeiter {
    // Instanzvariablen
    private int mitarbeiter_id;
    private String vorname, nachname;
    private String geburtsDatum; // TODO: 17/03/2020 Date-Objekt einfuegen
    private String position;
    private Addresse addresse;
    private Login login;
    private Bezahlung bezahlung;

    // Konstruktor
    public Mitarbeiter(int mitarbeiter_id, String vorname, String nachname, String geburtsDatum, String position, Addresse addresse, Login login, Bezahlung bezahlung) {
        this.mitarbeiter_id = mitarbeiter_id;
        this.vorname = vorname;
        this.nachname = nachname;
        this.geburtsDatum = geburtsDatum;
        this.position = position;
        this.addresse = addresse;
        this.login = login;
        this.bezahlung = bezahlung;
    }

    // Getter und Setter
    public int getMitarbeiter_id() {
        return mitarbeiter_id;
    }

    public void setMitarbeiter_id(int mitarbeiter_id) {
        this.mitarbeiter_id = mitarbeiter_id;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getGeburtsDatum() {
        return geburtsDatum;
    }

    public void setGeburtsDatum(String geburtsDatum) {
        this.geburtsDatum = geburtsDatum;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Addresse getAddresse() {
        return addresse;
    }

    public void setAddresse(Addresse addresse) {
        this.addresse = addresse;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public Bezahlung getBezahlung() {
        return bezahlung;
    }

    public void setBezahlung(Bezahlung bezahlung) {
        this.bezahlung = bezahlung;
    }
}
