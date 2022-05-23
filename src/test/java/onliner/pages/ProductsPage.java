package onliner.pages;

import framework.elements.Button;
import framework.elements.Checkbox;
import framework.elements.InputField;
import framework.elements.Label;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductsPage extends MainPage {

    private static final String pageLocator =  "//h1[@class='schema-header__title'][contains(text(),'%s')]";

    private final String filterButtonLocator =
            "//div[@class='schema-filter-button__inner-container']";
    private final String checkboxFilterLocator =
            "//div[@class='schema-filter__label'][contains(.,'%s')]/following-sibling::div//span[contains(text(),'%s')]";
    private final String inputFilterLocator =
            "//div[@class='schema-filter__label']/following-sibling::div//input[@placeholder='%s']";
    private final String productPriceLocator =
            "//div[@class='schema-product__price']/a/span";
    private final String productTitleLocator =
            "//div[@class='schema-product__title']";
    private final String productDescriptionLocator =
            "//div[@class='schema-product__description']";

    private final Button btnFilter = new Button(By.xpath(filterButtonLocator));
    private final Label lblProductDescriptions = new Label(By.xpath(productDescriptionLocator));

    public ProductsPage(String pageLocatorValue) {
        super(By.xpath(String.format(pageLocator, pageLocatorValue)));
    }

    public ProductsPage setCheckboxFilter(String filterType, String filterValue) {
        Checkbox cbxFilter = new Checkbox(By.xpath(String.format(checkboxFilterLocator, filterType, filterValue)));
        cbxFilter.scrollIntoView();
        cbxFilter.click();
        btnFilter.waitForElementIsPresent();
        return this;
    }

    public ProductsPage setInputFilter (String filterType, String filterInputValue) {
        InputField fldFilter = new InputField(By.xpath(String.format(inputFilterLocator, filterType)));
        fldFilter.scrollIntoView();
        fldFilter.sendKeys(filterInputValue);
        btnFilter.waitForElementIsPresent();
        return this;
    }

    public ProductsPage applyFilter(ProductsFilters filterType, String filterValue) {
        switch(filterType) {
            case MANUFACTURER:
            case RESOLUTION:
            case DIAGONALFROM:
            case DIAGONALTO:
                setCheckboxFilter(filterType.toString(),filterValue);
                break;
            case PRICETO:
                setInputFilter(filterType.toString(),filterValue);
                break;
            default:
                break;
        }
        return this;
    }

    public boolean isEachProductTitleContainsFilterValue(String filterValue) {
        Label lblProductTitles = new Label(By.xpath(productTitleLocator));
        List<WebElement> productsTitles = lblProductTitles.getElements();
        for (WebElement element : productsTitles) {
            if(element.getText().contains(filterValue)){
                return true;
            }
        }
        return false;
    }

    public boolean isEachProductPriceMatchesFilterValue(String filterValue) {
        Label lblProductPrices = new Label(By.xpath(productPriceLocator));
        List<WebElement> productsPrices = lblProductPrices.getElements();
        for (WebElement element : productsPrices) {
            double price = Double.parseDouble(element.getText().replaceAll(" р.","").replace(',','.'));
            if(!(price <= Double.parseDouble(filterValue))){
                return false;
            }
        }
        return true;
    }

    public boolean isEachProductDescriptionContainsFilterValue(String filterValue){
        List<WebElement> productsDescriptions = lblProductDescriptions.getElements();
        for (WebElement element : productsDescriptions) {
            if(element.getText().contains(filterValue)){
                return true;
            }
        }
        return false;
    }

    public boolean isEachProductDescriptionContainsFilterValueInRange(String filterValueStartRange, String filterValueEndRange){
        List<WebElement> productsDescriptions = lblProductDescriptions.getElements();
        for (WebElement element : productsDescriptions) {
            double diagonal = Double.parseDouble(element.getText().substring(0, 2));
            if(diagonal >= Double.parseDouble(filterValueStartRange) && diagonal <= Double.parseDouble(filterValueEndRange)){
                return false;
            }
        }
        return true;
    }
}