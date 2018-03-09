package operation;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 * This class provides basic operations to interact with the given page object, e.g. to type keys in a textinput field.
 */
public class UIOperation {

//    private static WebDriverWait wait;


    // TODO wirf exception wenn kein texteingabefeld
    public static void typeInTextfield(WebElement textinput, String keys) {

        // TODO prüfe ob texteingabefeld ist
        textinput.sendKeys(keys);
    }


    public static void selectOptionFromSelectByValue(WebElement select, String optionValue) {

        // prüft implizit, ob parameter select ein select-tag ist, da sonst UnexpectedTagNameException
        // geworfen wird.
        Select selectElement = new Select(select);
        selectElement.selectByValue(optionValue);
    }
}
