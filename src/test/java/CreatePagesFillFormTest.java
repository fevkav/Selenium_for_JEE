import operation.ContentOperation;
import operation.PageOperation;
import operation.UIOperation;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobjects.MandatorPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CreatePagesFillFormTest {

    private static MandatorPage mandatorPage;

    @BeforeClass
    public static void getMainNavisWithCreateSubmenu() {
        mandatorPage = (MandatorPage) PageOperation.startLoginSelectRole("Mandator", new ChromeDriver());

    }

    @AfterClass
    public static void closeDriver() {
        mandatorPage.quitDriver();
    }

    @Test
    public void fillBankkontoAnlegenFormAndSave() {

        PageOperation.clickMainNaviThenSubmenu(mandatorPage, "Bankkonto", "Anlegen");

        ContentOperation.fillCreatePage(mandatorPage.getCurrentContent());

        assertThat("headline mismatch. Might not have been saved.", mandatorPage.getCurrentContent().getHeadlineText()
                , is("Ihre Daten wurden gespeichert!"));
    }

    @Test
    public void fillOrganisationseinheitAnlegen() {

        PageOperation.clickMainNaviThenSubmenu(mandatorPage, "Organisationseinheit", "Anlegen");

        ContentOperation.fillCreatePage(mandatorPage.getCurrentContent());

        assertThat("headline mismatch. Might not have been saved.", mandatorPage.getCurrentContent().getHeadlineText()
                , is("Ihre Daten wurden gespeichert!"));
    }


    @Test//(expected = RuntimeException.class)
    public void fillGTUVersicherungenAnlegenShouldFail() {
        PageOperation.clickMainNaviThenSubmenu(mandatorPage, "GTU-Versicherungen", "Anlegen");

        // auf den als erstes aufgelisteten Geldtransportunternehmen klicken, um auf anlegen formular zu gelangen.
        UIOperation.click(mandatorPage.getCurrentContent().getLinkButtons().get(0));

        ContentOperation.fillCreatePage(mandatorPage.getCurrentContent());


    }



    //    @Test
    public void findPagesWithCreateSubmenu() {
        PageOperation.getMainNavisWithCreateSubmenu(mandatorPage);
    }

}
