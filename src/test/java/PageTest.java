import config.ConfigProperties;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import pageobjects.Page;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;
import static org.hamcrest.core.StringStartsWith.startsWith;


public class PageTest {

    private static Page page;

    private static ArrayList<Page> pages = new ArrayList<>();

    private static ConfigProperties config = new ConfigProperties();

    @BeforeClass
    public static void setUp() {
        page = new MockPage();
        pages.add(page);
    }

    @Test
    public void navigateToRootUrlTest() {

        page.navigateToRootUrl();
        assertThat("Root URL mismatch", page.getUrl(), is(config.getRootUrl() + "Login.jsp"));
        assertThat("Title mismatch", page.getTitle(), is("METRO Cash Handling System - Login"));

    }

    @Test
    public void navigateToUrlTest() {
        page.navigateToUrl("http://wrongxfcurl.de/");
        assertThat(page.getTitle(), not(startsWith("METRO Cash Handling System")));

        // TODO
    }

    @AfterClass
    public static void closeDrivers() {
        pages.forEach(page -> page.quitDriver());
    }

}
