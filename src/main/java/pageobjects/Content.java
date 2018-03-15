package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.*;

public class Content {

    private List<WebElement> elements;

    private Page page;

    private String headline;

    private List<WebElement> submitButtons;

    /**
     * Contains all text fields (one-line input) in the content.
     * <p>
     * Key: value of label element corresponding to the text input element.
     * Value: text input element.
     * <p>
     * NOTE: if no label found for element, the key is a iterating number starting with 0 as a String value
     */
    private Map<String, WebElement> textInputFields;

    private Map<String, WebElement> selects;
    private List<WebElement> labels;

    public Content(Page page) {
        this.page = page;
    }

    protected Content load() {

        headline = page.driver.findElement(By.tagName("h3")).getText();
        submitButtons = page.driver.findElements(By.xpath("//input[@type=\"submit\"]"));

        labels = page.driver.findElements(By.tagName("label"));
        textInputFields = joinLabelAndHisWebElements(page.driver.findElements(By.xpath("//input[@type=\"text\"]")));

        selects = joinLabelAndHisWebElements(page.driver.findElements(By.tagName("select")));

        return this;
    }

    private Map<String, WebElement> joinLabelAndHisWebElements(List<WebElement> elements) {

        Map<String, WebElement> labelsAndElements = new HashMap<>();
        String foundLabel;

        int keyValue = 0;
        for (WebElement element : elements) {
            foundLabel = null;
            for (WebElement label : labels) {

                if (element.getAttribute("name").equals(label.getAttribute("for"))) {
                    foundLabel = label.getText();
                    break;
                }
            }
            if (foundLabel != null) {
                labelsAndElements.put(foundLabel, element);

            } else {
                labelsAndElements.put(String.valueOf(keyValue), element);
                System.out.println("WARNING: No label found for element = " + element.getAttribute("name"));
                keyValue++;
            }
        }

        return labelsAndElements;

    }

    public String getHeadline() {
        return headline;
    }

    public List<WebElement> getSubmitButtons() {
        return submitButtons;
    }

    public Collection<WebElement> getTextInputFields() {
        return textInputFields.values();
    }


    private void printPretty(String element) {
        StringBuilder pretty = new StringBuilder();

        Iterator<Map.Entry<String, WebElement>> iterator = (element.equals("textfield"))
                ? textInputFields.entrySet().iterator()
                : (element.equals("select"))
                ? selects.entrySet().iterator()
                : null;
        Iterator<Map.Entry<String, WebElement>> iteratorSelects = selects.entrySet().iterator();

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

    public Collection<WebElement> getSelects() {
        return selects.values();
    }
}
