package onliner.hooks;

import framework.Browser;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hook {
    private static Browser browser = Browser.getInstance();

    @Before
    public void setup() {
        browser.windowMaximize();
    }

    /*@After
    public void teardown() {
        browser.exit();
    }*/
}