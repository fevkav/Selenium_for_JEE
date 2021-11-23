import operation.ContentOperation;
import operation.PageOperation;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobjects.Content;
import pageobjects.MandatorPage;
import pageobjects.SubPageContent;

import static operation.ContentOperation.*;
import static operation.PageOperation.clickMainNaviThenSubmenu;
import static operation.UIOperation.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

public class CreatePagesMandatorTest {

    public static final String IHRE_DATEN_WURDEN_GESPEICHERT = "Ihre Daten wurden gespeichert!";
    public static final String ANLEGEN = "Anlegen";
    public static final String BANKKONTO = "Bankkonto";
    private static MandatorPage mandatorPage;
    private Content currentContent;

    @BeforeClass
    public static void startLoginSelectMandatorRole() {
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

        clickMainNaviThenSubmenu(mandatorPage, BANKKONTO, ANLEGEN);

        fillCreatePageAndSave(currentContent);

        assertThat("headline mismatch. Might not have been saved.", currentContent.getHeadlineText()
                , is(IHRE_DATEN_WURDEN_GESPEICHERT));
    }

    /**
     * fails, if already exists.
     */
    @Test
    public void fillOrganisationseinheitAnlegenAndSave() {

        clickMainNaviThenSubmenu(mandatorPage, "Organisationseinheit", ANLEGEN);

        fillCreatePageAndSave(currentContent);

        assertThat("headline mismatch. Might not have been saved.", currentContent.getHeadlineText()
                , is(IHRE_DATEN_WURDEN_GESPEICHERT));
    }

    /**
     * fails, if already exists
     */
    @Test
    public void fillGTUVersicherungenAnlegenAndSave() {
        clickMainNaviThenSubmenu(mandatorPage, "GTU-Versicherungen", ANLEGEN);

        // auf den als erstes aufgelisteten Geldtransportunternehmen klicken, um auf anlegen formular zu gelangen.
        click(currentContent.getLinkButtons().get(0));

        // textfelder mit Zahlenwerte, Datum und string füllen
        for (WebElement tf : currentContent.getTextfields()) {
            if (tf.getAttribute("name").contains("sum_")) {
                typeInTextfield(tf, "10");
            } else if (tf.getAttribute("name").contains("runTime") || tf.getAttribute("name").contains("Date")) {
                typeInTextfield(tf, "01.01.2018");
            } else {
                typeInTextfield(tf, ContentOperation.TESTSTRING);
            }
        }

        selectNotEmptyOptionOfSelects(currentContent);

        addAddress(new SubPageContent(mandatorPage));

        clickContinueAndCheckHeadlines(currentContent);
        clickSaveButton(currentContent);

        assertThat(currentContent.getHeadlineText(), containsString(IHRE_DATEN_WURDEN_GESPEICHERT));
    }


    /**
     * Note: while using HtmlUnitDriver, textfields should be filled after the subpages are filled, because HtmlUnitdriver
     * adds some spaces in textfield values after changing/reloading pages.
     */
    @Test
    public void fillGesellschaftAnlegenAndSave() {
        clickMainNaviThenSubmenu(mandatorPage, "Gesellschaft", ANLEGEN);


        WebElement selectDivision = currentContent.getSelectById("AddSalesDivision.jspOrgUnitIDSelect");

        // zuvor erstellte Test-Organisationseinheit auswählen
        selectOptionByVisibleText(selectDivision, ContentOperation.TESTSTRING);

        WebElement selectCurrency = currentContent.getSelectById("AddSalesDivision.jspCurrencyIdSelect");
        selectOptionFromSelectElement(selectCurrency, 0);

        addAddress(new SubPageContent(mandatorPage));

        addRegistryEntry();

        for (WebElement tf : currentContent.getTextfields()) {
            if (tf.getAttribute("id").equals("AddSalesDivisionNummerText"))
                typeInTextfield(tf, "2222");
            else if (tf.getAttribute("id").equals("AddSalesDivisionPlanBarQuotaText"))
                typeInTextfield(tf, "100");
            else if (tf.getAttribute("id").equals("AddSalesDivisionDateClosingText")) {/* do nothing */} else
                typeInTextfield(tf, ContentOperation.TESTSTRING);
        }

        clickContinueAndCheckHeadlines(currentContent);

        clickSaveButton(currentContent);

        // Dialog mit nachfrage zur erweiterung erscheint
        assertThat(currentContent.getHeadlineText(), containsString("Erweitern der Gesellschaftsdaten"));
    }

    @Test
    public void fillMarktAnlegenAndSave() {

        clickMainNaviThenSubmenu(mandatorPage, "Markt", ANLEGEN);

        addAddress(new SubPageContent(mandatorPage));

        editCalendar(new SubPageContent(mandatorPage));

        selectNotEmptyOptionOfSelects(currentContent);


        fillSimpleTextfields(currentContent);
        typeInTextfield(currentContent.getTextfieldById("AddStoreStoreNumberText"), "0000");
        typeInTextfield(currentContent.getTextfieldById("AddStoreDateOpeningText"), "01.01.1991");


        clickContinueAndCheckHeadlines(currentContent);

        clickSaveButton(currentContent);


        // Dialog mit nachfrage ob detaildaten angelegt werden soll
        assertThat(currentContent.getHeadlineText(), containsString("Markt Detaildaten anlegen"));
    }

    // TODO auslagern
    private void addRegistryEntry() {

        for (WebElement button : currentContent.getSubPageButtons()) {
            if (button.getAttribute("id").contains("RegisterSubmit")) {
                click(button);
                break;
            }
        }

        SubPageContent registryContent = new SubPageContent(mandatorPage);
        fillSimpleTextfields(registryContent);
        selectNotEmptyOptionOfSelects(registryContent);
        selectNotEmptyOptionOfSelects(currentContent);

    }

    @Test
    public void fillKontenselektionAnlegenAndSave() {
        clickMainNaviThenSubmenu(mandatorPage, "Kontenselektion", ANLEGEN);

        fillSimpleTextfields(currentContent);
        selectNotEmptyOptionOfSelects(currentContent);

        clickContinueAndCheckHeadlines(currentContent);
        clickSaveButton(currentContent);

        assertThat(currentContent.getHeadlineText(), is(IHRE_DATEN_WURDEN_GESPEICHERT));
    }

    @Test
    public void fillRahmenvertragAnlegenAndSave() {

        clickMainNaviThenSubmenu(mandatorPage, "Rahmenvertrag", ANLEGEN);

        fillSimpleTextfields(currentContent);
        fillDateTextfields(currentContent);

        selectOptionFromSelectElement(currentContent.getSelectById("AddFrameContractSalesDivisionIdSelect"),
                1);

        // nach Auswahl der Gesellschaft ladet Seite neu, daher aktuellen Content holen.
        currentContent = mandatorPage.getCurrentContent();

        selectOptionFromSelectElement(currentContent.getSelectById("AddFrameContractCashTransComIdSelect"), 0);

        clickContinueAndCheckHeadlines(currentContent);
        clickSaveButton(currentContent);

        assertThat(currentContent.getHeadlineText(), is(IHRE_DATEN_WURDEN_GESPEICHERT));
    }


}
