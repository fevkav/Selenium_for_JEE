package operation;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.UnexpectedTagNameException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UIOperationTest {

    private static WebElement textInputUserMock;
    private static WebElement textInputPasswordMock;
    private static WebElement selectMock;

    private static final String userValue = "MaxMustermann@max.net";
    private static final String passwordValue = "Qjuz385#+>_";

    @BeforeClass
    public static void setUp() throws Exception {

        textInputUserMock = Mockito.mock(WebElement.class);
        textInputPasswordMock = Mockito.mock(WebElement.class);

        Mockito.when(textInputUserMock.getAttribute("value")).thenReturn(userValue);
        Mockito.when(textInputPasswordMock.getAttribute("value")).thenReturn(passwordValue);

    }

    @Test
    public void typeInTextfieldTest() throws Exception {


        UIOperation.typeInTextfield(textInputUserMock, userValue);
        UIOperation.typeInTextfield(textInputPasswordMock, passwordValue);

        assertThat("Could not type in textfield username",
                textInputUserMock.getAttribute("value"), is(userValue));

        assertThat("Could not type in textfield password",
                textInputPasswordMock.getAttribute("value"), is(passwordValue));
    }

    @Test(expected = UnexpectedTagNameException.class)
    public void selectFromNonSelectElementShouldThrowException() {
        UIOperation.selectOptionFromSelectByValue(textInputPasswordMock, "option");
    }

    private WebElement mockTextFieldElement() {
        // getTagname muss input sein

        // getattribute("type") muss text sein

        // getattribute("value")

        return null;
    }
}