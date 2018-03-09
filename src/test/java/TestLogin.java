import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.Base64;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


/**
 * @author mtag
 *
 */
@FixMethodOrder(MethodSorters.JVM)
@Ignore
public class TestLogin {

    private static WebDriver driver;

    @BeforeClass
    public static void setUp() {
        // driver = new HtmlUnitDriver();
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://10.16.62.36:8080/mchs/Login.jsp");
    }

    @Test
    public void doLogin() {
        verifyTitle("METRO Cash Handling System - Login");
        enterUserName("Manuel.Klein@metrosystems.net");
        enterPassword("Qmx1M00heDAzJA==");
        pressSubmit();
    }

    @Test
    public void doSelectMandant() {
        verifyTitle("METRO Cash Handling System");
        clickLink();
    }

    private void clickLink() {
        WebElement element = driver.findElement(By.id("SelectRoleRole_Mandator_METRO AG"));
        element.click();
    }

    private void pressSubmit() {
        WebElement element = driver.findElement(By.id("LoginLogin"));
        element.click();
    }

    private void verifyTitle(String expectedTitle) {
        String actualTitle = driver.getTitle();
        assertThat(actualTitle, equalTo(expectedTitle));
    }

    private void enterUserName(String userName) {
        WebElement element = driver.findElement(By.id("LoginUserName"));
        element.sendKeys(userName);
    }

    private void enterPassword(String password) {
        WebElement element = driver.findElement(By.id("LoginPassword"));
        element.sendKeys(new String(Base64.getDecoder().decode(password)));
    }

}
