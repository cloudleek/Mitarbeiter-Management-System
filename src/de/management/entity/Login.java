package de.management.entity;

/**
 * Modelliert ein Paket aus Anmeldedaten eines Benutzers.
 */
public class Login {
    // Instanzvariablen
    private String username, passwort;

    // Konstruktor
    public Login(String username, String passwort) {
        this.username = username;
        this.passwort = passwort;
    }

    // Public Methoden
    public boolean isLoginKorrekt(String[] loginInput) {
        return loginInput[0].equals(username) && loginInput[1].equals(this.passwort);
    }

    // Getter und Setter
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }
}
