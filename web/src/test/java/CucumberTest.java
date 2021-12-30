import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"ru.sbtqa.tag.stepdefs.en", "ru.sbtqa.tag.stepdefs.ru", "common"}
)
public class CucumberTest {
}