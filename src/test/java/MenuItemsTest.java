import operation.PageOperation;
import org.junit.After;
import org.junit.Test;
import pageobjects.MandatorPage;
import pageobjects.OperatorPage;
import pageobjects.Page;
import pageobjects.RolePage;

import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class MenuItemsTest {
    private Page page;


    @After
    public void closeDriver() {
        page.quitDriver();
    }


    @Test
    public void checkMenuOfMandator() {

        page = PageOperation.startLoginSelectRole("Mandator");

        Map<String, List<String>> navigationExpected = ((MandatorPage) page).getGermanNavigation();

        Map<String, List<String>> navigationActual = PageOperation.getNavigationHierarchy((RolePage) page);

        assertThat(navigationActual, is(navigationExpected));
        assertThat(navigationActual.size(), is(navigationExpected.size()));


    }

    @Test
    public void checkMenuOfOperator() {

        page = PageOperation.startLoginSelectRole("Operator");

        Map<String, List<String>> navigationExpected = ((OperatorPage) page).getGermanNavigation();

        Map<String, List<String>> navigationActual = PageOperation.getNavigationHierarchy((RolePage) page);

        assertThat(navigationActual, is(navigationExpected));
        assertThat(navigationActual.size(), is(navigationExpected.size()));

    }


}