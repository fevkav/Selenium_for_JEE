import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import pageobjects.LoginPage;
import pageobjects.RolePage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

public class RolePageTest {


    private LoginPage loginpage;
    private RolePage rolePage;

    @Before
    public void setUp() {
        loginpage = new LoginPage();
        rolePage = (RolePage) loginpage.navigateToLoginPageAndLogin();

    }

    @Test
    public void checkIfRolePageContainsAnyRoleLink() {

        List<WebElement> rolesActual = rolePage.getRoleLinkElements();

        assertThat("Role page does not include any role link",
                rolesActual.isEmpty(), is(false));

    }

    @Test
    public void assertAllIdsOfRoleLinks() {

        List<String> idsActual = new ArrayList();
        rolePage.getRoleLinkElements().forEach(roleElement -> idsActual.add(roleElement.getAttribute("id")));   // TODO methode schreiben

        String roleIdPrefix = "SelectRoleRole_";

        List<String> idsExpected = Arrays.asList(
                roleIdPrefix + "SystemAdmin_", roleIdPrefix + "Mandator_METRO AG",
                roleIdPrefix + "Operator_METRO AG", roleIdPrefix + "Controller_METRO AG",
                roleIdPrefix + "Approver_METRO AG"
        );

        assertThat(idsActual, containsInAnyOrder(idsExpected.toArray()));
    }

    @Test
    public void selectRoleMandatorTest() {
        rolePage.selectRole("Mandator");
    }

    @Test
    public void selectRoleSystemAdminTest() {
        rolePage.selectRole("SystemAdmin");
    }

    @Test
    public void selectRoleOperatorTest() {
        rolePage.selectRole("Operator");
    }

    @Test
    public void selectRoleControllerTest() {
        rolePage.selectRole("Controller");
    }

    @Test
    public void selectRoleApproverTest() {
        rolePage.selectRole("Approver");
    }

    @Test
    public void changeRoleTest() {

        rolePage.selectRole("Mandator");
        rolePage.changeRole("Operator");
    }

    @After
    public void quitDriver() {
        loginpage.quitDriver();
        rolePage.quitDriver();

    }


}
