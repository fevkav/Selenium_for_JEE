import operation.PageOperation;
import operation.UIOperation;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import pageobjects.MandatorPage;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CreateEditDeletePagesTest {

    private static MandatorPage mandatorPage;

    @BeforeClass
    public static void getMainNavisWithCreateSubmenu() {
        mandatorPage = (MandatorPage) PageOperation.startLoginSelectRole("Mandator", new ChromeDriver());
        // nur um zu sehen, welche Seiten ein Anlegen unterpunkt haben, sollte entfernt werden
        PageOperation.getMainNavisWithCreateSubmenu(mandatorPage);
    }

    @AfterClass
    public static void closeDriver() {
        mandatorPage.quitDriver();
    }

    @Test
    public void fillBankkontoAnlegenFormAndSave() {

        PageOperation.clickMainNaviThenSubmenu(mandatorPage, "Bankkonto", "Anlegen");

        List<WebElement> textfields = mandatorPage.getCurrentContent().getTextfields();
        List<WebElement> selects = mandatorPage.getCurrentContent().getSelects();
        List<WebElement> submitButtons = mandatorPage.getCurrentContent().getSubmitButtons();


        // Textfelder füllen
        for (WebElement tf : textfields) {
            UIOperation.typeInTextfield(tf, "TESTEINGABE");
        }

        // 1. index aus selects auswählen
        for (WebElement select : selects) {
            Select selectElement = new Select(select);
            selectElement.selectByIndex(1);
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

        assertThat("headline mismatch. Might not have been saved.", mandatorPage.getCurrentContent().getHeadlineText()
                , is("Ihre Daten wurden gespeichert!"));





    }


}
