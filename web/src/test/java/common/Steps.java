package common;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import ru.sbtqa.tag.pagefactory.context.PageContext;
import ru.sbtqa.tag.pagefactory.environment.Environment;
import ru.sbtqa.tag.pagefactory.exceptions.PageException;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import static common.Waits.getDriver;

public class Steps {

    private final Duration TIMEOUT = Duration.ofSeconds(10);

    @When("^WEB. user clicks on the element \"([^\"]*)\"$")
    public void clickButton(String elementName) throws PageException {
        try {
            Waits.elementToBeClickable(elementName, TIMEOUT).click();
        } catch (StaleElementReferenceException e) {
            e.printStackTrace();
        }
    }

    @Then("^WEB. user checks that the element \"([^\"]*)\" is displayed$")
    public void elementIsDisplayed(String elementName) throws PageException {
        Waits.visibilityOf(elementName, TIMEOUT).isDisplayed();
    }

    @Then("^WEB. users checks that the text \"([^\"]*)\" is visible$")
    public void checksElementWithTextIsPresent(String text) {
        Waits.checksElementWithTextIsPresent(text, TIMEOUT);
    }

    @When("^WEB. user enters text \"(.*)\" in the field \"([^\"]*)\"$")
    public void userWaitsSecondsUntilElementIsPresentAndPutsText(String text, String elementName) throws PageException {
        WebElement element = Waits.visibilityOf(elementName, TIMEOUT);
        element.clear();
        element.sendKeys(text);
    }

    @When("^WEB. user checks that table \"([^\"]*)\" sorted correctly$")
    public void userWaitsSecondsUntilElementIsPresentAndPutsText(String elementName) throws PageException {
        List<WebElement> elementList = getDriver()
                .findElements(By.xpath(
                        Environment.getFindUtils().getElementByTitle(PageContext.getCurrentPage(), elementName)));
        Assert.assertTrue(checkSorted(elementList));
    }

    private boolean checkSorted(List<WebElement> list) {
        List<Integer> unsortedList = list.stream().map(a -> a.getAttribute("href"))
                .map(a -> a.substring(a.indexOf("=") + 1))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        List<Integer> sortedList = unsortedList.stream()
                .sorted((a, b) -> b - a).collect(Collectors.toList());
        return unsortedList.equals(sortedList);
    }
}