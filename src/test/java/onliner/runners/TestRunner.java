package onliner.runners;

import framework.TestListener;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.Listeners;

@Listeners(TestListener.class)
@CucumberOptions(
        features = {"src/test/java/onliner/features"},
        glue = {"onliner.stepdefinitions","onliner.hooks"},
        plugin = {"pretty","html:target/cucumber.html",
                "json:target/cucumber.json","json:target/cucumber-reports/CucumberTestReport.json"},
        publish = true)

public class TestRunner extends AbstractTestNGCucumberTests {
}