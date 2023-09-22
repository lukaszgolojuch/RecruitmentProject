package setup;

import browsers.Browser;
import org.junit.After;

public class TestContext {

    private Browser browser;

    @After
    public void tearDownBrowser() {
        if (browser != null) {
            browser.getDriver().quit();
        }
    }

    public Browser getBrowser() {
        return browser;
    }

    public void setBrowser(Browser browser) {
        this.browser = browser;
    }
}
