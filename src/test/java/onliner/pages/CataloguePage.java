package onliner.pages;

import framework.elements.Button;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

public class CataloguePage extends MainPage {

    private static final String pageLocator = "//h1[@class='catalog-navigation__title'][contains(text(),'%s')]";

    private final String catalogNavigationItemLocator =
            "//span[@class='catalog-navigation-classifier__item-title'][contains(.,'%s')]";
    private final String catalogAsideListItemLocator =
            "//div[@class='catalog-navigation-list__aside-item'][contains(.,'%s')]";
    private final String catalogAsideListDropdownItemLocator =
            "//*[@id=\"container\"]/div/div/div/div/div[1]/div[4]/div/div[2]/div[1]/div/div[2]/div[2]/div/a[1][contains(.,'%s')]";

    private Button btnCatalogNavigationItem;
    private Button btnCatalogAsideListItem;
    private Button btnCatalogAsideListDropdownItem;

    public CataloguePage(String pageLocatorValue) {
        super(By.xpath(String.format(pageLocator, pageLocatorValue)));
    }

    public CataloguePage selectCatalogNavigationItem(String selectedCatalogNavigationItem) {
        btnCatalogNavigationItem = new Button(By.xpath(String.format(catalogNavigationItemLocator, selectedCatalogNavigationItem)));
        btnCatalogNavigationItem.click();
        return this;
    }

    public ProductsPage moveToAndSelectCatalogAsideListDropdownItem(String selectedCatalogAsideListItem, String selectedCatalogAsideListDropdownItem) {
        btnCatalogAsideListItem = new Button(By.xpath(String.format(catalogAsideListItemLocator, selectedCatalogAsideListItem)));
        btnCatalogAsideListDropdownItem = new Button(By.xpath(String.format(catalogAsideListDropdownItemLocator, selectedCatalogAsideListDropdownItem)));
        btnCatalogAsideListItem.moveToElementAndClick();
        btnCatalogAsideListDropdownItem.moveToElementAndClick();
        return new ProductsPage(selectedCatalogAsideListDropdownItem);
    }
}