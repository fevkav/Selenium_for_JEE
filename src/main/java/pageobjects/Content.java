package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Content {

    private List<WebElement> elements;

    private Page page;

    private String headline;

    private List<WebElement> submitButtons;

    /**
     * Contains all text input fields in the content.
     * <p>
     * Key: value of label element corresponding to the text input element.
     * Value: text input element.
     */
    private Map<String, WebElement> textInputFields;

    public Content(Page page) {
        this.page = page;
    }

    protected Content load() {

        headline = page.driver.findElement(By.tagName("h3")).getText();
        submitButtons = page.driver.findElements(By.xpath("//input[@type=\"submit\"]"));

        findTextInputFieldsAndLabels();

        return this;
    }


    private void findTextInputFieldsAndLabels() {
        List<WebElement> textfields = page.driver.findElements(By.xpath("//input[@type=\"text\"]"));
        List<WebElement> labels = page.driver.findElements(By.tagName("label"));

        textInputFields = new HashMap<>();
        String foundLabel;
        for (WebElement textfield : textfields) {
            foundLabel = null;
            for (WebElement label : labels) {

                if (textfield.getAttribute("name").equals(label.getAttribute("for"))) {
                    foundLabel = label.getText();
                    break;
                }
            }
            if (foundLabel != null) {
                textInputFields.put(foundLabel, textfield);
            } else {
                textInputFields.put("", textfield);
                System.out.println("WARNING: No label found for the text input field with name tag = "
                        + textfield.getAttribute("name"));
            }
        }
    }

    public String getHeadline() {
        return headline;
    }

    public List<WebElement> getSubmitButtons() {
        return submitButtons;
    }

    public Map<String, WebElement> getTextInputFields() {
        return textInputFields;
    }

    public void printTextInputFieldsPretty() {
        StringBuilder pretty = new StringBuilder();
        Iterator<Map.Entry<String, WebElement>> iterator = textInputFields.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<String, WebElement> entry = iterator.next();
            pretty.append("label " + entry.getKey());
            pretty.append(" | textinputfield name: " + entry.getValue().getAttribute("name"));
            pretty.append(" | textinputfield value: " + entry.getValue().getAttribute("value"));
            pretty.append(System.getProperty("line.separator"));

        }
        System.out.println(pretty);

    }
}
