package pageobjects;

import org.openqa.selenium.WebDriver;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MandatorPage extends RolePage {
    public MandatorPage(WebDriver driver) {
        super(driver);
    }

    public MandatorPage() {
        super();
    }

    public Map<String, List<String>> getGermanNavigation() {
        Map<String, List<String>> navi = new HashMap<>();
        navi.put("Home", Arrays.asList());
        navi.put("Währungen", Arrays.asList("Zuweisen", "Gebinde ansehen"));
        navi.put("Mandant", Arrays.asList("Bearbeiten", "Ansehen"));
        navi.put("Bank", Arrays.asList("(De)Aktivieren", "Ansehen"));
        navi.put("GTU", Arrays.asList("Ansehen"));
        navi.put("Cashcenter", Arrays.asList("Detaildaten anlegen", "Detaildaten bearbeiten", "Ansehen"));
        navi.put("Bankkonto", Arrays.asList("Anlegen", "Bearbeiten", "Löschen", "Ansehen"));
        navi.put("Organisationseinheit", Arrays.asList("Anlegen", "Bearbeiten"));
        navi.put("Gesellschaft", Arrays.asList("Anlegen", "Erweitern", "Freigeben", "Ansehen"));
        navi.put("Markt", Arrays.asList("Anlegen", "Bearbeiten", "Detaildaten anlegen", "Detaildaten bearbeiten",
                "Ansehen", "Anlegen durch Kopieren"));
        navi.put("Ebenen", Arrays.asList("Bearbeiten"));
        navi.put("Workflow", Arrays.asList("Bearbeiten"));
        navi.put("Kontenselektion", Arrays.asList("Anlegen", "Bearbeiten"));
        navi.put("GTU-Versicherungen", Arrays.asList("Anlegen", "Bearbeiten", "Ansehen", "Zuordnen"));
        navi.put("Leistungsverzeichnisse", Arrays.asList("Anlegen", "Bearbeiten", "Ansehen", "Anlegen durch Kopieren",
                "Massenänderung", "Dokumente erstellen", "Dokumente ansehen", "Dokumente löschen/freigeben"));
        navi.put("Rahmenvertrag", Arrays.asList("Anlegen", "Bearbeiten", "Kündigen", "Ansehen"));
        navi.put("Wechselgeld", Arrays.asList("Akzeptanz-Monitor", "Akzeptanz aufheben"));
        navi.put("Buchen", Arrays.asList("Daten freigeben", "Sachkonten-Tabelle SAP"));
        navi.put("Münzgeldtabelle", Arrays.asList("Bericht"));
        navi.put("Berichte", Arrays.asList());
        navi.put("Managementberichte", Arrays.asList());

        return navi;

    }


}
