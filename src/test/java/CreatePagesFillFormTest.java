import operation.ContentOperation;
import operation.PageOperation;
import operation.UIOperation;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobjects.Content;
import pageobjects.MandatorPage;
import pageobjects.SubPageContent;

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

        ContentOperation.fillCreatePageAndSave(currentContent);

        assertThat("headline mismatch. Might not have been saved.", currentContent.getHeadlineText()
                , is("Ihre Daten wurden gespeichert!"));
    }

    /**
     * fails, if already exists.
     */
    @Test
    public void fillOrganisationseinheitAnlegenAndSave() {

        PageOperation.clickMainNaviThenSubmenu(mandatorPage, "Organisationseinheit", "Anlegen");

        ContentOperation.fillCreatePageAndSave(currentContent);

        assertThat("headline mismatch. Might not have been saved.", currentContent.getHeadlineText()
                , is("Ihre Daten wurden gespeichert!"));
    }

    /**
     * fails, if already exists
     */
    @Test
    public void fillGTUVersicherungenAnlegenAndSave() {
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
                UIOperation.typeInTextfield(tf, ContentOperation.TESTSTRING);
            }
        }

        ContentOperation.selectNotEmptyOptionOfSelects(currentContent);

        ContentOperation.addAddress(new SubPageContent(mandatorPage));

        ContentOperation.clickContinueAndCheckHeadlines(currentContent);
        ContentOperation.clickSaveButton(currentContent);

        assertThat(currentContent.getHeadlineText(), containsString("Ihre Daten wurden gespeichert!"));
    }


    /**
     * Note: while using HtmlUnitDriver, textfields should be filled after the subpages are filled, because HtmlUnitdriver
     * adds some spaces in textfield values after changing/reloading pages.
     */
    @Test
    public void fillGesellschaftAnlegenAndSave() {
        PageOperation.clickMainNaviThenSubmenu(mandatorPage, "Gesellschaft", "Anlegen");


        WebElement selectDivision = currentContent.getSelectById("AddSalesDivision.jspOrgUnitIDSelect");

        // zuvor erstellte Test-Organisationseinheit auswählen
        UIOperation.selectOptionByVisibleText(selectDivision, ContentOperation.TESTSTRING);

        WebElement selectCurrency = currentContent.getSelectById("AddSalesDivision.jspCurrencyIdSelect");
        UIOperation.selectOptionFromSelectElement(selectCurrency, 0);

        ContentOperation.addAddress(new SubPageContent(mandatorPage));

        addRegistryEntry();

        for (WebElement tf : currentContent.getTextfields()) {
            if (tf.getAttribute("id").equals("AddSalesDivisionNummerText"))
                UIOperation.typeInTextfield(tf, "2222");
            else if (tf.getAttribute("id").equals("AddSalesDivisionPlanBarQuotaText"))
                UIOperation.typeInTextfield(tf, "100");
            else if (tf.getAttribute("id").equals("AddSalesDivisionDateClosingText")) {/* do nothing */} else
                UIOperation.typeInTextfield(tf, ContentOperation.TESTSTRING);
        }

        ContentOperation.clickContinueAndCheckHeadlines(currentContent);

        ContentOperation.clickSaveButton(currentContent);

        // Dialog mit nachfrage zur erweiterung erscheint
        assertThat(currentContent.getHeadlineText(), containsString("Erweitern der Gesellschaftsdaten"));
    }

    @Test
    public void fillMarktAnlegenAndSave() {

        PageOperation.clickMainNaviThenSubmenu(mandatorPage, "Markt", "Anlegen");

        ContentOperation.addAddress(new SubPageContent(mandatorPage));

        ContentOperation.editCalendar(new SubPageContent(mandatorPage));

        ContentOperation.selectNotEmptyOptionOfSelects(currentContent);


        ContentOperation.fillSimpleTextfields(currentContent);
        UIOperation.typeInTextfield(currentContent.getTextfieldById("AddStoreStoreNumberText"), "0000");
        UIOperation.typeInTextfield(currentContent.getTextfieldById("AddStoreDateOpeningText"), "01.01.1991");


        ContentOperation.clickContinueAndCheckHeadlines(currentContent);

        ContentOperation.clickSaveButton(currentContent);


        // Dialog mit nachfrage ob detaildaten angelegt werden soll
        assertThat(currentContent.getHeadlineText(), containsString("Markt Detaildaten anlegen"));


    }




    // TODO auslagern
    private void addRegistryEntry() {

        for (WebElement button : currentContent.getSubPageButtons()) {
            if (button.getAttribute("id").contains("RegisterSubmit")) {
                UIOperation.click(button);
                break;
            }
        }

        SubPageContent registryContent = new SubPageContent(mandatorPage);
        ContentOperation.fillSimpleTextfields(registryContent);
        ContentOperation.selectNotEmptyOptionOfSelects(registryContent);
        UIOperation.click(registryContent.getApplyButton());

    }

    // nur um seiten mit anlegen option zu finden
//        @Test
    public void findPagesWithCreateSubmenu() {
        PageOperation.getMainNavisWithCreateSubmenu(mandatorPage);
    }


}
