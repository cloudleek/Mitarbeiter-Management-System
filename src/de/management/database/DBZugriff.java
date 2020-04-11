package de.management.database;

// SQL Imports
import java.sql.*;
// Anwendung Imports
import de.management.entity.*;

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
    public static boolean validiereBenutzerkennung(String kennung) {
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
    public static boolean validiereAnmeldung(String name, String passwort) {
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

    /**
     * Laedt einen Mitarbeiter aus der Datenbank und gibt diesen als Mitarbeiter-Objekt zurueck.
     * @param loginId Eindeutige Login ID-Nummer des Mitarbeiters
     * @return Mitarbeiter mit allen Daten
     * @see Mitarbeiter
     */
    public static Mitarbeiter loadMitarbeiter(int loginId) {
        // Mitarbeiter
        Mitarbeiter mitarbeiter = null;
        // SQl Query
        String query = "SELECT * FROM tbl_mitarbeiter WHERE login_id = ?";
        // Datenbankzugriff
        try {
            // Statement
            PreparedStatement preparedStatement = getVerbindung().prepareStatement(query);
            // Parameter definieren
            preparedStatement.setInt(1, loginId);
            // Query ausfuehren
            ResultSet resultSet = preparedStatement.executeQuery();
            // Daten auswerten
            while(resultSet.next()) {
                int mitarbeiter_id = resultSet.getInt("mitarbeiter_id");
                String vorname = resultSet.getString("vorname");
                String nachname = resultSet.getString("nachname");
                String geburtsdatum = resultSet.getString("geburtsdatum");
                String position = resultSet.getString("position");
                Adresse adresse = loadAdresse(resultSet.getInt("adresse_id"));
                Login login = loadLogin(resultSet.getInt("login_id"));
                Bezahlung bezahlung = loadBezahlung(resultSet.getInt("bezahlung_id"));
                mitarbeiter = new Mitarbeiter(mitarbeiter_id, vorname, nachname, geburtsdatum, position, adresse, login, bezahlung);
            }
            // Verbindung trennen
            preparedStatement.close();
            verbindung.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Output
        return mitarbeiter;
    }

    /**
     * Laedt die Adresse eines Mitarbeiters aus der Datenbank und gibt diese als Adresse-Objekt zurueck.
     * @param adresse_id Eindeutige Adressen ID-Nummer des Mitarbeiters
     * @return Adresse mit allen Daten
     * @see Adresse
     */
    public static Adresse loadAdresse(int adresse_id) {
        // Adresse
        Adresse adresse = null;
        // DQL
        String query = "SELECT * FROM tbl_adresse WHERE adresse_id = ?";
        // Datenbankzugriff
        try {
            // Statement
            PreparedStatement preparedStatement = getVerbindung().prepareStatement(query);
            // Parameter definieren
            preparedStatement.setInt(1, adresse_id);
            // Query ausfuehren
            ResultSet resultSet = preparedStatement.executeQuery();
            // Daten auswerten
            while(resultSet.next()) {
                String strasse = resultSet.getString("strasse");
                String hausNr = resultSet.getString("hausNummer");
                String ort = resultSet.getString("ort");
                String plz = resultSet.getString("postLeitZahl");
                adresse = new Adresse(adresse_id, strasse, hausNr, ort, plz);
            }
            // Verbindung trennen
            preparedStatement.close();
            verbindung.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Output
        return adresse;
    }

    /**
     * Laedt die Logindaten eines Mitarbeiters aus der Datenbank und gibt diese als Login-Objekt zurueck.
     * @param login_id Eindeutige Login ID-Nummer des Mitarbeiters
     * @return Login mit Zugangsdaten
     * @see Login
     */
    public static Login loadLogin(int login_id) {
        // Login
        Login login = null;
        // DQL
        String query = "SELECT * FROM tbl_login WHERE login_id = ?";
        // Datenbankzugriff
        try {
            // Statement
            PreparedStatement preparedStatement = getVerbindung().prepareStatement(query);
            // Parameter definieren
            preparedStatement.setInt(1, login_id);
            // Query ausfuehren
            ResultSet resultSet = preparedStatement.executeQuery();
            // Daten auswerten
            while(resultSet.next()) {
                String benutzername = resultSet.getString("benutzername");
                String passwort = resultSet.getString("passwort");
                login = new Login(benutzername, passwort);
            }
            // Verbindung trennen
            preparedStatement.close();
            verbindung.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Output
        return login;
    }

    /**
     * Laedt die Logindaten eines Mitarbeiters aus der Datenbank und giebt diese als Login-Objekt zurueck.
     * @param benutzerkennung Kennung des Benutzers
     * @param passwort Passwort des Benutzers
     * @return Login mit Zugangsdaten
     * @see Login
     */
    public static Login loadLogin(String benutzerkennung, String passwort) {
        // Login
        Login login = null;
        // DQL
        String query = "SELECT * FROM tbl_login WHERE benutzername = ? AND passwort = ?";
        // Datenbankzugriff
        try {
            // Statement
            PreparedStatement preparedStatement = getVerbindung().prepareStatement(query);
            // Parameter definieren
            preparedStatement.setString(1, benutzerkennung);
            preparedStatement.setString(2, passwort);
            // Query ausfuehren
            ResultSet resultSet = preparedStatement.executeQuery();
            // Daten auswerten
            while(resultSet.next()) {
                String loginName = resultSet.getString("benutzername");
                String loginPasswort = resultSet.getString("passwort");
                login = new Login(loginName, loginPasswort);
            }
            // Verbindung trennen
            preparedStatement.close();
            verbindung.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Output
        return login;
    }


    /**
     * Laedt eine Bezahlungsstufe aus der Datenbank und gibt diese als Bezahlungs-Objekt zurueck.
     * @param bezahlung_id Eindeutige Bezahlungs ID der Bezahlungsstufe
     * @return Bezahlung-Objekt den Bezahlungsdaten
     * @see Bezahlung
     */
    public static Bezahlung loadBezahlung(int bezahlung_id) {
        // Bezahlung
        Bezahlung bezahlung = null;
        // DQL
        String query = "SELECT * FROM tbl_bezahlung WHERE bezahlung_id = ?";
        // Datenbankzugriff
        try {
            // Statement
            PreparedStatement preparedStatement = getVerbindung().prepareStatement(query);
            // Parameter definieren
            preparedStatement.setInt(1, bezahlung_id);
            // Query ausfuehren
            ResultSet resultSet = preparedStatement.executeQuery();
            // Daten auswerten
            while(resultSet.next()) {
                String stufe = resultSet.getString("stufe");
                double betrag = resultSet.getDouble("betrag");
                bezahlung = new Bezahlung(stufe, betrag);
            }
            // Verbindung trennen
            preparedStatement.close();
            verbindung.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Output
        return bezahlung;
    }

    /**
     * Laedt die Bankverbindung eines Mitarbeiters aus der Datenbank und gibt diese als Bankverbindung-Objekt zurueck.
     * @param bankverbindung_id Eindeutige Bankverbindungs ID der Bankverbindung
     * @return Bankverbindungs-Objekt mit allen Kontodaten
     */
    public static Bankverbindung loadBankverbindung(int bankverbindung_id) {
        // Output
        Bankverbindung bankverbindung = null;
        // DQL
        String query = "SELECT * FROM tbl_bankverbindung WHERE bankverbindung_id = ?";
        // Datenbankzugriff
        try {
            // Statement
            PreparedStatement preparedStatement = getVerbindung().prepareStatement(query);
            // Parameter definieren
            preparedStatement.setInt(1, bankverbindung_id);
            // Query ausfuehren
            ResultSet resultSet = preparedStatement.executeQuery();
            // Daten auswerten
            while(resultSet.next()) {
                String bankLeitZahl = resultSet.getString("bankLeitzahl");
                String kontoNummer = resultSet.getString("kontoNummer");
                bankverbindung = new Bankverbindung(bankLeitZahl, kontoNummer);
            }
            // Verbindung trennen
            preparedStatement.close();
            verbindung.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Output
        return bankverbindung;
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
