import operation.ContentOperation;
import operation.PageOperation;
import operation.UIOperation;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobjects.MandatorPage;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CreateEditDeletePagesTest {

    private static MandatorPage mandatorPage;

    @BeforeClass
    public static void getMainNavisWithCreateSubmenu() {
        mandatorPage = (MandatorPage) PageOperation.startLoginSelectRole("Mandator", new ChromeDriver());
        // nur um zu sehen, welche Seiten ein Anlegen unterpunkt haben
//        PageOperation.getMainNavisWithCreateSubmenu(mandatorPage);
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
    public void editBankkontoTest() {
        PageOperation.clickMainNaviThenSubmenu(mandatorPage, "Bankkonto", "Bearbeiten");

        List<WebElement> selectBankkonto = mandatorPage.getCurrentContent().getSelects();

        UIOperation.selectOptionByVisibleText(selectBankkonto.get(0), "TESTEINGABE ; TESTEINGABE ; Commerzbank AG");

        // auf bearbeiten klicken
        UIOperation.click(mandatorPage.getCurrentContent().getSubmitButtons().get(0));

        assertThat("headline mismatch. Might not have been editted.", mandatorPage.getCurrentContent().getHeadlineText()
                , is("Ihre Daten wurden gespeichert!"));


    }

}
