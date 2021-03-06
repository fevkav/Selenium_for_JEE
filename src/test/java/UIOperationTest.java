
import operation.PageOperation;
import operation.UIOperation;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.UnexpectedTagNameException;
import pageobjects.LoginPage;
import pageobjects.RolePage;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

/* TODO bad practise. Test sollte unabhängig von nicht zu testenden objekten sein
 => textiput und select mocken */
public class UIOperationTest {

    private static final String userValue = "MaxMustermann@max.net";
    private static final String passwordValue = "Qjuz385#+>_";

    @Test
    public void typeInTextfieldTest() throws Exception {

        LoginPage page = new LoginPage();
        page.navigateToRootUrl();
        WebElement textInputUser = page.getTextinputUser();
        WebElement textInputPassword = page.getTextinputPassword();

        UIOperation.typeInTextfield(textInputUser, userValue);
        UIOperation.typeInTextfield(textInputPassword, passwordValue);

        assertThat("Could not type in textfield username",
                page.getTextinputUser().getAttribute("value"), is(userValue));

        assertThat("Could not type in textfield password",
                page.getTextinputPassword().getAttribute("value"), is(passwordValue));
        page.quitDriver();
    }


    @Test(expected = UnexpectedTagNameException.class)
    public void selectFromNonSelectElementShouldThrowException() {

        WebElement emptyElement = mock(WebElement.class);
        UIOperation.selectOptionFromSelectElement(emptyElement, "option 1");
    }

    // TODO bad smells
    @Test
    public void checkOptionsFromSelectInBankkontoAnlegen() {
        RolePage mandatorPage = (RolePage) PageOperation.startLoginSelectRole("Mandator");
        PageOperation.clickMainNaviThenSubmenu(mandatorPage, "Bankkonto", "Anlegen");

        List<String> expectedOptionsWaehrung = Arrays.asList("", "EUR");

        WebElement selectWaehrung = mandatorPage.getCurrentContent().getSelects().get(1);

        assertThat("option visible texts mismatch", UIOperation.getOptionsVisibleTextsFromSelect(selectWaehrung)
                , is(expectedOptionsWaehrung));

    }

    @Test
    public void selectOptionFromSelectByValueTest() {

        LoginPage page = new LoginPage(new ChromeDriver());
        page.navigateToRootUrl();

        UIOperation.selectOptionFromSelectElement(page.getSelectLanguage(), "en_GB");

        Select select = new Select(page.getSelectLanguage());
        assertThat("Select failed!",
                select.getFirstSelectedOption().getAttribute("value"), is("en_GB"));

        page.quitDriver();
    }


}