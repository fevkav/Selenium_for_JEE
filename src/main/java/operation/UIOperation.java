package operation;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

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
            throw new RuntimeException(
                    "Given WebElement is not a textinput field");
        }

        textinput.clear();
        textinput.sendKeys(keys);
        if (!textinput.getAttribute("id").toLowerCase().contains("password"))
            System.out.println("type \"" + keys + "\" in textfield id: " + textinput.getAttribute("id"));
    }

    /**
     * Selects option from drop-down by option value
     * @param select      drop-down list as a html select-tag
     * @param optionValue option's value to select
     * @throws org.openqa.selenium.support.ui.UnexpectedTagNameException if select is not a select tag
     */
    public static void selectOptionFromSelectElement(WebElement select, String optionValue) {

        Select selectElement = new Select(select);
        selectElement.selectByValue(optionValue);

    }

    /**
     * Selects option from drop-down by index
     *
     * @param select drop-down list as a html select-tag
     * @param index  option's index to select
     */
    public static void selectOptionFromSelectElement(WebElement select, int index) {

        Select selectElement = new Select(select);
        selectElement.selectByIndex(index);

    }

    /**
     * Selects option from drop-down by visible text
     *
     * @param select      drop-down list as a html select-tag
     * @param visibleText option's visible text to select
     */
    public static void selectOptionByVisibleText(WebElement select, String visibleText) {

        Select selectElement = new Select(select);
        selectElement.selectByVisibleText(visibleText);

    }

    /**
     * @param select have to be a select tag
     * @return all visible texts of the options in given WebElement
     * @throws org.openqa.selenium.support.ui.UnexpectedTagNameException
     */
    public static List<String> getOptionsVisibleTextsFromSelect(WebElement select) {
        Select selectElement = new Select(select);
        List<WebElement> options = selectElement.getOptions();
        List<String> optionsString = new ArrayList<>();
        for (WebElement option : options) {
            optionsString.add(option.getText());
        }

        return optionsString;
    }

    /**
     * A simple click on the given WebElement
     * @param element
     */
    public static void click(WebElement element) {
        element.click();
    }


}
