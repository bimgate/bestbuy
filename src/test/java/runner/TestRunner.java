package runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Created by bratislav.miletic on 08/10/2017.
 */

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/features/",
        glue = "com.bestbuy.qa.steps",
        strict = true,
        plugin = {"html:target/cucumber-report"}
        )

    public class TestRunner {


}
