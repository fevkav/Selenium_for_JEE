package pageobjects;

import operation.UIOperation;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * First page you see after calling the root url, including the login.
 * To find WebElements within this page object, the PageFactory class is used.
 */
public class LoginPage extends Page {

    /**
     * The drop-down list for the language selection.
     */
    @FindBy(xpath = "//select[@id='headerSelectLanguage']")
    private WebElement selectLanguage;

    /**
     * The textinput field for the username.
     */
    @FindBy(xpath = "//input[@id='LoginUserName']")
    private WebElement textinputUser;

    /**
     * The textinput field for the password.
     */
    @FindBy(xpath = "//input[@id='LoginPassword']")
    private WebElement textinputPassword;

    /**
     * The submit button to submit the credentials.
     */
    @FindBy(xpath = "//input[@type='submit']")
    private WebElement submitButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage() {
        super();
    }

    /**
     * @return True if the login form with language selection, username/password input, submit button
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

    public Page navigateToLoginPageAndLogin() {

        navigateToRootUrl();
        UIOperation.typeInTextfield(textinputUser, config.getUser());
        UIOperation.typeInTextfield(textinputPassword, config.getPassword());
        UIOperation.click(submitButton);

        try {
            driver.findElement(By.id("LoginWrongUserNameOrPasswordMessage"));
        } catch (NoSuchElementException e) {
            return new RolePage(driver);
        }

        throw new RuntimeException("Wrong Username/Password");

    }

    public WebElement getSelectLanguage() {
        return selectLanguage;
    }

    public WebElement getTextinputUser() {
        return textinputUser;
    }

    public WebElement getTextinputPassword() {
        return textinputPassword;
    }
}
