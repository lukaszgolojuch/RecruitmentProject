package browsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeBrowser implements Browser {

    private WebDriver driver;

    public ChromeBrowser() {
        initDriver();
    }

    private void initDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--window-size=1280,800");

        driver = new ChromeDriver(options);
    }

    public WebDriver getDriver() {
        if (this.driver == null) {
            initDriver();
        }
        return this.driver;
    }

    @Override
    public void setDriver(WebDriver webDriver) {
        this.driver = webDriver;
    }
}
