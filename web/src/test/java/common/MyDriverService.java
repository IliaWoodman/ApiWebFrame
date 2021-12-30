package common;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import ru.sbtqa.tag.pagefactory.drivers.DriverService;
import ru.sbtqa.tag.pagefactory.exceptions.UnsupportedBrowserException;
import ru.sbtqa.tag.pagefactory.web.drivers.CreatedChromeDriver;
import ru.sbtqa.tag.pagefactory.web.drivers.CreatedFirefoxDriver;
import ru.sbtqa.tag.pagefactory.web.properties.WebConfiguration;

public class MyDriverService implements DriverService {
    private static final WebConfiguration PROPERTIES = WebConfiguration.create();
    private WebDriver webDriver = null;

    @Override
    public void mountDriver() {
        String browserName = PROPERTIES.getBrowserName();
        String version = PROPERTIES.getWebDriverVersion();
        String url = PROPERTIES.getStartingUrl();
        DesiredCapabilities capabilities = new DesiredCapabilities(browserName, version, getOs());

        if (browserName.equals("chrome")) {
            setWebDriver(new CreatedChromeDriver(capabilities).get());
        } else if (browserName.equals("firefox")) {
            setWebDriver(new CreatedFirefoxDriver(capabilities).get());
        } else {
            try {
                throw new UnsupportedBrowserException("'" + browserName + "' is not supported yet");
            } catch (UnsupportedBrowserException e) {
                e.printStackTrace();
            }
        }
        webDriver.get(url);
    }

    public Platform getOs() {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("mac")) {
            return Platform.MAC;
        } else if (os.contains("windows")) {
            return Platform.WINDOWS;
        } else if (os.contains("linux")) {
            return Platform.LINUX;
        } else {
            try {
                throw new Exception("Platform no found");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public WebDriver getDriver() {
        if (isDriverEmpty()) {
            mountDriver();
        }
        return webDriver;
    }

    @Override
    public boolean isDriverEmpty() {
        return webDriver == null;
    }

    @Override
    public void demountDriver() {
        webDriver.quit();
    }

    public void setWebDriver(WebDriver driver) {
        webDriver = driver;
    }
}