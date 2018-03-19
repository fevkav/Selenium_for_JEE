import operation.PageOperation;
import operation.UIOperation;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobjects.MandatorPage;

import java.util.List;

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
    public void createBankkontoTest() {
        PageOperation.clickMainNaviThenSubmenu(mandatorPage, "Bankkonto", "Anlegen");
        List<WebElement> textfieldsBankkontoAnlegen = mandatorPage.getCurrentContent().getTextfields();
        List<WebElement> selectsBankkontoAnlegen = mandatorPage.getCurrentContent().getSelects();

        for (WebElement tf : textfieldsBankkontoAnlegen) {
            UIOperation.typeInTextfield(tf, "TESTEINGABE");
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}
