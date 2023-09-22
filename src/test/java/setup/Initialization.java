package setup;

import browsers.Browser;
import browsers.ChromeBrowser;
import org.openqa.selenium.WebDriver;

public class Initialization {

    private final TestContext testContext;

    public Initialization(TestContext testContext) {
        this.testContext = testContext;
    }

    public void setupBrowser(String pageUrl, String browserName) {
        if (testContext.getBrowser() == null) {
            Browser browser = selectBrowser(browserName);
            WebDriver driver = browser.getDriver();
            driver.manage().window().maximize();
            testContext.setBrowser(browser);
            testContext.getBrowser().getDriver().get(pageUrl);
        } else {
            testContext.getBrowser().setDriver(selectBrowser(browserName).getDriver());
            testContext.getBrowser().getDriver().navigate().refresh();
            testContext.getBrowser().getDriver().manage().window().maximize();
        }
    }

    private Browser selectBrowser(String browserName) {
        switch (browserName) {
            case "Chrome":
                return new ChromeBrowser();
            default:
                throw new IllegalArgumentException("Invalid browser name specified");
        }
    }
}
