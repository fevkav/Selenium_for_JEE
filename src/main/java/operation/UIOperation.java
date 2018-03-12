package operation;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 * This class provides basic operations to interact with the given page object, e.g. to type keys in a textinput field.
 */
public class UIOperation {
    /**
     * @param textinput Text input field
     * @param keys      the string to type in
     * @throws RuntimeException if textinput is not a input tag of type text
     */
    public static void typeInTextfield(WebElement textinput, String keys) throws RuntimeException {

        if (!textinput.getTagName().equals("input") && !textinput.getAttribute("type").equals("text")) {
            throw new RuntimeException("Given WebElement in typeInTextfield(WebElement, String) is not a textinput field");
        }

        textinput.sendKeys(keys);
    }

    /**
     * @param select      drop-down list as a html select-tag
     * @param optionValue
     * @throws org.openqa.selenium.support.ui.UnexpectedTagNameException if select is not a select tag
     */
    public static void selectOptionFromSelectElementByValue(WebElement select, String optionValue) {

        Select selectElement = new Select(select);
        selectElement.selectByValue(optionValue);
    }

    public static void click(WebElement element) {
        element.click();
    }


}
