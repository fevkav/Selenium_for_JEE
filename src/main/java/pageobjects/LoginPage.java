package pageobjects;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * First page you see after calling the root url.
 */
public class LoginPage extends Page {

    @FindBy(xpath = "//select[@id='headerSelectLanguage']")
    private WebElement selectLanguage;

    @FindBy(xpath = "//input[@id='LoginUserName']")
    private WebElement textinputUser;

    @FindBy(xpath = "//input[@id='LoginPassword']")
    private WebElement textinputPassword;

    @FindBy(xpath = "//input[@type='submit']")
    private WebElement submitButton;

    public LoginPage(WebDriver driver) {

        super(driver);
        PageFactory.initElements(driver, this);
    }

    public LoginPage() {
        super();
        PageFactory.initElements(driver, this);
    }

    /**
     *
     * @return True if the login form with language selection, username/password input and submit button
     * is clickable.
     */
    public boolean isLoginFormPresent() {

        String message = "Error: drop-down list for language selection not found in Login Page.";
        try {
            wait.until(ExpectedConditions.elementToBeClickable(selectLanguage));
            message = "Error: textinput for username not found in Login Page.";
            wait.until(ExpectedConditions.elementToBeClickable(textinputUser));
            message = "Error: textinput for password not found in Login Page.";
            wait.until(ExpectedConditions.elementToBeClickable(textinputPassword));
            message = "Error: submit button not found in Login Page.";
            wait.until(ExpectedConditions.elementToBeClickable(submitButton));

        } catch (TimeoutException e) {
            System.out.println(message);
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    public WebElement getTextinputUser() {
        return textinputUser;
    }

    public WebElement getTextinputPassword() {
        return textinputPassword;
    }

}
