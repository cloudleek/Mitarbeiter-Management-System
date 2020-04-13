package de.management.entity;

/**
 * Modelliert einen Mitarbeiter, der durch persoenliche, bankspezifische, berufliche und Adressdaten gekennzeichnet ist.
 */
public class Mitarbeiter {
    // Instanzvariablen
    private int mitarbeiter_id;
    private String vorname, nachname;
    private String geburtsDatum;
    private String position;
    private Adresse adresse;
    private Login login;
    private Bezahlung bezahlung;
    private Bankverbindung bankverbindung;

    // Konstruktor
    public Mitarbeiter(int mitarbeiter_id, String vorname, String nachname, String geburtsDatum, String position, Adresse adresse, Login login, Bezahlung bezahlung, Bankverbindung bankverbindung) {
        this.mitarbeiter_id = mitarbeiter_id;
        this.vorname = vorname;
        this.nachname = nachname;
        this.geburtsDatum = geburtsDatum;
        this.position = position;
        this.adresse = adresse;
        this.login = login;
        this.bezahlung = bezahlung;
        this.bankverbindung = bankverbindung;
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

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
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

    public Bankverbindung getBankverbindung() {
        return bankverbindung;
    }

    public void setBankverbindung(Bankverbindung bankverbindung) {
        this.bankverbindung = bankverbindung;
    }

    public String getFormatiertName() {
        return this.nachname + ", " + this.vorname;
    }
}
