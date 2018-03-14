package pageobjects;

import operation.PageOperation;
import operation.UIOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

public class MandatorPageTest {

    private MandatorPage mandatorPage;


    @Before
    public void startAndLoginAndSelectMandatorRole() {
        mandatorPage = (MandatorPage) PageOperation.startLoginSelectRole("Mandator");
    }

    @Test
    public void checkAllMainNaviItems() {

        List<String> linkTextsExpected = Arrays.asList("Home", "Währungen", "Mandant", "Bank", "GTU", "Cashcenter",
                "Bankkonto", "Organisationseinheit", "Gesellschaft", "Markt", "Ebenen", "Workflow", "Kontenselektion",
                "GTU-Versicherungen", "Leistungsverzeichnisse", "Rahmenvertrag", "Wechselgeld", "Buchen", "Münzgeldtabelle",
                "Berichte", "Managementberichte");

        assertThat("Wrong main navigation items", mandatorPage.getAllMainNaviItemLinkTexts(),
                containsInAnyOrder(linkTextsExpected.toArray()));
    }

    @Test
    public void checkSubmenuItemsForMenuBank() {

        WebElement mainNaviBank = mandatorPage.getMainNaviItemElementByLinkText("Bank");

        UIOperation.click(mainNaviBank);

        List<String> linkTextsExpected = Arrays.asList("(De)Aktivieren", "Ansehen");

        assertThat("Wrong submenus for menu \"Bank\"", mandatorPage.getAllSubmenuItemLinkTexts(mainNaviBank),
                containsInAnyOrder(linkTextsExpected.toArray()));

    }

    @Test
    public void clickMainNaviWaehrungThenSubmenuZuweisenTest() {
        PageOperation.clickMainNaviItemThenSubmenuItem(mandatorPage, "Währungen", "Zuweisen");
    }


    @After
    public void closeDriver() {
        mandatorPage.quitDriver();
    }

}