import operation.PageOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageobjects.Page;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ContentTest {

    private Page mandatorPage;

    @Before
    public void startLoginSelectMandatorRoleClickMandantBearbeiten() {
        mandatorPage = PageOperation.startLoginSelectRole("Mandator");

    }

    @Test
    public void checkHeadlineMandatorBearbeiten() {
        PageOperation.clickMainNaviItemThenSubmenuItem(mandatorPage, "Mandant", "Bearbeiten");
        assertThat("Wrong headline in content",
                mandatorPage.getCurrentContent().getHeadlineText(), is("Mandant bearbeiten"));
    }

    @Test
    public void checkTextInputFieldsOfMandantBearbeiten() {
        PageOperation.clickMainNaviItemThenSubmenuItem(mandatorPage, "Mandant", "Bearbeiten");
        mandatorPage.getCurrentContent().printTextInputFieldsPretty();
    }

    @Test
    public void checkSelectsOfBankkontoAnlegen() {
        PageOperation.clickMainNaviItemThenSubmenuItem(mandatorPage, "Bankkonto", "Anlegen");
        mandatorPage.getCurrentContent().printSelectsPretty();
    }

    // "Geldtransportunternehmen ansehen" Seite hat nicht dargestellte link buttons
    @Test(expected = AssertionError.class)
    public void linkButtonsInGTUAnsehenTest() {
        PageOperation.clickMainNaviItemThenSubmenuItem(mandatorPage, "GTU", "Ansehen");
        assertThat("Number of link buttons mismatch", mandatorPage.getCurrentContent().getLinkButtons().size(),
                is(13));
    }

    @After
    public void quitDriver() {
        mandatorPage.quitDriver();
    }

}
