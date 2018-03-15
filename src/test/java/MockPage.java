import org.openqa.selenium.WebDriver;
import pageobjects.Content;
import pageobjects.Page;

public class MockPage extends Page {

    public MockPage(WebDriver driver) {
        super(driver);
    }

    public MockPage() {
        super();
    }

    @Override
    public Content getCurrentContent() {
        return null;
    }
}
