package pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Represents the address edit page.
 */
public class AddressContent extends Content {

    @FindBy(className = "mchsSetRecord2PerLine")
    private List<WebElement> adresses;

    @FindBy(xpath = "//input[contains(@id, \"TakeOnSubmit\")]")
    private WebElement applyButton;

    @FindBy(id = "EditAddressSetAddSubmit")
    private WebElement addButton;


    public AddressContent(Page page) {
        super(page);
    }

    public WebElement getAddButton() {
        return addButton;
    }

    public WebElement getApplyButton() {
        return applyButton;
    }
}
