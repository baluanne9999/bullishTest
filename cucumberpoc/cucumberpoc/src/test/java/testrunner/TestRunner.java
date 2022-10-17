
package testrunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/",
        tags = "@Regression",
        glue = {"stepdefinitions"},
        plugin = {"pretty", "html:cucumber-reports/report.html"},
        monochrome = true
)


public class TestRunner {

}