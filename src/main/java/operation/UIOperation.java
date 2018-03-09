package operation;

import org.openqa.selenium.WebElement;
import pageobjects.Page;

public class UIOperation {

//    private Page page;

    public UIOperation() {
//        this.page = page;
    }

    // TODO wirf exception wenn kein texteingabefeld
    public void typeInTextfield(WebElement textinput, String keys) {
        textinput.sendKeys(keys);
    }
}
