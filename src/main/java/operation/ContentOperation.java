package operation;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import pageobjects.AddressContent;
import pageobjects.Content;

import java.util.List;


public class ContentOperation {

    private static final String TESTSTRING = "Testinput";

    private static final String TESTZIPCODE = "00000";

    private static final String TESTGEO = "50,00000";

    private static final String TESTEMAIL = "test@test.de";

    private static final String TESTPHONE = "0123456789";


    /**
     * Fills all textfields with a test string, selects not empty option of all selects, adds a address and tries to
     * save entry.
     *
     * @param content the current content of the page. Need to be a craete-form
     */
    public static void fillCreatePage(Content content) {


        // Textfelder mit TESTSTRING füllen
        List<WebElement> textfields = content.getTextfields();
        for (WebElement tf : textfields) {
            UIOperation.typeInTextfield(tf, TESTSTRING);
        }

        // nicht leere option aus select wählen
        List<WebElement> selects = content.getSelects();
        for (WebElement select : selects) {
            List<WebElement> options = new Select(select).getOptions();
            for (WebElement option : options) {
                if (!(option.getText().equals("") || option.getText().equals(" "))) {
                    UIOperation.selectOptionFromSelectElement(select, option.getAttribute("value"));
                    break;
                }
            }
        }

        // Adresse hinzufügen, falls vorhanden
        if (content.getAddAddressButton() != null) {
            UIOperation.click(content.getAddAddressButton());
            addAddress(new AddressContent(content.getPage()));
        }

        String headlineBeforeSubmit = content.getHeadlineText();

        // falls vorhanden auf weiter klicken
        if (content.getContinueButton() != null) {
            UIOperation.click(content.getContinueButton());
        } else
            throw new RuntimeException("Couldn't submit create form. No continue button found.");

        // test durch Inhaltsüberschrift, ob gleiche seite noch present
        if (headlineBeforeSubmit.equals(content.getHeadlineText()))
            throw new RuntimeException("Headlines before and after are the same. "
                    + "Form filled incorrectly or entry to create already exists.");

        // klick auf speichern
        if (content.getSaveButton() != null) {
            UIOperation.click(content.getSaveButton());
        } else
            throw new RuntimeException("Couldn't save entry. No save button found.");

    }

    /**
     * Adds a new address with test input.
     * @param addressContent the content need to be the address edit page.
     */
    public static void addAddress(AddressContent addressContent) {

        // klick auf hinzufügen
        if (addressContent.getAddButton() == null)
            throw new RuntimeException("No add button found in address edit page.");
        UIOperation.click(addressContent.getAddButton());

        // fülle alle textfelder
        for (WebElement textfield : addressContent.getTextfields()) {
            if (textfield.getAttribute("id").contains("ZipCode"))
                UIOperation.typeInTextfield(textfield, TESTZIPCODE);
            else if (textfield.getAttribute("id").contains("AddressGeo"))
                UIOperation.typeInTextfield(textfield, TESTGEO);
            else if (textfield.getAttribute("id").contains("AddressPhone")
                    || textfield.getAttribute("id").contains("AddressFax"))
                UIOperation.typeInTextfield(textfield, TESTPHONE);
            else if (textfield.getAttribute("id").contains("Email"))
                UIOperation.typeInTextfield(textfield, TESTEMAIL);
            else
                UIOperation.typeInTextfield(textfield, TESTSTRING);
        }

        // nicht leere option aus select wählen
        for (WebElement select : addressContent.getSelects()) {
            List<WebElement> options = new Select(select).getOptions();
            for (WebElement option : options) {
                if (!(option.getText().equals("") || option.getText().equals(" "))) {
                    UIOperation.selectOptionFromSelectElement(select, option.getAttribute("value"));
                    break;
                }
            }
        }

        String headlineBeforeApply = addressContent.getHeadlineText();

        // klicke auf Übernehmen
        if (addressContent.getApplyButton() == null)
            throw new RuntimeException("No apply button found in address edit page.");
        UIOperation.click(addressContent.getApplyButton());


        // Test durch headline, ob gleiche seite noch present
        if (headlineBeforeApply.equals(addressContent.getHeadlineText()))
            throw new RuntimeException("Headline after equals headline before click on apply. Form filled incorrectly?");

        // klicke auf weiter
        if (addressContent.getContinueButton() == null)
            throw new RuntimeException("continue button not found.");
        UIOperation.click(addressContent.getContinueButton());


    }

}
