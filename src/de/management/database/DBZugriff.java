package de.management.database;

// SQL Imports
import java.sql.*;
import java.util.ArrayList;
// Anwendung Imports
import de.management.entity.*;

/**
 * Stellt eine Verbindung und verschiedene Zugriffe auf die Datenbank zur Verfuegung.
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

    public static ArrayList<Mitarbeiter> loadMitarbeiterOfAbteilung(int abteilung_id) {
        // Lokale Variablen
        ArrayList<Mitarbeiter> mitarbeiterOfAbteilung = new ArrayList<Mitarbeiter>();
        // SQL Query
        String query = "SELECT * FROM tbl_mitarbeiter WHERE abteilung_id = ?";
        // Datenbankzugruff
        try {
            // Statement
            PreparedStatement preparedStatement = getVerbindung().prepareStatement(query);
            // Parameter setzen
            preparedStatement.setInt(1, abteilung_id);
            // Query ausfuehren
            ResultSet resultSet = preparedStatement.executeQuery();
            // Daten auswerten
            while(resultSet.next()) {
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Output
        return mitarbeiterOfAbteilung;
    }

    public static Abteilung loadAbteilung(int abteilung_id) {
        // Abteilung
        Abteilung abteilung = null;
        // Lokale Varialbne
        String kennung = null;
        String funktion = null;
        int manager_id = 0;
        // SQL Query
        String query = "SELECT * FROM tbl_abteilung WHERE abteilung_id = ?";
        // Datenbankzugriff
        try {
            // Statement
            PreparedStatement preparedStatement = getVerbindung().prepareStatement(query);
            // Paramater definieren
            preparedStatement.setInt(1, abteilung_id);
            // Query ausfuehren
            ResultSet resultSet = preparedStatement.executeQuery();
            // Daten auswerten
            while(resultSet.next()) {
                kennung = resultSet.getString("kennung");
                funktion = resultSet.getString("funktion");
                manager_id = resultSet.getInt("manager_id");
            }
            Mitarbeiter manager = loadMitarbeiterById(manager_id);
            abteilung = new Abteilung(kennung, funktion, manager, abteilung_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Output
        return abteilung;
    }

    public static int loadAbteilungId(int mitarbeiter_id) {
        // Lokale Variable
        int abteilung_id = 0;
        // SQL Query
        String query = "SELECT * FROM tbl_abteilung WHERE manager_id = ?";
        // Datenbankzugriff
        try {
            // Statement
            PreparedStatement preparedStatement = getVerbindung().prepareStatement(query);
            // Paramter definieren
            preparedStatement.setInt(1, mitarbeiter_id);
            // Query ausfuehren
            ResultSet resultSet = preparedStatement.executeQuery();
            // Daten auswerten
            while(resultSet.next()) {
                abteilung_id = resultSet.getInt("abteilung_id");
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        // output
        return abteilung_id;
    }

    public static Mitarbeiter loadMitarbeiterById(int mitarbeiter_id) {
        // Mitarbeiter
        Mitarbeiter mitarbeiter = null;
        // Lokale Variablen
        int adresse_id = 0;
        int login_id = 0;
        int bezahlung_id = 0;
        int bankverbindung_id = 0;
        // SQl Query
        String query = "SELECT * FROM tbl_mitarbeiter WHERE mitarbeiter_id = ?";
        // Datenbankzugriff
        try {
            // Statement
            PreparedStatement preparedStatement = getVerbindung().prepareStatement(query);
            // Parameter definieren
            preparedStatement.setInt(1, mitarbeiter_id);
            // Query ausfuehren
            ResultSet resultSet = preparedStatement.executeQuery();
            // Daten auswerten
            while(resultSet.next()) {
                String vorname = resultSet.getString("vorname");
                String nachname = resultSet.getString("nachname");
                String geburtsdatum = resultSet.getString("geburtsdatum");
                String position = resultSet.getString("position");
                adresse_id = resultSet.getInt("adresse_id");
                login_id = resultSet.getInt("login_id");
                bezahlung_id = resultSet.getInt("bezahlung_id");
                bankverbindung_id = resultSet.getInt("bankverbindung_id");
                mitarbeiter = new Mitarbeiter(mitarbeiter_id, vorname, nachname, geburtsdatum, position, null, null, null, null);
            }
            // Verbindung trennen
            preparedStatement.close();
            verbindung.close();
            // Daten ergaenzen
            mitarbeiter.setAdresse(loadAdresse(adresse_id));
            mitarbeiter.setLogin(loadLogin(login_id));
            mitarbeiter.setBezahlung(loadBezahlung(bezahlung_id));
            mitarbeiter.setBankverbindung(loadBankverbindung(bankverbindung_id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Output
        return mitarbeiter;
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
        // Lokale Variablen
        int adresse_id = 0;
        int login_id = 0;
        int bezahlung_id = 0;
        int bankverbindung_id = 0;
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
                adresse_id = resultSet.getInt("adresse_id");
                login_id = resultSet.getInt("login_id");
                bezahlung_id = resultSet.getInt("bezahlung_id");
                bankverbindung_id = resultSet.getInt("bankverbindung_id");
                mitarbeiter = new Mitarbeiter(mitarbeiter_id, vorname, nachname, geburtsdatum, position, null, null, null, null);
            }
            // Verbindung trennen
            preparedStatement.close();
            verbindung.close();
            // Daten ergaenzen
            mitarbeiter.setAdresse(loadAdresse(adresse_id));
            mitarbeiter.setLogin(loadLogin(login_id));
            mitarbeiter.setBezahlung(loadBezahlung(bezahlung_id));
            mitarbeiter.setBankverbindung(loadBankverbindung(bankverbindung_id));
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
                login = new Login(benutzername, passwort, login_id);
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
    public static int loadLoginId(String benutzerkennung, String passwort) {
        // Login
        int login_id = 0;
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
                login_id = resultSet.getInt("login_id");
            }
            // Verbindung trennen
            preparedStatement.close();
            verbindung.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Output
        return login_id;
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
                bezahlung = new Bezahlung(stufe, betrag, bezahlung_id);
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
                bankverbindung = new Bankverbindung(bankLeitZahl, kontoNummer, bankverbindung_id);
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

    public static void insertMitarbeiter(Mitarbeiter mitarbeiter) {
        // DML
        String query = "INSERT INTO tbl_mitarbeiter (mitarbeiter_id, vorname, nachname, geburtsdatum, position, adresse_id, login_id, bankverbindung_id, bezahlung_id) VALUES(NULL, ?, ?, ?, ?, ?, ?, ?, ?)";
        // Datenbankzugriff
        try {
            // Statement
            PreparedStatement preparedStatement = getVerbindung().prepareStatement(query);
            // Paramater
            preparedStatement.setString(1, mitarbeiter.getVorname());
            preparedStatement.setString(2, mitarbeiter.getNachname());
            preparedStatement.setString(3, mitarbeiter.getGeburtsDatum());
            preparedStatement.setString(4, mitarbeiter.getPosition());
            preparedStatement.setInt(5, mitarbeiter.getAdresse().getAddresse_id());
            preparedStatement.setInt(6, mitarbeiter.getLogin().getLogin_id());
            preparedStatement.setInt(7, mitarbeiter.getBankverbindung().getBankverbindung_id());
            preparedStatement.setInt(8, mitarbeiter.getBezahlung().getBezahlung_id());
            // Query
            preparedStatement.executeUpdate();
            // Verbindung
            preparedStatement.close();
            verbindung.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int insertAdresse(String strasse, String hausNummer, String ort, String postLeitzahl) {
        // Output
        int adresse_id = 0;
        // DML
        String query = "INSERT INTO tbl_adresse (adresse_id, strasse, hausNummer, ort, postLeitZahl) VALUES (null, ?, ?, ?, ?)";
        // Datenbankzugriff
        try {
            // Statement
            PreparedStatement preparedStatement = getVerbindung().prepareStatement(query);
            // Paramater
            preparedStatement.setString(1, strasse);
            preparedStatement.setString(2, hausNummer);
            preparedStatement.setString(3, ort);
            preparedStatement.setString(4, postLeitzahl);
            // Query
            preparedStatement.executeUpdate();
            ResultSet resultSet = getVerbindung().createStatement().executeQuery("SELECT last_insert_rowid()");
            while(resultSet.next()) {
                adresse_id = (int) resultSet.getLong(1);
            }
            // Verbindung
            preparedStatement.close();
            verbindung.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adresse_id;
    }

    public static int insertLogin(String benutzername, String passwort) {
        // Output
        int login_id = 0;
        // DML
        String query = "INSERT INTO tbl_login (login_id, benutzername, passwort) VALUES (NULL, ?, ?)";
        // Datenbankzugriff
        try {
            // Statement
            PreparedStatement preparedStatement = getVerbindung().prepareStatement(query);
            // Paramater
            preparedStatement.setString(1, benutzername);
            preparedStatement.setString(2, passwort);
            // Query
            preparedStatement.executeUpdate();
            ResultSet resultSet = getVerbindung().createStatement().executeQuery("SELECT last_insert_rowid()");
            while(resultSet.next()) {
                login_id = (int) resultSet.getLong(1);
            }
            // Verbindung
            preparedStatement.close();
            verbindung.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return login_id;
    }

    public static int insertBezahlung(String stufe, double betrag) {
        // Output
        int bezahlung_id = 0;
        // DML
        String query = "INSERT INTO tbl_bezahlung (bezahlung_id, stufe, betrag) VALUES (NULL, ?, ?)";
        // Datenbankzugriff
        try {
            // Statement
            PreparedStatement preparedStatement = getVerbindung().prepareStatement(query);
            // Paramater
            preparedStatement.setString(1, stufe);
            preparedStatement.setDouble(2, betrag);
            // Query
            preparedStatement.executeUpdate();
            ResultSet resultSet = getVerbindung().createStatement().executeQuery("SELECT last_insert_rowid()");
            while(resultSet.next()) {
                bezahlung_id = (int) resultSet.getLong(1);
            }
            // Verbindung
            preparedStatement.close();
            verbindung.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bezahlung_id;
    }

    public static int insertBankverbindung(String bankLeitzahl, String kontoNumer) {
        // Output
        int bankverbindung_id = 0;
        // DML
        String query = "INSERT INTO tbl_bankverbindung (bankverbindung_id, bankLeitzahl, kontoNummer) VALUES (NULL, ?, ?)";
        // Datenbankzugriff
        try {
            // Statement
            PreparedStatement preparedStatement = getVerbindung().prepareStatement(query);
            // Paramater
            preparedStatement.setString(1, bankLeitzahl);
            preparedStatement.setString(2, kontoNumer);
            // Query
            preparedStatement.executeUpdate();
            ResultSet resultSet = getVerbindung().createStatement().executeQuery("SELECT last_insert_rowid()");
            while(resultSet.next()) {
                bankverbindung_id = (int) resultSet.getLong(1);
            }
            // Verbindung
            preparedStatement.close();
            verbindung.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bankverbindung_id;
    }

    public static void removeMitarbeiter(Mitarbeiter mitarbeiter) {
        // DML
        String query = "DELETE FROM tbl_mitarbeiter WHERE mitarbeiter_id = ?";
        // Datenbankzugriff
        try {
            // Statement
            PreparedStatement preparedStatement = getVerbindung().prepareStatement(query);
            // Paramater
            preparedStatement.setInt(1, mitarbeiter.getMitarbeiter_id());
            // Query
            preparedStatement.executeUpdate();
            // Verbindung
            preparedStatement.close();
            verbindung.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Kaskadierendes Löschen
        removeAdresse(mitarbeiter.getAdresse());
        removeBankverbindung(mitarbeiter.getBankverbindung());
        removeLogin(mitarbeiter.getLogin());
    }

    public static void removeAdresse(Adresse adresse) {
        // DML
        String query = "DELETE FROM tbl_adresse WHERE adresse_id = ?";
        // Datenbankzugriff
        try {
            // Statement
            PreparedStatement preparedStatement = getVerbindung().prepareStatement(query);
            // Paramater
            preparedStatement.setInt(1, adresse.getAddresse_id());
            // Query
            preparedStatement.executeUpdate();
            // Verbindung
            preparedStatement.close();
            verbindung.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void removeLogin(Login login) {
        // DML
        String query = "DELETE FROM tbl_login WHERE login_id = ?";
        // Datenbankzugriff
        try {
            // Statement
            PreparedStatement preparedStatement = getVerbindung().prepareStatement(query);
            // Paramater
            preparedStatement.setInt(1, login.getLogin_id());
            // Query
            preparedStatement.executeUpdate();
            // Verbindung
            preparedStatement.close();
            verbindung.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void removeBezahlung(Bezahlung bezahlung) {
        // DML
        String query = "DELETE FROM tbl_bezahlung WHERE bezahlung_id = ?";
        // Datenbankzugriff
        try {
            // Statement
            PreparedStatement preparedStatement = getVerbindung().prepareStatement(query);
            // Paramater
            preparedStatement.setInt(1, bezahlung.getBezahlung_id());
            // Query
            preparedStatement.executeUpdate();
            // Verbindung
            preparedStatement.close();
            verbindung.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void removeBankverbindung(Bankverbindung bankverbindung) {
        // DML
        String query = "DELETE FROM tbl_bankverbindung WHERE bankverbindung_id = ?";
        // Datenbankzugriff
        try {
            // Statement
            PreparedStatement preparedStatement = getVerbindung().prepareStatement(query);
            // Paramater
            preparedStatement.setInt(1, bankverbindung.getBankverbindung_id());
            // Query
            preparedStatement.executeUpdate();
            // Verbindung
            preparedStatement.close();
            verbindung.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
