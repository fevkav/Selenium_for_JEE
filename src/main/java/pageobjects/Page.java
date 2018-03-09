package pageobjects;

import config.ConfigProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Represents a Page. Includes basic methods to handle the WebDriver and to get basic information from the current page.
 */
public abstract class Page {

    protected WebDriver driver;

    protected WebDriverWait wait;

    private ConfigProperties config;


    public Page(WebDriver driver) {
        this.driver = driver;
        this.driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        this.wait = new WebDriverWait(driver, 30);
        config = new ConfigProperties();
    }

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

    public void quitDriver() {
        this.driver.quit();
    }

    /**
     * Returns the current title of this page.
     * @return
     */
    public String getTitle() {
        return this.driver.getTitle();
    }

    /**
     * Returns the current URL of this page.
     * @return
     */
    public String getUrl() {
        return this.driver.getCurrentUrl();
    }

    // TODO semantisch falsch, da nur auf den div-Block geschaut wird. macht nur sinn, wenn auf jeder seite ein logo sein soll
    // https://sqa.stackexchange.com/questions/12912/how-to-check-is-image-is-loaded-or-not-in-selenium
    public boolean isLogoDisplayed() {
        return this.driver.findElement(By.id(config.getlogoId())).isDisplayed();
    }

}
