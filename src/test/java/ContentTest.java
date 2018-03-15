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
        PageOperation.clickMainNaviItemThenSubmenuItem(mandatorPage, "Mandant", "Bearbeiten");
    }

    @Test
    public void checkHeadlineMandatorBearbeiten() {
        assertThat("Wrong headline in content",
                mandatorPage.getCurrentContent().getHeadline(), is("Mandant bearbeiten"));
    }

    @Test
    public void checkTextInputFieldsOfMandantBearbeiten() {
        mandatorPage.getCurrentContent().printTextInputFieldsPretty();
    }

    @After
    public void quitDriver() {
        mandatorPage.quitDriver();
    }

}
