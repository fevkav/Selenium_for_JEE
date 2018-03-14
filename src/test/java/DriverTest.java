import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import java.util.ArrayList;

public class DriverTest {

    private static ArrayList<WebDriver> drivers = new ArrayList<>();

    @Test
    public void chromeTest() {
        drivers.add(new ChromeDriver());
    }

    @Test
    public void firefoxTest() {
        drivers.add(new FirefoxDriver());
    }

    @Test
    public void edgeTest() {
        drivers.add(new EdgeDriver());
    }

    @Test
    public void htmlUnitTest() {
        drivers.add(new HtmlUnitDriver());
    }

    @Test
    public void phantomJSTest() {
        drivers.add(new PhantomJSDriver());
    }

    @After
    public void closeDrivers() {
        drivers.forEach(driver -> driver.quit());
    }
}
