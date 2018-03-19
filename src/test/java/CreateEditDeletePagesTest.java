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

        fillBankkontoAnlegen("TESTEINGABE");

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

        fillBankkontoAnlegen("TESTEDITTED");

        assertThat("headline mismatch. Might not have been editted.", mandatorPage.getCurrentContent().getHeadlineText()
                , is("Ihre Daten wurden gespeichert!"));


    }

    private void fillBankkontoAnlegen(String testInput) {

        List<WebElement> textfields = mandatorPage.getCurrentContent().getTextfields();
        List<WebElement> selects = mandatorPage.getCurrentContent().getSelects();
        List<WebElement> submitButtons = mandatorPage.getCurrentContent().getSubmitButtons();

        // Textfelder füllen
        for (WebElement tf : textfields) {
            UIOperation.typeInTextfield(tf, testInput);
        }

        // 1. index aus selects auswählen
        for (WebElement select : selects) {
            UIOperation.selectOptionFromSelectElement(select, 1);
        }

        submitButtons.forEach(btn -> System.out.println(btn.getAttribute("value")));
        // Weiter klicken
        for (WebElement button : submitButtons) {
            if (button.getAttribute("value").contains("Weiter")) {
                UIOperation.click(button);
                break;
            }
        }

        for (WebElement button : submitButtons) {
            if (button.getAttribute("value").contains("Speichern")) {
                UIOperation.click(button);
                break;
            }
        }

    }


}
