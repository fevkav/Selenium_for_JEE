package pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Content {

    private List<WebElement> elements;

    private Page page;

    @FindBy(tagName = "h3")
    private WebElement headline;

    @FindBy(xpath = "//input[@type=\"submit\"]")
    private List<WebElement> submitButtons;

    @FindBy(xpath = "//input[@type=\"text\" and not(contains(@style, \"display: none\"))]")
    private List<WebElement> textfields;

    @FindBy(tagName = "label")
    private List<WebElement> labels;

    @FindBy(tagName = "select")
    private List<WebElement> selects;

    /**
     * Links with a icon (view or edit link)
     */
    @FindBy(className = "mchsIconLinkSmall")
    private List<WebElement> linkButtons;

    public Content(Page page) {
        this.page = page;
        load();
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


    public String getHeadlineText() {
        return headline.getText();
    }

    public List<WebElement> getSubmitButtons() {
        return submitButtons;
    }

    public WebElement getContinueButton() {

        for (WebElement webElement : submitButtons) {
            if (webElement.getAttribute("value").contains("Weiter")) {
                return webElement;
            }
        }

        return null;
    }

    public WebElement getSaveButton() {

        for (WebElement webElement : submitButtons) {
            if (webElement.getAttribute("value").contains("Speichern")) {
                return webElement;
            }
        }

        return null;
    }


    public List<WebElement> getLabels() {
        return labels;
    }

    public List<WebElement> getTextfields() {
        return textfields;
    }

    public List<WebElement> getLinkButtons() {
        return linkButtons;
    }

    public List<WebElement> getSelects() {
        return selects;
    }
}
