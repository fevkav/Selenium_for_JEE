package operation;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobjects.LoginPage;

import static org.junit.Assert.*;

public class UIOperationTest {

    private UIOperation operation;
    private LoginPage loginPage;

    @Before
    public void setUp() throws Exception {
        operation = new UIOperation();
        loginPage = new LoginPage(new ChromeDriver());
        loginPage.navigateToRootUrl();
    }

    @Test
    public void typeInTextfield() throws Exception {
        operation.typeInTextfield(loginPage.getTextinputUser(), "User");

    }

}