package operation;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import pageobjects.AddressContent;
import pageobjects.Content;

import java.util.List;


public class ContentOperation {

    private static final String TESTSTRING = "Testinput2";

    private static final String TESTZIPCODE = "00000";

    private static final String TESTGEO = "50,00000";

    private static final String TESTEMAIL = "test@test.de";

    private static final String TESTPHONE = "0123456789";
    private static final String TESTDATE = "01.01.2018";



    /**
     * Fills all textfields with a test string, selects not empty option of all selects, adds a address and tries to
     * save entry.
     *
     * @param content the current content of the page. Need to be a craete-form
     */
    public static void fillCreatePage(Content content) {

        if (!content.getHeadlineText().contains("anlegen"))
            throw new RuntimeException("Headline does not contain \"anlegen\". Is current page a create page?");


        // normale Textfelder mit String füllen
        fillSimpleTextfields(content);

        // Textfelder mit Datum füllen
        fillDateTextfields(content);


        // nicht leere option aus select wählen
        selectNotEmptyOptionOfSelects(content);

        // Adresse hinzufügen, falls im formular erforderlich
        addAddress(new AddressContent(content.getPage()));


        String headlineBeforeSubmit = content.getHeadlineText();

        // falls vorhanden auf weiter klicken
        clickContinueAndCheckHeadlines(content, headlineBeforeSubmit);

        // klick auf speichern
        clickSaveButton(content);
    }

    public static void clickSaveButton(Content content) {
        if (content.getSaveButton() != null) {
            UIOperation.click(content.getSaveButton());
        } else
            throw new RuntimeException("Couldn't save entry. No save button found.");
    }

    public static void clickContinueAndCheckHeadlines(Content content, String headlineBeforeSubmit) {
        if (content.getContinueButton() != null) {
            UIOperation.click(content.getContinueButton());
        } else
            throw new RuntimeException("Couldn't submit create form. No continue button found.");

        // test durch Inhaltsüberschrift, ob gleiche seite noch present
        if (headlineBeforeSubmit.equals(content.getHeadlineText())) {
            throw new RuntimeException("Headlines before and after are the same. "
                    + "Form filled incorrectly or entry to create already exists. Form validation messages: "
                    + getValidationMessages(content));
        }
    }

    public static void selectNotEmptyOptionOfSelects(Content content) {
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
    }

    public static void fillDateTextfields(Content content) {
        if (content.hasDateFields()) {
            for (WebElement tf : content.getDateTextfields()) {
                UIOperation.typeInTextfield(tf, TESTDATE);
            }
        }
    }

    public static void fillSimpleTextfields(Content content) {

        for (WebElement tf : content.getSimpleTextfields()) {
            UIOperation.typeInTextfield(tf, TESTSTRING);
        }
    }


    /**
     * Returns all form validation messages appear after a incorrect form submit.
     *
     * @param content the current content of the page
     * @return one line String of all validation messages.
     */
    public static String getValidationMessages(Content content) {
        StringBuilder messages = new StringBuilder();
        for (WebElement messageDiv : content.getFormValidationMessages()) {
            if (!messageDiv.getText().equals("") && !messageDiv.getText().equals(" ")) {
                messages.append(messageDiv.getText()).append(" ");
            }
        }
        return String.valueOf(messages);
    }

    /**
     * Adds a new address with test input.
     *
     * @param addressContent the content need to be the address edit page.
     */
    public static void addAddress(AddressContent addressContent) {

        try {
            WebElement addressButton = addressContent.getAddAddressButton();
            UIOperation.click(addressButton);
        } catch (NoSuchElementException e) {
            System.out.println("WARNING: Create page doesn't contain address add button");
            return;
        }

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
