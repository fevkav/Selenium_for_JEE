package operation;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import pageobjects.Content;

import java.util.List;

public class ContentOperation {

    private static final String TESTSTRING = "Testinput";


    public static void fillCreatePage(Content content) {

        List<WebElement> textfields = content.getTextfields();
        List<WebElement> selects = content.getSelects();
        WebElement continueButton = content.getContinueButton();


        // Textfelder mit TESTSTRING füllen
        for (WebElement tf : textfields) {
            UIOperation.typeInTextfield(tf, TESTSTRING);
        }

        // nicht leere option aus select wählen
        for (WebElement select : selects) {
            List<WebElement> options = new Select(select).getOptions();
            for (WebElement option : options) {
                if (!(option.getText().equals("") || option.getText().equals(" "))) {
                    UIOperation.selectOptionFromSelectElement(select, option.getAttribute("value"));
                    break;
                }
            }
        }

        String headlineBeforeSubmit = content.getHeadlineText();

        // falls vorhanden auf weiter klicken
        if (continueButton != null) {
            UIOperation.click(continueButton);
        } else
            throw new RuntimeException("Couldn't submit create form. No continue button found.");

        // test durch Inhaltsüberschrift, ob gleiche seite noch present
        if (headlineBeforeSubmit.equals(content.getHeadlineText()))
            throw new RuntimeException("Headline after equals headline before click on continue. "
                    + "Form filled incorrectly or entry to create already exists?");

        WebElement saveButton = content.getSaveButton();
        if (saveButton != null) {
            UIOperation.click(saveButton);
        } else
            throw new RuntimeException("Couldn't save create operation. No save button found.");

    }

}
