package onliner.pages;

import org.openqa.selenium.By;

public class HomePage extends MainPage {
    private static String pageLocator = "onliner_logo";

    public HomePage() {
        super(By.className(pageLocator));
    }
}