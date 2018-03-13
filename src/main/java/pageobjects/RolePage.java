package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class RolePage extends Page {

    /**
     * This element appears after the role is selected, so it can't be initialized within this object instantiation,
     * because this page object contains the role selection page.
     */
    By roleChangeButtonLocator = By.id("topMenuButtonChangeRole");
    @FindBy(xpath = "//a[starts-with(@id, \"SelectRoleRole\")]")
    private List<WebElement> roleLinks;

    public RolePage() {
        super();
    }

    public RolePage(WebDriver driver) {
        super(driver);
    }

    public List<WebElement> getRoleLinkElements() {
        return roleLinks;
    }

//    public void clickOnRoleLinkAndCheckRole(WebElement roleLink) {
//
//        String roleLinkId = roleLink.getAttribute("id");
//
//        UIOperation.click(roleLink);
//
//        WebElement roleChangeButton = wait.until(ExpectedConditions.elementToBeClickable(roleChangeButtonLocator));
//        // TODO exception handling
//
//        String roleButtonTitle = roleChangeButton.getAttribute("title");
//
//        System.out.println("language: " + getLanguage());
//
//        if (roleButtonTitle.contains("Role") || roleButtonTitle.contains("Rolle") ) {
//
//        }
//    }


}
