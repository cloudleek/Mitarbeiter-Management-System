package de.management.entity;

/**
 * Modelliert ein Paket aus Anmeldedaten eines Benutzers.
 */
public class Login {
    // Instanzvariablen
    private String username, passwort;
    private int login_id;

    // Konstruktor
    public Login(String username, String passwort, int login_id) {
        this.username = username;
        this.passwort = passwort;
        this.login_id = login_id;
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

    public int getLogin_id() {
        return login_id;
    }

    public void setLogin_id(int login_id) {
        this.login_id = login_id;
    }
}
