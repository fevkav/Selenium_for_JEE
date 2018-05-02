package pageobjects;

import org.openqa.selenium.WebDriver;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OperatorPage extends RolePage {


    public OperatorPage(WebDriver driver) {
        super(driver);
    }

    public OperatorPage() {
        super();
    }

    public Map<String, List<String>> getGermanNavigation() {
        Map<String, List<String>> navi = new HashMap<>();
        navi.put("Home", Arrays.asList());
        navi.put("Währungen", Arrays.asList("Gebinde ansehen"));
        navi.put("Bank", Arrays.asList("Ansehen"));
        navi.put("GTU", Arrays.asList("Ansehen"));
        navi.put("Cashcenter", Arrays.asList("Ansehen"));
        navi.put("Bankkonto", Arrays.asList("Ansehen"));
        navi.put("Organisationseinheit", Arrays.asList("Ansehen"));
        navi.put("Gesellschaft", Arrays.asList("Ansehen"));
        navi.put("Markt", Arrays.asList("Ansehen"));
        navi.put("Workflow", Arrays.asList("Bearbeiten"));
        navi.put("Leistungsverzeichnisse", Arrays.asList("Ansehen"));
        navi.put("Kontoauszugsdaten", Arrays.asList("Surrogat erfassen", "CashEDI ansehen"));
        navi.put("Wechselgeld", Arrays.asList("Surrogat erfassen", "Fehlerhafte Sätze bearbeiten", "Löschen", "Ansehen",
                "Bearbeiten", "Status-Monitor", "Akzeptanz-Monitor"));
        navi.put("Auszähldaten", Arrays.asList("Surrogat erfassen", "Fehlerhafte Sätze bearbeiten",
                "Datensätze bearbeiten", "Datei löschen", "Ansehen (Auszählobjekte)", "Status-Monitor",
                "Ansehen offener/fehlender Kassendaten", "Ansehen gematchter/gelöschter Kassendaten"));
        navi.put("Abmischen Auszähl-/Bankdaten", Arrays.asList("Match-Monitor", "Umwandeln Noten/Münzen"));
        navi.put("Buchen", Arrays.asList("Daten erstellen", "Sachbuchung anlegen", "Sachbuchung ansehen/löschen",
                "Berichte ansehen"));
        navi.put("Münzgeldtabelle", Arrays.asList("Bankdaten anlegen", "Bankdaten bearbeiten/löschen", "Bericht"));
        navi.put("Rechnungen", Arrays.asList("Anlegen", "Bearbeiten/löschen"));
        navi.put("Reklamationen", Arrays.asList("Anlegen", "Bearbeiten", "Ansehen", "Auswahltexte"));
        navi.put("Berichte", Arrays.asList());
        navi.put("Managementberichte", Arrays.asList());
        navi.put("Tools", Arrays.asList("Cash Count Expectation Calculator", "Wechselgeld Liefertage-Rechner"));

        return navi;

    }


}
