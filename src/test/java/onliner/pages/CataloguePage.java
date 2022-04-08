package onliner.pages;

import framework.elements.Button;
import org.openqa.selenium.By;

public class CataloguePage extends MainPage {

    private static final String pageLocator = "//h1[@class='catalog-navigation__title'][contains(text(),'%s')]";

    private final String catalogNavigationItemLocator =
            "//span[@class='catalog-navigation-classifier__item-title'][contains(.,'%s')]";
    private final String catalogAsideListItemLocator =
            "//div[@class='catalog-navigation-list__aside-item'][contains(.,'%s')]";
    private final String catalogAsideListDropdownItemLocator =
            "//a[@class='catalog-navigation-list__dropdown-item'][contains(.,'%s')]";

    public CataloguePage(String pageLocatorValue) {
        super(By.xpath(String.format(pageLocator, pageLocatorValue)));
    }

    public CataloguePage selectCatalogNavigationItem(String selectedCatalogNavigationItem) {
        Button btnCatalogNavigationItem = new Button(By.xpath(String.format(catalogNavigationItemLocator, selectedCatalogNavigationItem)));
        btnCatalogNavigationItem.click();
        return this;
    }

    public ProductsPage moveToAndSelectCatalogAsideListDropdownItem(String selectedCatalogAsideListItem, String selectedCatalogAsideListDropdownItem) {
        Button btnCatalogAsideListItem = new Button(By.xpath(String.format(catalogAsideListItemLocator, selectedCatalogAsideListItem)));
        Button btnCatalogAsideListDropdownItem = new Button(By.xpath(String.format(catalogAsideListDropdownItemLocator, selectedCatalogAsideListDropdownItem)));
        btnCatalogAsideListItem.moveToElementAndClick();
        btnCatalogAsideListDropdownItem.moveToElementAndClick();
        return new ProductsPage(selectedCatalogAsideListDropdownItem);
    }
}