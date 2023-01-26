package runner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:featureFiles",
        glue = "",
        tags = "",
        plugin = {"pretty", "html:target/cucumber.html"},
        publish = true
)
public class RunTest {
}
