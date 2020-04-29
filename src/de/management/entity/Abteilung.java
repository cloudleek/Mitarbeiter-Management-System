package de.management.entity;
// Utility Imports
import java.util.ArrayList;

/**
 * Modelliert die Abteilung einer Organisation, die Mitarbeiter bzw. Personal beinhaltet.
 */
public class Abteilung {
    // Instanzvariablen
    private ArrayList<Mitarbeiter> mitarbeiterList;
    private String kennung, funktion;
    private Mitarbeiter manager;
    private int abteilung_id;

    // Konstruktor
    public Abteilung(String kennung, String funktion, Mitarbeiter manager, int abteilung_id) {
        this.mitarbeiterList = new ArrayList<>();
        this.kennung = kennung;
        this.funktion = funktion;
        this.manager = manager;
        this.abteilung_id = abteilung_id;
    }

    // Public Methoden
    public void addMitarbeiter(Mitarbeiter mitarbeiter) {
        this.mitarbeiterList.add(mitarbeiter);
    }

    public void removeMitarbeiter(Mitarbeiter mitarbeiter) {
        this.mitarbeiterList.remove(mitarbeiter);
    }

    public void removeMitarbeiter(int mitarbeiter_id) {
        Mitarbeiter mitarbeiter = mitarbeiterList.stream()
                .filter(element -> element.getMitarbeiter_id() == mitarbeiter_id)
                .findAny()
                .orElse(null);
        this.mitarbeiterList.remove(mitarbeiter_id);
    }

    // Getter und Setter
    public ArrayList<Mitarbeiter> getMitarbeiterList() {
        return mitarbeiterList;
    }

    public void setMitarbeiterList(ArrayList<Mitarbeiter> mitarbeiterList) {
        this.mitarbeiterList = mitarbeiterList;
    }

    public String getKennung() {
        return kennung;
    }

    public void setKennung(String kennung) {
        this.kennung = kennung;
    }

    public String getFunktion() {
        return funktion;
    }

    public void setFunktion(String funktion) {
        this.funktion = funktion;
    }

    public Mitarbeiter getManager() {
        return manager;
    }

    public void setManager(Mitarbeiter manager) {
        this.manager = manager;
    }

    public int getAbteilung_id() {
        return abteilung_id;
    }

    public void setAbteilung_id(int abteilung_id) {
        this.abteilung_id = abteilung_id;
    }
}
