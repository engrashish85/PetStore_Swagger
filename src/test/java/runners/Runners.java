package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources",
        glue = {"stepDefinitions", "utility.Hooks"},
        plugin={"pretty","html:target/cucumber-reports.html", "" +
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        publish = true,
        tags = "@getPet"

)
public class Runners extends AbstractTestNGCucumberTests {

}
