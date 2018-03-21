package pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class Content {

    private Page page;

    @FindBy(tagName = "h3")
    private WebElement headline;

    @FindBy(xpath = "//input[@type=\"submit\"]")
    protected List<WebElement> submitButtons;

    @FindBy(xpath = "//input[@type=\"text\" and not(contains(@style, \"display: none\"))]")
    private List<WebElement> textfields;

    @FindBy(tagName = "label")
    private List<WebElement> labels;

    @FindBy(tagName = "select")
    private List<WebElement> selects;

    @FindBy(css = "div.mchsSubPageButton > input")
    private List<WebElement> subPageButtons;


    //    @FindBy(xpath = "//input[contains(@id, \"AddressSetSubmit\")]") bei gtu-versich. id=...AddressSubmit
    @FindBy(xpath = "//input[contains(@id, \"Address\") and contains(@id, \"Submit\")]")
    private WebElement addAddressButton;

    @FindBy(xpath = "//input[contains(@id, \"SaveSubmit\")]")
    private WebElement saveButton;

    @FindBy(className = "mchsValidationMessage")
    private List<WebElement> formValidationMessages;

    /**
     * Links with a icon (view or edit link)
     */
    @FindBy(className = "mchsIconLinkSmall")
    private List<WebElement> linkButtons;

    private boolean hasDateFields = false;

    public Content(Page page) {
        this.page = page;
        load();
    }

    public List<WebElement> getFormValidationMessages() {
        return formValidationMessages;
    }

    protected Content load() {

        PageFactory.initElements(page.driver, this);

        return this;
    }

    /**
     * Checks, if the found and displayed textfield's id corresponding to the for attribute of a label.
     */
    public void checkLabelsOfTextfields() {

        boolean foundLabel;

        for (WebElement textfield : textfields) {
            foundLabel = false;
            for (WebElement label : labels) {

                try {

                    if (textfield.getAttribute("id").equals(label.getAttribute("for"))) {
                        foundLabel = true;
                        break;
                    } else if (textfield.getAttribute("id").toUpperCase()
                            .contains(label.getAttribute("for").toUpperCase())) {
                        foundLabel = true;
                        System.out.println("WARNING: for attribute of label " + label.getText()
                                + " does not match corresponding id attribute. for=" + label.getAttribute("for")
                                + " -> id=" + textfield.getAttribute("id"));
                        break;
                    }
                } catch (NullPointerException e) {
                    if (textfield.isDisplayed())
                        System.out.println("WARNING: No id found for element with name " + textfield.getAttribute("name"));
                    break;
                }
            }
            if (!foundLabel) {
                if (textfield.isDisplayed())
                    System.out.println("WARNING: No label found for element with name: " + textfield.getAttribute("name"));
            }
        }
    }

    /**
     *
     * @return the headline displayed as usually a h3 tag in the current viewed page.
     */
    public String getHeadlineText() {
        return headline.getText();
    }


    public WebElement getContinueButton() {

        for (WebElement webElement : submitButtons) {
            if (webElement.getAttribute("value").contains("Weiter")) {
                return webElement;
            }
        }

        return null;
    }

    public List<WebElement> getSimpleTextfields() {
        List<WebElement> simpleTextfields = new ArrayList<>();

        for (WebElement tf : textfields) {
            if (!(tf.getAttribute("class").contains("Date")
                    || tf.getAttribute("name").contains("runTime") || tf.getAttribute("name").contains("Date"))) { // gtu-versich. abweichend
                simpleTextfields.add(tf);
            }
        }
        return simpleTextfields;
    }

    public List<WebElement> getDateTextfields() {

        List<WebElement> dateTextfields = new ArrayList<>();
        for (WebElement tf : textfields) {
            if (tf.getAttribute("class").contains("Date")
                    || tf.getAttribute("name").contains("runTime") || tf.getAttribute("name").contains("Date")) { // gtu-versich. abweichend
                dateTextfields.add(tf);
            }
        }
        hasDateFields = !dateTextfields.isEmpty();
        return dateTextfields;
    }

    public WebElement getSaveButton() {
        return saveButton;
    }

    public List<WebElement> getLabels() {
        return labels;
    }


    public List<WebElement> getLinkButtons() {
        return linkButtons;
    }

    public List<WebElement> getSelects() {
        return selects;
    }

    public WebElement getAddAddressButton() {
        return addAddressButton;
    }

    public boolean hasDateFields() {
        return hasDateFields;
    }

    public List<WebElement> getTextfields() {
        return textfields;
    }

    public Page getPage() {
        return page;
    }
}
