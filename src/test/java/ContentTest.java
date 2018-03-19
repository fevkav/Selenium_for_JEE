import operation.PageOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageobjects.MandatorPage;
import pageobjects.RolePage;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ContentTest {

    private RolePage mandatorPage;

    @Before
    public void startLoginSelectMandatorRole() {
        mandatorPage = (RolePage) PageOperation.startLoginSelectRole("Mandator");

    }

    @Test
    public void checkHeadlineMandatorBearbeiten() {
        PageOperation.clickMainNaviThenSubmenu(mandatorPage, "Mandant", "Bearbeiten");
        assertThat("Wrong headline in content",
                mandatorPage.getCurrentContent().getHeadlineText(), is("Mandant bearbeiten"));
    }


    @Test
    public void checkLabelsForTextInputFieldsInMandantBearbeiten() {

        PageOperation.clickMainNaviThenSubmenu(mandatorPage, "Mandant", "Bearbeiten");
        mandatorPage.getCurrentContent().checkLabelsOfTextfields();
    }

    /**
     * Page "GTU Ansehen" has undisplayed link buttons?
     */
    @Test(expected = AssertionError.class)
    public void checkIfGTUAnsehenHasUndisplayedLinkButtons() {
        PageOperation.clickMainNaviThenSubmenu(mandatorPage, "GTU", "Ansehen");
        assertThat("GTU Ansehen has undisplayed linkbuttons", PageOperation.hasNotDisplayedElements(
                mandatorPage.getCurrentContent().getLinkButtons()), nullValue());
    }

    @Test
    public void clickAndCheckLinkTextsOfAllMainNaviElementsOfMandatorRole() {

        List<String> linkTextsExpected = ((MandatorPage) mandatorPage).getGermanMainNavis();

        assertThat("Wrong main navigation items", PageOperation.clickOnAllMainNavisAndReturnLinkTexts(mandatorPage),
                containsInAnyOrder(linkTextsExpected.toArray()));
    }


    @After
    public void quitDriver() {
        mandatorPage.quitDriver();
    }

}
