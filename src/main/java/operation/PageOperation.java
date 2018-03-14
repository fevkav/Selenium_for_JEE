package operation;

import org.openqa.selenium.WebElement;
import pageobjects.LoginPage;
import pageobjects.Page;
import pageobjects.RolePage;

public class PageOperation {

    /**
     * Uses a HtmlUnitDriver with enabled javascript
     *
     * @param role
     * @return
     */
    public static Page startLoginSelectRole(String role) {
        LoginPage loginPage = new LoginPage();
        RolePage rolePage = (RolePage) loginPage.navigateToLoginPageAndLogin();

        return rolePage.selectRole(role);
    }

    public static void clickMainNaviItemThenSubmenuItem(Page page, String mainNaviLinkText, String submenuLinkText) {

        WebElement mainNavi = ((RolePage) page).getMainNaviItemElementByLinkText(mainNaviLinkText);

        UIOperation.click(mainNavi);

        WebElement submenu = ((RolePage) page).getSubMenuItemElementByLinkText(submenuLinkText);

        UIOperation.click(submenu);


    }
}
