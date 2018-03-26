package pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Represents the address edit page.
 */
public class SubPageContent extends Content {

    @FindBy(xpath = "//input[contains(@id, \"TakeOnSubmit\")]")
    private WebElement applyButton;

    @FindBy(id = "EditAddressSetAddSubmit")
    private WebElement addButton;


    public SubPageContent(Page page) {
        super(page);
    }

    public WebElement getAddButton() {
        return addButton;
    }

    public WebElement getApplyButton() {
        return applyButton;
    }
}
