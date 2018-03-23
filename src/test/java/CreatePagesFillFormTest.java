import operation.ContentOperation;
import operation.PageOperation;
import operation.UIOperation;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebElement;
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
        mandatorPage = (MandatorPage) PageOperation.startLoginSelectRole("Mandator");
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
                UIOperation.typeInTextfield(tf, "Testinput");
            }
        }

        ContentOperation.selectNotEmptyOptionOfSelects(currentContent);

        ContentOperation.addAddress(new AddressContent(mandatorPage));

        ContentOperation.clickContinueAndCheckHeadlines(currentContent);
        ContentOperation.clickSaveButton(currentContent);

        assertThat(currentContent.getHeadlineText(), containsString("Ihre Daten wurden gespeichert!"));
    }

    @Test
    public void fillGesellschaftAnlegenAndSave() {
        PageOperation.clickMainNaviThenSubmenu(mandatorPage, "Gesellschaft", "Anlegen");

        ContentOperation.fillSimpleTextfields(currentContent);

        WebElement textfieldNumber = currentContent.getTextfieldById("AddSalesDivisionNummerText");
        UIOperation.typeInTextfield(textfieldNumber, "0000");

        WebElement textfieldPlanbarQuote = currentContent.getTextfieldById("AddSalesDivisionPlanBarQuotaText");
        UIOperation.typeInTextfield(textfieldPlanbarQuote, "100");

        WebElement selectDivision = currentContent.getSelectById("AddSalesDivision.jspOrgUnitIDSelect");
        UIOperation.selectOptionByVisibleText(selectDivision, ContentOperation.TESTSTRING);

        WebElement selectCurrency = currentContent.getSelectById("AddSalesDivision.jspCurrencyIdSelect");
        UIOperation.selectOptionFromSelectElement(selectCurrency, 0);

        ContentOperation.addAddress(new AddressContent(mandatorPage));


        addRegistryEntry();

        ContentOperation.clickContinueAndCheckHeadlines(currentContent);

        ContentOperation.clickSaveButton(currentContent);

        // TODO

        assertThat(currentContent.getHeadlineText(), containsString("Ihre Daten wurden gespeichert!"));

    }

    // TODO auslagern?
    private void addRegistryEntry() {

        for (WebElement button : currentContent.getSubPageButtons()) {
            if (button.getAttribute("id").contains("RegisterSubmit")) {
                UIOperation.click(button);
                break;
            }
        }

        AddressContent registryContent = new AddressContent(mandatorPage);
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
