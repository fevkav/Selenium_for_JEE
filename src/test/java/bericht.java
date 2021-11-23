import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class bericht {



    @Test
    public void navigateToRootUrlAndLoginTest() {

        WebDriver driver = new ChromeDriver();
        driver.get("http://10.11.12.13:8080/");

        WebElement usernameTextField = driver.findElement(By.id("username"));
        WebElement passwordTextField = driver.findElement(By.id("pw"));
        WebElement submitButton = driver.findElement(By.id("submitButton"));

        usernameTextField.sendKeys("fkavalci");
        passwordTextField.sendKeys("test123");
        submitButton.click();
    }

}
