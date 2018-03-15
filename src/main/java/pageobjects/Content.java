package pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.*;

public class Content {

    private List<WebElement> elements;

    private Page page;

    @FindBy(tagName = "h3")
    private WebElement headline;

    @FindBy(xpath = "//input[@type=\"submit\"]")
    private List<WebElement> submitButtons;

    /**
     * Contains all text fields (one-line input) in the content.
     * <p>
     * Key: value of label element corresponding to the text input element.
     * Value: text input element.
     * <p>
     * NOTE: if no label found for element (for attribute of label not match id attribute of corresponding element),
     * the key is a iterating number starting with 0 as a String value.
     */
    private Map<String, WebElement> labelsAndTextfields;
    private Map<String, WebElement> labelsAndSelects;

    @FindBy(xpath = "//input[@type=\"text\"]")
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
    }

    protected Content load() {

        PageFactory.initElements(page.driver, this);

        return this;
    }

    public Map<String, WebElement> joinLabelAndHisWebElements(List<WebElement> elements) {

        Map<String, WebElement> labelsAndElements = new HashMap<>();
        String foundLabel;

        int keyValue = 0;
        for (WebElement element : elements) {
            foundLabel = null;
            for (WebElement label : labels) {

                if (element.getAttribute("id").equals(label.getAttribute("for"))) {
                    foundLabel = label.getText();
                    break;
                } else if (element.getAttribute("id").toUpperCase()
                        .contains(label.getAttribute("for").toUpperCase())) {
                    foundLabel = label.getText();
                    System.out.println("WARNING: label found, but for attribute does not match corresponding id attribute");
                    break;
                }
            }
            if (foundLabel != null) {
                labelsAndElements.put(foundLabel, element);

            } else {
                labelsAndElements.put(String.valueOf(keyValue), element);
                System.out.println("WARNING: No label found for element: " + element.getAttribute("name")
                        + ". Please check getLabels()");
                keyValue++;
            }
        }

        return labelsAndElements;

    }


    public String getHeadlineText() {
        return headline.getText();
    }

    public List<WebElement> getSubmitButtons() {
        return submitButtons;
    }

    public Collection<WebElement> getLabelsAndTextfields() {
        return labelsAndTextfields.values();
    }


    private void printPretty(String element) {
        StringBuilder pretty = new StringBuilder();

        if (labelsAndTextfields == null)
            labelsAndTextfields = joinLabelAndHisWebElements(textfields);
        if (labelsAndSelects == null)
            labelsAndSelects = joinLabelAndHisWebElements(selects);

        Iterator<Map.Entry<String, WebElement>> iterator = (element.equals("textfield"))
                ? labelsAndTextfields.entrySet().iterator()
                : (element.equals("select"))
                ? labelsAndSelects.entrySet().iterator()
                : null;

        while (iterator.hasNext()) {
            Map.Entry<String, WebElement> entry = iterator.next();
            pretty.append("label " + entry.getKey());
            pretty.append(" | " + element + " name: " + entry.getValue().getAttribute("name"));
            pretty.append(" | " + element + " value: " + entry.getValue().getAttribute("value"));
            pretty.append(System.getProperty("line.separator"));
        }
        System.out.println(pretty);

    }

    public void printTextInputFieldsPretty() {
        printPretty("textfield");
    }

    public void printSelectsPretty() {
        printPretty("select");
    }

    public Collection<WebElement> getLabelsAndSelects() {
        return labelsAndSelects.values();
    }

    public List<WebElement> getLabels() {
        return labels;
    }

    public List<WebElement> getLinkButtons() {
        return linkButtons;
    }
}
