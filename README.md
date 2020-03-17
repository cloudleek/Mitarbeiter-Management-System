# Mitarbeiter-Management-System

Wirtschaftsinformatik Facharbeit

## Implementierung
* Backend: Java
* Frontend: JavaFX (SceneBuilder)
* Datenbank: SQLite

## Übersicht
Diese Management-Anwendung erlaubt es drei verschiedenen Benutzergruppen Aktionen hinsichtlich ihrer Tätigkeit innerhalb eines simulierten Unternehmens vorzunehmen. Es erfolgt eine Unterteilung eines Mitarbeiters in drei verschiedene Positionen:

### Mitarbeiter
Ein Mitarbeiter stellt in der bestehenden Hierarchie das niedrigste Element dar, welches einem Manager untergeordnet ist und folgende Aktionen vornehmen kann:
* Anweseneinheit registrieren,
* Abwesenheit melden,
* Datenblatt einsehen und
* Status der Bezahlung überprüfen.

### Manager
Ein Manager repräsentiert die leitende Instanz einer simulierten Abteilung und ist mehreren Mitarbeitern untergeordnet. Des Weiteren kann jeder Manager folgende Aktionen vornehmen:
* Mitarbeiter einstellen, entlassen und bearbeiten,
* Bezahlungen einstellen und Boni vergeben und
* Datenblätter der einzelnen Mitarbeiter verwalten.

### Administrator
Ein Administrator stellt die rangtechnisch höchste Instanz innerhalb der Anwendung dar und kann administrative Tätigkeiten in Bezug auf ihm untergeordnete Instanzen vornehmen:
* Manager erstellen und verwalten,
* Login-Daten eines Benutzers verwalten und
* alle Aktionen eines Managers ebenfalls verrichten.
