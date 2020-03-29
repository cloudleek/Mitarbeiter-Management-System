# Mitarbeiter-Management-System

Wirtschaftsinformatik Facharbeit

## 1. Zielbestimmung
Die Desktopanwendung easyEmployee dient zur Verwaltung von Angestellten innerhalb eines Unternehmens.

### 1.1 Musskriterien
- Autorisierte Benutzer:
  - Mitarbeiter
    - Ein- und Austragen der eigenen Anwesenheit
    - Ansehen der Bezahlung
    - Anzeige des eigenen Datenblatts
  - Manager
    - Mitarbeiter erstellen, löschen und bearbeiten
    - Bezahlungen zuweisen
    - Mitarbeiter der eigenen Abteilung tabellarisch darstellen
  - Administrator
    - Verwaltung von Managern und Mitarbeitern
    - Bezahlungen erstellen, löschen und bearbeiten
    - Abteilungen erstellen, löschen und bearbeiten
    
## 2. Produkteinsatz

### 2.1 Anwendungsbereiche
- Die Anwendung dient zur administrativen Unterstützung des Management eines Unternehmens, um Mitarbeiter verschiedener Abteilungen möglichst effizient zu verwalten.

### 2.2 Zielgruppen
- Organisationen, die über einen entsprechenden Mitarbeiterstamm verfügen, der nur digital effizient zu verwalten ist.
- Mitarbeiter verschiedener Positionen, die von der Digitalisierung der Verwaltung profitieren.

### 2.3 Betriebsbedingungen
- Betriebsdauer 24/7
- Keine Wartung notwendig.

## 3. Produktumgebung

### 3.1 Software
- Java Runtime Environment (min. JRE 8)

### 3.2 Hardware
- Grafikfähige Rechner
- Ausreichende Datenspeicherkapazität
- Rechenleistung, die mit der Benutzerzahl gerecht ist

### 3.3 Organisation
- Netzwerkkomunikation, um mehreren Nutzern einen Datenbankzugriff zu ermöglichen.
- Administratoren, die Manager einrichten und Anträge der Manager bearbeiten.

## 4. Anwendungsfunktionen (noch nicht vollständig)
| Kennung | Benutzergruppe       |
|---------|----------------------|
| F1xx    | Angemeldete Benutzer |
| F2xx    | Manager              |
| F3xx    | Administrator        |
| F4xx    | Alle Benutzer        |

### 4.1 Mitarbeiter
Unter der Bedingung, dass sich der Benutzer gem. /F400/ angemeldet hat, stehen folgende Funktionen zur Verfügung:
- /F100/ Anwesenheit eintragen
- /F101/ Anwesenheit austragen
- /F102/ Datenblatt einsehen
- /F103/ Bezahlung ansehen

### 4.2 Manager
Unter der Bedingung, dass sich der Benutzer gem. /F400/ angemeldet hat, stehen folgende Funktionen zur Verfügung:
- /F200/ Antrag auf Erstellung eines Mitarbeiters stellen
- /F201/ Mitarbeiter entlassen
- /F202/ Mitarbeiter bearbeiten
- /F203/ Bezahlung eines Mitarbeiters festlegen
- /F210/ Alle Mitarbeiter der eigenen Abteilung tabellarisch darstellen

### 4.3 Administration
Unter der Begingung, dass sich der Benutzer gem. /F400/ angemeldet hat, stehen folgende Funktionen zur Verfügung:
- /F300/ Benutzerkonten anlegen
- /F301/ Benutzerkonten entfernen
- /F302/ Benutzerkonten bearbeiten
- /F310/ Bezahlungen anlegen
- /F311/ Bezahlungen entfernen
- /F312/ Bezahlungen bearbeiten
- /F320/ Abteilungen anlegen
- /F321/ Abteilungen entfernen
- /F322/ Abteilungen bearbeiten

### 4.4 Anmeldung
- /F400/ Login mit Benutzerdaten (Nickname und Passwort)
- /F405/ Logout nach Bestätigung der Intention (Modales Pop-Up Fenster)
- /F410/ Wiederherstellung des Passworts

## 5. Anwendungsdaten
- /D000/ Benutzerdaten:
  - Nickname (Loginname)
  - Passwort
  - Vor- und Zuname
  - Geburtsdatum
  - Anschrift
  - Bankverbindung
  - eMail
  - Telefonnummer
  - Anwesenheiten
- /D100/ Abteilungsdaten:
  - Bezeichnung
  - Tätigkeitsbereich
  - Zuständiger Manager

## 6. Benutzeroberfläche
- Folgt noch

## 7. Testszenarien
- Folgt noch

## 8. Entwicklungsumgebung

### 8.1 Software
- Betriebssystem: Windows 10
- IntelliJ 2019 3.2 in der Ultimate Edition
- SQLite 

### 8.2 Hardware
- Private Rechner mit der genannten Software
