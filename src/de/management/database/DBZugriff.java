package de.management.database;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

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
