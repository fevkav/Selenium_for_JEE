package operation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageobjects.LoginPage;
import pageobjects.Page;
import pageobjects.RolePage;

import java.util.ArrayList;
import java.util.List;

public class PageOperation {

    /**
     * Starts the WebDriver, navigates to login page, logs in and selects Role. Uses HtmlUnitDriver as default WebDriver.
     *
     * @param role String of the role (e.g. "Mandator")
     * @return start page of the given role
     */
    public static Page startLoginSelectRole(String role) {
        LoginPage loginPage = new LoginPage();
        RolePage rolePage = (RolePage) loginPage.navigateToLoginPageAndLogin();

        return rolePage.selectRole(role);
    }

    /**
     * Starts the WebDriver, navigates to login page, logs in and selects role.
     *
     * @param role   the role to select
     * @param driver Webdriver to use
     * @return the page, after selected given role
     */
    public static Page startLoginSelectRole(String role, WebDriver driver) {
        LoginPage loginPage = new LoginPage(driver);
        RolePage rolePage = (RolePage) loginPage.navigateToLoginPageAndLogin();

        return rolePage.selectRole(role);
    }

    /**
     * Clicks on the given main navi and then on the given submenu.
     *
     * @param page             the current page.
     * @param mainNaviLinkText the visible link text of the main navi in the current page to be clicked
     * @param submenuLinkText  the visible link text of the submenu of the given main navi.     *
     * @throws org.openqa.selenium.NoSuchElementException if given linktexts couldn't have been found.
     */
    public static void clickMainNaviThenSubmenu(Page page, String mainNaviLinkText, String submenuLinkText) {

        WebElement mainNavi = ((RolePage) page).getMainNaviItemElementByLinkText(mainNaviLinkText);

        UIOperation.click(mainNavi);

        WebElement submenu = ((RolePage) page).getSubMenuItemElementByLinkText(submenuLinkText);

        UIOperation.click(submenu);

        ((RolePage) page).loadContent();

    }

    /**
     * Checks if the given WebElements contain not displayed WebElements.
     *
     * @param elements list of WebElements to check
     * @return list of not displayed WebElements. null if elements does not contain not displayed WebElements.
     */
    public static List<WebElement> hasNotDisplayedElements(List<WebElement> elements) {
        List<WebElement> notDisplayedElements = new ArrayList<>();
        elements.forEach(linkButton -> {
            if (!linkButton.isDisplayed())
                notDisplayedElements.add(linkButton);
        });
        if (!notDisplayedElements.isEmpty()) {
            return notDisplayedElements;
        }
        return null;
    }


    /**
     * Clicks through all main navis and prints all find main navi link texts with a create submenu.
     *
     * @param page current page
     * @return the visible link texts of all main links
     */
    public static List<String> clickOnAllMainNavisAndReturnLinkTexts(RolePage page) {

        int mainNavisSize = page.getAllMainNaviItems().size();
        List<WebElement> mainNavis;
        List<String> links = new ArrayList<>();
        // StateElementReferenceException. Nach jedem Klick auf MainNavi müssen Elemente erneut geholt werden.
        for (int i = 0; i < mainNavisSize; i++) {
            mainNavis = page.getAllMainNaviItems();
            UIOperation.click(mainNavis.get(i));
            System.out.println("clicked on " + mainNavis.get(i).getText());
            links.add(mainNavis.get(i).getText());
        }
        return links;
    }

    /**
     * Finds main navis with a create submenu.
     * @param page current page
     * @return found submenu WebElements
     *
     * @see getCreateSubmenu() in Rolepage
     */
    public static List<WebElement> getMainNavisWithCreateSubmenu(RolePage page) {

        int numberOfMainNavis = page.getAllMainNaviItems().size();
        WebElement submenu;

        List<WebElement> mainNavisWithCreateSubmenu = new ArrayList<>();
        List<WebElement> mainNavis;

        StringBuilder foundSubmenus = new StringBuilder();
        foundSubmenus.append("found main navis with \"create\" submenu: ");

        for (int i = 0; i < numberOfMainNavis; i++) {
            mainNavis = page.getAllMainNaviItems();
            submenu = page.getCreateSubmenu(mainNavis.get(i));
            if (submenu != null) {
                mainNavisWithCreateSubmenu.add(submenu);
                foundSubmenus.append(mainNavis.get(i).getText()).append(", ");
            }
        }
        System.out.println(foundSubmenus);
        return mainNavisWithCreateSubmenu;
    }


}
