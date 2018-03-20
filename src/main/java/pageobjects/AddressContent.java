package pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AddressContent extends Content {

    @FindBy(className = "mchsSetRecord2PerLine")
    private List<WebElement> adresses;

    @FindBy(id = "EditAddressTakeOnSubmit")
    private WebElement applyButton;     // TODO andere buttons auch mit pagefactory holen


    public AddressContent(Page page) {
        super(page);
    }

    public boolean containsContactEntry() {
        return adresses != null;
    }

    public WebElement getAddButton() {
        for (WebElement addButton : submitButtons) {
            if (addButton.getAttribute("value").contains("Hinzufügen")) {
                return addButton;
            }
        }
        return null;
    }

    public WebElement getApplyButton() {
        return applyButton;
    }
}
