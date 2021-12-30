package pages;

import common.MyDriverService;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import ru.sbtqa.tag.pagefactory.Page;
import ru.sbtqa.tag.pagefactory.actions.PageActions;
import ru.sbtqa.tag.pagefactory.annotations.PageEntry;
import ru.sbtqa.tag.pagefactory.checks.PageChecks;
import ru.sbtqa.tag.pagefactory.environment.Environment;
import ru.sbtqa.tag.pagefactory.web.actions.WebPageActions;
import ru.sbtqa.tag.pagefactory.web.checks.WebPageChecks;
import ru.sbtqa.tag.pagefactory.web.junit.WebSetupSteps;

@PageEntry(title = "Базовая страница")
public class BasePage implements Page {
    private static PageActions pageActions = new WebPageActions();
    private static PageChecks pageChecks = new WebPageChecks();

    public BasePage() {
        Environment.setDriverService(new MyDriverService());
        WebSetupSteps.initWeb();
        PageFactory.initElements((WebDriver) Environment.getDriverService().getDriver(), this);
        Environment.setPageActions(pageActions);
        Environment.setPageChecks(pageChecks);
    }

    public String toString() {
        return this.getTitle();
    }
}