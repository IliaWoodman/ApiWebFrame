package common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.sbtqa.tag.pagefactory.context.PageContext;
import ru.sbtqa.tag.pagefactory.environment.Environment;
import ru.sbtqa.tag.pagefactory.exceptions.PageException;

import java.time.Duration;

public class Waits {

    public static WebDriver getDriver() {
        return Environment.getDriverService().getDriver();
    }

    public static WebElement getElement(String elementName) throws PageException {
        return Environment
                .getFindUtils()
                .getElementByTitle(PageContext.getCurrentPage(), elementName);
    }

    public static WebElement elementToBeClickable(String elementName, Duration wait) throws PageException {
        return new WebDriverWait(getDriver(), wait)
                .until(ExpectedConditions
                        .elementToBeClickable(getElement(elementName)));
    }

    public static WebElement visibilityOf(String elementName, Duration wait) throws PageException {
        return new WebDriverWait(Environment.getDriverService().getDriver(), wait)
                .until(ExpectedConditions
                        .visibilityOf(getElement(elementName)));
    }

    public static WebElement checksElementWithTextIsPresent(String text, Duration wait) {
        return new WebDriverWait(getDriver(), wait)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), '" + text + "')]")));
    }
}