package pageobjects;

import operation.UIOperation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class RolePage extends Page {

    /**
     * This element appears after the role is selected, so it can't be initialized within this object instantiation,
     * because this page object contains the role selection page.
     */
    protected By roleChangeButtonLocator = By.id("topMenuButtonChangeRole");

    @FindBy(xpath = "//a[starts-with(@id, \"SelectRoleRole\")]")
    private List<WebElement> roleLinks;

    By subMenuItemLocator = By.cssSelector("div.mainNaviItemLevel2 > a.mainNaviItem");

    @FindBy(css = "div.mainNaviItemLevel1 > a.mainNaviItem")
    private List<WebElement> mainNaviItems;

    private Content currentContent;

    public RolePage() {
        super();
        currentContent = new Content(this);
    }

    public RolePage(WebDriver driver) {
        super(driver);
        currentContent = new Content(this);
    }

    public List<WebElement> getRoleLinkElements() {
        return roleLinks;
    }

    public Page selectRole(String role) {

        String roleLinkId = "SelectRoleRole_" + role + ((role.equals("SystemAdmin")) ? "_" : "_METRO AG");

        WebElement roleLink = wait.until(ExpectedConditions.elementToBeClickable(By.id(roleLinkId)));
        // TODO


        UIOperation.click(roleLink);

        System.out.println("Selected role is " + role);

        switch (role) {
            case "SystemAdmin":
                return new SystemAdminPage(driver);
            case "Mandator":
                return new MandatorPage(driver);
            case "Operator":
                return new OperatorPage(driver);
            case "Controller":
                return new ControllerPage(driver);
            case "Approver":
                return new ApproverPage(driver);
            default:
                throw new RuntimeException("Wrong role");
        }
    }

    public Page changeRole(String role) {
        WebElement roleChangebutton = wait.until(ExpectedConditions.elementToBeClickable(roleChangeButtonLocator));
        // TODO exception handling

        UIOperation.click(roleChangebutton);
        return selectRole(role);
    }


    public List<String> getAllMainNaviItemLinkTexts() {
        List<String> linkTexts = new ArrayList<>();
        mainNaviItems.forEach(item -> linkTexts.add(item.getText()));
        return linkTexts;
    }

    public WebElement getMainNaviItemElementByLinkText(String linktext) {
        return driver.findElement(By.linkText(linktext));
    }

    /**
     * NOTE: Main navi has to be clicked on.
     *
     * @param linktext the exact displayed link text in selected language
     * @return the submenu link as WebElement
     */
    public WebElement getSubMenuItemElementByLinkText(String linktext) {
        return driver.findElement(By.linkText(linktext));
    }

    /**
     * First clicks on given main menu. then returns the "create" submenu, if exists.
     *
     * @param mainNaviElement main menu element
     * @return the clicked create submenu. null if given main menu doesn't contain a create submenu
     */
    public WebElement getCreateSubmenu(WebElement mainNaviElement) {
        UIOperation.click(mainNaviElement);
        try {
            return driver.findElement(By.xpath("//a[contains(@href, \"add=click\")]"));
        } catch (Exception e) {
            return null;
        }
    }

    public List<String> getAllSubmenuItemLinkTexts() {

        List<String> linkTexts = new ArrayList<>();
        driver.findElements(subMenuItemLocator).forEach(item -> linkTexts.add(item.getText()));
        return linkTexts;
    }


    public void loadContent() {
        currentContent = currentContent.load();
    }

    @Override
    public Content getCurrentContent() {
        return currentContent;
    }

    public List<WebElement> getAllMainNaviItems() {
        return mainNaviItems;
    }
}
