package pageObjects;

import browsers.Browser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static utils.WaitUtils.waitUntilElementVisible;

public abstract class ExercisesBasePage {

    protected static Browser browser;

    private final String currentWindowHandler;
    private WebDriver driver;

    private static final String WAVE_IMAGE_ID = "fala";
    private static final String LOGO_IMAGE_ID = "logo";
    private static final String BLOCK_FOOTER_ID = "block_footer";

    @FindBy(id = WAVE_IMAGE_ID)
    private static WebElement waveImageWebElement;
    @FindBy(id = LOGO_IMAGE_ID)
    private static WebElement logoImageWebElement;
    @FindBy(id = BLOCK_FOOTER_ID)
    private static WebElement blockFooterWebElement;

    public ExercisesBasePage(Browser browser) {
        ExercisesBasePage.browser = browser;
        currentWindowHandler = browser.getDriver().getWindowHandle();
    }

    protected WebDriver getDriver() {
        if(driver == null) {
            driver = browser.getDriver();
        }
        return driver;
    }

    public Browser getBrowser() {
        return this.browser;
    }

    public void setBrowser(Browser browser) {
        this.browser = browser;
    }

    public abstract void initializePageFactory();

    public abstract void waitForPageToBeLoaded();

    public String getCurrentWindowHandler() {
        return currentWindowHandler;
    }

    protected void basePageCommonElementsVisbility() {
        headerVisibility();
        footerElementsVisibility();
    }

    private static void headerVisibility() {
        waitUntilElementVisible(browser.getDriver(), waveImageWebElement);
        waitUntilElementVisible(browser.getDriver(), logoImageWebElement);
    }

    private static void footerElementsVisibility() {
        waitUntilElementVisible(browser.getDriver(), blockFooterWebElement);
    }
}
