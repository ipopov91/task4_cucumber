package onliner.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/java/onliner/features"},
        glue = {"onliner.stepdefinitions","onliner.hooks"},
        plugin = {"pretty","html:target/cucumber.html",
                "json:target/cucumber.json"},
        publish = true)

public class TestRunner {
}