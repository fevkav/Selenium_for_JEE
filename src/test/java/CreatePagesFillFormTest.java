import operation.ContentOperation;
import operation.PageOperation;
import operation.UIOperation;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobjects.AddressContent;
import pageobjects.Content;
import pageobjects.MandatorPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

public class CreatePagesFillFormTest {

    private static MandatorPage mandatorPage;
    private Content currentContent;

    @BeforeClass
    public static void getMainNavisWithCreateSubmenu() {
        mandatorPage = (MandatorPage) PageOperation.startLoginSelectRole("Mandator", new ChromeDriver());
    }

    @AfterClass
    public static void closeDriver() {
        mandatorPage.quitDriver();
    }

    @Before
    public void setUp() throws Exception {
        currentContent = mandatorPage.getCurrentContent();
    }

    /**
     * fails, if already exists
     */
    @Test
    public void fillBankkontoAnlegenFormAndSave() {

        PageOperation.clickMainNaviThenSubmenu(mandatorPage, "Bankkonto", "Anlegen");

        ContentOperation.fillCreatePage(currentContent);

        assertThat("headline mismatch. Might not have been saved.", currentContent.getHeadlineText()
                , is("Ihre Daten wurden gespeichert!"));
    }

    /**
     * fails, if already exists.
     */
    @Test
    public void fillOrganisationseinheitAnlegen() {

        PageOperation.clickMainNaviThenSubmenu(mandatorPage, "Organisationseinheit", "Anlegen");

        ContentOperation.fillCreatePage(currentContent);

        assertThat("headline mismatch. Might not have been saved.", currentContent.getHeadlineText()
                , is("Ihre Daten wurden gespeichert!"));
    }

    /**
     * fails, if already exists
     */
    @Test//(expected = RuntimeException.class)
    public void fillGTUVersicherungenAnlegenShouldFail() {
        PageOperation.clickMainNaviThenSubmenu(mandatorPage, "GTU-Versicherungen", "Anlegen");

        // auf den als erstes aufgelisteten Geldtransportunternehmen klicken, um auf anlegen formular zu gelangen.
        UIOperation.click(currentContent.getLinkButtons().get(0));

        // textfelder mit Zahlenwerte, Datum und string füllen
        for (WebElement tf : currentContent.getTextfields()) {
            if (tf.getAttribute("name").contains("sum_")) {
                UIOperation.typeInTextfield(tf, "10");
            } else if (tf.getAttribute("name").contains("runTime") || tf.getAttribute("name").contains("Date")) {
                UIOperation.typeInTextfield(tf, "01.01.2018");
            } else {
                UIOperation.typeInTextfield(tf, "Testinput");
            }
        }

        ContentOperation.selectNotEmptyOptionOfSelects(currentContent);

        ContentOperation.addAddress(new AddressContent(mandatorPage));

        String headlineBefore = currentContent.getHeadlineText();
        ContentOperation.clickContinueAndCheckHeadlines(currentContent, headlineBefore);
        ContentOperation.clickSaveButton(currentContent);

        assertThat(currentContent.getHeadlineText(), containsString("Ihre Daten wurden gespeichert!"));
    }

    // nur um seiten mit anlegen option zu finden
//        @Test
    public void findPagesWithCreateSubmenu() {
        PageOperation.getMainNavisWithCreateSubmenu(mandatorPage);
    }


}
