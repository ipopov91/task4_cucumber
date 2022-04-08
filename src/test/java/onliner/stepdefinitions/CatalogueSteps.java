package onliner.stepdefinitions;

import framework.Browser;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import onliner.pages.ProductsFilters;
import onliner.pages.CataloguePage;
import onliner.pages.HomePage;
import onliner.pages.ProductsPage;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;
import java.util.Map;

public class CatalogueSteps {

    private static final Browser browser = Browser.getInstance();

    private HomePage homePage;
    private CataloguePage categoryPage;
    private ProductsPage productsPage;

    private static String ERROR_MSG_TITLE_NOT_MATCH = "Not each product title contains selected manufacturer: %s";
    private static String ERROR_MSG_PRICE_NOT_MATCH_RANGE = "Not each product price is within selected range: до %s";
    private static String ERROR_MSG_DESCRIPTION_NOT_MATCH = "Not each product description contains filtered value: %s";
    private static String ERROR_MSG_DESCRIPTION_NOT_MATCH_RANGE = "Not each product description contains value within selected range: %s %s";

    private static final HashMap<String,String> appliedFilters = new HashMap<String, String>();

    @Given("Onliner Home Page is opened")
    public void onlinerHomePageOpened() {
        browser.navigate(Browser.props.getProperty("URL"));
        homePage = new HomePage();
        homePage.assertIsOpen();
    }

    @And("I click {string} on Onliner Home Page")
    public void selectNavigationItem(String selectedNavigationItem) {
        categoryPage = homePage.selectNavigationItem(selectedNavigationItem);
    }

    @And("select {string} on Onliner Category Page")
    public void selectCatalogNavigationItem(String selectedCatalogNavigationItem) {
        categoryPage.selectCatalogNavigationItem(selectedCatalogNavigationItem);
    }

    @And("move to {string} and {string} on Onliner Category Page")
    public void moveToCatalogUseDropdownItem(String selectedCatalogAsideListItem,
                                             String selectedCatalogAsideListDropdownItem ) {
        productsPage = categoryPage.moveToAndSelectCatalogAsideListDropdownItem(selectedCatalogAsideListItem,
                selectedCatalogAsideListDropdownItem);
    }

    @Given("I am on Onliner Products Page")
    public void onlinerProductsPageOpened() {
        productsPage.assertIsOpen();
    }

    @When("I check the following filters")
    public void checkFilters(Map<String,String> filtersToApply) {
        filtersToApply.forEach((field,value) -> {
            productsPage.applyFilter(ProductsFilters.valueOf(field),value);
            appliedFilters.put(field,value);
        });
    }

    @Then("correct search results are displayed")
    public void correctSearchResultsDisplayed() {

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue(productsPage.isEachProductTitleContainsFilterValue(appliedFilters.get("MANUFACTURER")),
                String.format(ERROR_MSG_TITLE_NOT_MATCH,appliedFilters.get("MANUFACTURER")));
        softAssert.assertTrue(productsPage.isEachProductPriceMatchesFilterValue(appliedFilters.get("PRICETO")),
                String.format(ERROR_MSG_PRICE_NOT_MATCH_RANGE,appliedFilters.get("PRICETO")));
        softAssert.assertTrue(productsPage.isEachProductDescriptionContainsFilterValue(appliedFilters.get("RESOLUTION")),
                String.format(ERROR_MSG_DESCRIPTION_NOT_MATCH,appliedFilters.get("RESOLUTION")));
        softAssert.assertTrue(productsPage.isEachProductDescriptionContainsFilterValueInRange(appliedFilters.get("DIAGONALFROM"),appliedFilters.get("DIAGONALFROM")),
                String.format(ERROR_MSG_DESCRIPTION_NOT_MATCH_RANGE,appliedFilters.get("DIAGONALTO"),appliedFilters.get("DIAGONALTO")));
        softAssert.assertAll();
    }
}