package de.management.database;

import java.sql.*;

/**
 * Stellt eine Verbindung zur Datenbank zur Verfuegung.
 */
public class DBZugriff {
    // Instanzvariablen
    static Connection verbindung = null;
    static final String dbUrl = "jdbc:sqlite:db/mitarbeiter.sqlite";

    // Public Methoden

    /**
     * Returniert eine hergestellte Datenbankverbindung.
     * @return Datenbankverbindung
     */
    public static Connection getVerbindung() {
        try {
            if(verbindung == null || verbindung.isClosed()) {
                connectDatabase();
                return verbindung;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return verbindung;
    }

    /**
     * Gleicht die Eingabe der Benutzerkennung mit der Datenbank ab, ob die Benutzerkennung vorhanden ist.
     * @param kennung Input des Benutzers.
     * @return Wahr oder falsch Wert, ob die Benutzerkennung vorhanden ist.
     */
    public static boolean validateBenutzerkennung(String kennung) {
        boolean validerInput = false;
        // SQL Query
        String query = "SELECT * FROM tbl_login WHERE benutzername = ?";
        // Datenbankzugriff
        try {
            PreparedStatement preparedStatement = getVerbindung().prepareStatement(query);
            // Parameter setzen
            preparedStatement.setString(1, kennung);
            // Query ausfuehren
            ResultSet resultSet = preparedStatement.executeQuery();
            // Daten auswerten
            validerInput = resultSet.next();
            // Verbindung trennen
            preparedStatement.close();
            verbindung.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Output
        return validerInput;
    }

    /**
     * Validiert den Login eines Benutzers in Abhaengigkeit von der Uebereinstimmung der Benutzerdaten mit der Datenbank.
     * @return Wahr oder falsch Wert, ob der Login valide ist.
     */
    public static boolean validateLogin(String name, String passwort) {
        boolean validerLogin = false;
        // SQl Query
        String query = "SELECT * FROM tbl_login WHERE benutzername = ? AND passwort = ?";
        // Datenbankzugriff
        try {
            PreparedStatement preparedStatement = getVerbindung().prepareStatement(query);
            // Parameter setzen
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, passwort);
            // Query ausfuehren
            ResultSet resultSet = preparedStatement.executeQuery();
            // Daten auswerten
            validerLogin = resultSet.next();
            // Verbindung trennen
            preparedStatement.close();
            verbindung.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Output
        return validerLogin;
    }

    // Private Methoden

    /**
     * Stellt eine Datenbankverbindung her.
     */
    private static void connectDatabase() {
        try {
            verbindung = DriverManager.getConnection(dbUrl);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
