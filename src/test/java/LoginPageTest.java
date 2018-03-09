import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageobjects.LoginPage;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class LoginPageTest {

    private static LoginPage loginpage;

    @Before
    public void setUp() {
        loginpage = new LoginPage();
        // the loginpage is also the root page
        loginpage.navigateToRootUrl();
    }

    /**
     * NOTE: testing of title and url of the Login Page is provided in PageTest, because it's the same page
     */
    @Test
    public void isLoginPageLoadedTest() {

        assertThat("Login form is not present or is incorrect", loginpage.isLoginFormPresent(), is(true));
    }

    @After
    public void quitDriver() {
        loginpage.quitDriver();
    }

}
