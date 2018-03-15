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
                mandatorPage.getCurrentContent().getHeadline(), is("Mandant bearbeiten"));
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







    @After
    public void quitDriver() {
        mandatorPage.quitDriver();
    }

}
