import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageobjects.LoginPage;
import pageobjects.Page;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


public class LoginPageTest {

    private static LoginPage loginpage;

    @Before
    public void setUp() {
        loginpage = new LoginPage();

    }

    @Test
    public void isLoginPageLoadedTest() {

        loginpage.navigateToRootUrl();
        assertThat("Login form is not present or is incorrect", loginpage.isLoginFormPresent(), is(true));
    }

    @Test
    public void navigateToLoginPageAndLoginTest() {
        Page pageAfterLogin = loginpage.navigateToLoginPageAndLogin();

        assertThat("Login failed! The page title ends with \"Login\"",
                pageAfterLogin.getTitle(), not(endsWith("Login")));


    }

    @After
    public void quitDriver() {
        loginpage.quitDriver();
    }

}
