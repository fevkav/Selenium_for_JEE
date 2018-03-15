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
import static org.hamcrest.Matchers.*;


public class MandatorPageTest {

    private MandatorPage mandatorPage;

    // TODO BeforeClass nutzen
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
    public void checkHeaderOfContentAfterClickMainNaviWaehrungThenSubmenuZuweisen() {
        PageOperation.clickMainNaviItemThenSubmenuItem(mandatorPage,
                "Währungen", "Zuweisen");

        assertThat("Header of content mismatch", mandatorPage.getCurrentContent().getHeadlineText(),
                equalTo("Währung zuweisen"));
    }

    @Test
    public void checkSubmitButtonsInBuchenFreigeben() {
        PageOperation.clickMainNaviItemThenSubmenuItem(mandatorPage,
                "Buchen", "Daten freigeben");

        assertThat(mandatorPage.getCurrentContent().getSubmitButtons().size(), is(3));
        assertThat(mandatorPage.getCurrentContent().getSubmitButtons().get(0).getAttribute("value"),
                is("Freigeben"));
        assertThat(mandatorPage.getCurrentContent().getSubmitButtons().get(1).getAttribute("value"),
                is("Ablehnen"));
        assertThat(mandatorPage.getCurrentContent().getSubmitButtons().get(2).getAttribute("value"),
                is("Abbrechen"));
    }

    @Test
    public void checkSubmitButtonsInAPageWithoutButtons() {
        PageOperation.clickMainNaviItemThenSubmenuItem(mandatorPage, "Bank", "Ansehen");
        assertThat(mandatorPage.getCurrentContent().getSubmitButtons().isEmpty(), is(true));
    }


    @After
    public void closeDriver() {
        mandatorPage.quitDriver();
    }

}