package suites;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = {"stepDefinitions"},
        features = {
            "src/test/resources/features/exerciseOne.feature"
        },
        tags = "not @ignore",
        monochrome = true
)

public class TestRunner {
}
