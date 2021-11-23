import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobjects.Content;
import pageobjects.OperatorPage;

import static operation.ContentOperation.*;
import static operation.PageOperation.clickMainNaviThenSubmenu;
import static operation.PageOperation.startLoginSelectRole;
import static operation.UIOperation.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CreatePagesOperatorTest {

    public static final String SURROGAT_ERFASSEN = "Surrogat erfassen";
    public static final String IHRE_DATEN_WURDEN_GESPEICHERT = "Ihre Daten wurden gespeichert!";
    public static final String ANLEGEN = "Anlegen";
    private static OperatorPage operatorPage;
    private Content currentContent;

    @BeforeClass
    public static void startLoginSelectOperatorRole() {
        operatorPage = (OperatorPage) startLoginSelectRole("Operator", new ChromeDriver());
    }

    @Before
    public void setUpContent() {
        currentContent = operatorPage.getCurrentContent();
    }

    @AfterClass
    public static void closeDriver() {
        operatorPage.quitDriver();
    }


    @Test
    public void fillKontoauszugsdatenSurrogatAndSave() {
        clickMainNaviThenSubmenu(operatorPage, "Kontoauszugsdaten", SURROGAT_ERFASSEN);

        currentContent = operatorPage.getCurrentContent();
        selectNotEmptyOptionOfSelects(currentContent);

        fillSimpleTextfields(currentContent);
        fillDateTextfields(currentContent);
        typeInTextfield(currentContent.getTextfieldById("EditBankTransactionSurrogateTransactionAmountText"),
                "100");
        click(currentContent.getContinueButton());
        click(currentContent.getButtonByValue("Datei erstellen"));

        assertThat(currentContent.getHeadlineText(), is(IHRE_DATEN_WURDEN_GESPEICHERT));

    }

    @Test
    public void fillWechselgeldSurrogatAndSave() {
        clickMainNaviThenSubmenu(operatorPage, "Wechselgeld", SURROGAT_ERFASSEN);

        // wählt GWS Gmbh als GTU, Cash Center wird automatisch ausgefüllt. TODO
        selectOptionFromSelectElement(currentContent.getSelects().get(0), 2);

        clickContinueAndCheckHeadlines(currentContent);

        // Auswahl Gesellschaft und Markt
        selectNotEmptyOptionOfSelects(currentContent);

        // Bestellsumme eingeben
        typeInTextfield(currentContent.getTextfieldById("EditChangeTransactionRecordSurrogatechangeAmount"), "123,45");

        // TODO Denominationen

        clickContinueAndCheckHeadlines(currentContent);

        // TODO weitere Surrogate hinzufügen

        clickSaveButton(currentContent);

        assertThat(currentContent.getHeadlineText(), is(IHRE_DATEN_WURDEN_GESPEICHERT));

    }

    @Test
    public void fillAuszaehldatenSurrogatAndSave() {
        clickMainNaviThenSubmenu(operatorPage, "Auszähldaten", SURROGAT_ERFASSEN);

        // wählt GWS Gmbh als GTU, Cash Center wird automatisch ausgefüllt.  TODO
        selectOptionFromSelectElement(currentContent.getSelects().get(0), 2);

        clickContinueAndCheckHeadlines(currentContent);

        selectNotEmptyOptionOfSelects(currentContent);

        typeInTextfield(currentContent.getTextfieldById("EditCashCountTransactionRecordSurrogatetargetAmount"), "123,45");
        typeInTextfield(currentContent.getTextfieldById("EditCashCountTransactionRecordSurrogatenoteAmount"), "123,45");
        typeInTextfield(currentContent.getTextfieldById("EditCashCountTransactionRecordSurrogatecoinAmount"), "123,45");

        clickContinueAndCheckHeadlines(currentContent);

        clickSaveButton(currentContent);

        assertThat(currentContent.getHeadlineText(), is(IHRE_DATEN_WURDEN_GESPEICHERT));
    }

    @Test
    public void fillReklamationenAnlegenAndSave() {

        clickMainNaviThenSubmenu(operatorPage, "Reklamationen", ANLEGEN);

        selectNotEmptyOptionOfSelects(currentContent);

        // wählt GWS GmbH als GTU, da beispielsweise Arndt keinen Cash Center aufgelistet hat.
        selectOptionFromSelectElement(currentContent.getSelectById("AddReclamationcashTransCom1Id"), 2);
        selectOptionFromSelectElement(currentContent.getSelectById("AddReclamationcashTransCom2Id"), 2);

        // Betrag eingeben
        typeInTextfield(currentContent.getTextfieldById("AddReclamationamount"), "123");

        clickContinueAndCheckHeadlines(currentContent);

        clickSaveButton(currentContent);

        assertThat(currentContent.getHeadlineText(), is(IHRE_DATEN_WURDEN_GESPEICHERT));

    }

}
