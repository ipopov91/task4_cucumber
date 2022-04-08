package onliner.pages;

import framework.BasePage;
import framework.elements.Button;
import org.openqa.selenium.By;

public class MainPage extends BasePage {
    private static final String pageLocator = "onliner_logo";
    private static final String navigationItemLocator = "//ul[@class='b-main-navigation']//span[text() = '%s']";

    public MainPage() {
        super(By.className(pageLocator));
    }

    public MainPage(final By locator) {
        super(locator);
    }

    public CataloguePage selectNavigationItem(String selectedNavigationItem) {
        Button btnNavigationItem = new Button(By.xpath(String.format(navigationItemLocator, selectedNavigationItem)));
        btnNavigationItem.click();
        return new CataloguePage(selectedNavigationItem);
    }
}