package pageobjects;

import org.openqa.selenium.WebDriver;

import java.util.Arrays;
import java.util.List;

public class MandatorPage extends RolePage {
    public MandatorPage(WebDriver driver) {
        super(driver);
    }

    public MandatorPage() {
        super();
    }

    public List<String> getGermanMainNavis() {
        return Arrays.asList("Home", "Währungen", "Mandant", "Bank", "GTU", "Cashcenter",
                "Bankkonto", "Organisationseinheit", "Gesellschaft", "Markt", "Ebenen", "Workflow", "Kontenselektion",
                "GTU-Versicherungen", "Leistungsverzeichnisse", "Rahmenvertrag", "Wechselgeld", "Buchen", "Münzgeldtabelle",
                "Berichte", "Managementberichte");
    }


}
