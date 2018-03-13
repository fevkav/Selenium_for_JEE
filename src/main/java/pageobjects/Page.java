package pageobjects;

import config.ConfigProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Represents a Page. Includes basic methods to handle the WebDriver and to get basic information from the current page.
 */
public abstract class Page {

    protected WebDriver driver;

    protected WebDriverWait wait;

    protected ConfigProperties config;

    /**
     * @param driver the driver to use for this page.
     */
    public Page(WebDriver driver) {
        this.driver = driver;
        this.driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        this.wait = new WebDriverWait(driver, 30);
        config = new ConfigProperties();
        PageFactory.initElements(driver, this);
    }

    /**
     * Constructor with a HtmlUnitDriver.
     */
    public Page() {
        this(new HtmlUnitDriver());
    }

    /**
     * Navigates to the root url specified in application.properties.
     * <p>NOTE: calling without been logged in, the url ends with "Login.jsp"</p>
     */
    public void navigateToRootUrl() {
        this.driver.get(config.getRootUrl());
    }

    public void navigateToUrl(String url) {
        this.driver.get(url);
    }

    /**
     *
     */
    public void quitDriver() {
        this.driver.quit();
    }

    /**
     * @return the current title of this page.
     */
    public String getTitle() {
        return this.driver.getTitle();
    }

    /**
     *
     * @return the current URL of this page.
     */
    public String getUrl() {
        return this.driver.getCurrentUrl();
    }

    public String getLanguage() {
        int startIndexOfLanguage = getUrl().indexOf("language=") + 9;
        return getUrl().substring(startIndexOfLanguage, startIndexOfLanguage + 5);
    }

    // TODO semantisch falsch, da nur auf den div-Block geschaut wird. macht nur sinn, wenn auf jeder seite ein logo sein soll
    // https://sqa.stackexchange.com/questions/12912/how-to-check-is-image-is-loaded-or-not-in-selenium
//    public boolean isLogoDisplayed() {
//        return this.driver.findElement(By.id(config.getlogoId())).isDisplayed();
//    }

}
