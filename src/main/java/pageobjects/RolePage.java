package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class RolePage extends Page {

    @FindBy(xpath = "//a[starts-with(@id, \"SelectRoleRole\")]")
    private List<WebElement> roleElements;

    public RolePage() {
        super();
    }

    public RolePage(WebDriver driver) {
        super(driver);
    }

    public List<WebElement> getRoleElements() {
        return roleElements;
    }
}
