package pageObjects;

import browsers.Browser;
import enums.ExerciseOneButtons;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.WaitUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static enums.ExerciseOneButtons.getRandomButton;
import static org.assertj.core.api.Assertions.assertThat;

public class ExerciseOnePage extends ExercisesBasePage {

    private static final String FIRST_BUTTON_ID = "btnButton1";
    private static final String SECOND_BUTTON_ID = "btnButton2";
    private static final String CHECK_SOLUTION_BUTTON_ID = "solution";
    private static final String SEQUENCE_INFO_CODE_BLOCK_CSS = "td:nth-of-type(3) > code";
    private static final String MESSAGE_CODE_BLOCK_CLASS = "wrap";
    private static final String NOT_OK_MESSAGE_TEXT = "NOT OK.";
    private static final String OK_MESSAGE_TEXT = "OK. Good answer";

    @FindBy(id = FIRST_BUTTON_ID)
    private static WebElement firstButtonWebElement;
    @FindBy(id = SECOND_BUTTON_ID)
    private static WebElement secondButtonWebElement;
    @FindBy(id = CHECK_SOLUTION_BUTTON_ID)
    private static WebElement checkSolutionButtonWebElement;
    @FindBy(className = MESSAGE_CODE_BLOCK_CLASS)
    private static WebElement messageCodeBlockWebElement;
    @FindBy(css = SEQUENCE_INFO_CODE_BLOCK_CSS)
    private static WebElement sequenceInfoCodeBlockWebElement;

    WaitUtils waitUtils;

    public ExerciseOnePage(Browser browser) {
        super(browser);
        waitUtils = new WaitUtils(browser);
        initializePageFactory();
        waitForPageToBeLoaded();
    }

    @Override
    public void initializePageFactory() {
        PageFactory.initElements(browser.getDriver(), this);
    }

    @Override
    public void waitForPageToBeLoaded() {
        basePageCommonElementsVisbility();
        commonPageElementsVisibility();
    }

    public void clickSequenceWithAdditionalClick(boolean additionalBeginning, boolean additionalEnd) {
        if (additionalBeginning) {
            clickRandomButton();
        }
        clickCorrectSequence();
        if (additionalEnd) {
            clickRandomButton();
        }
    }

    public void checkIfExpectedMessageDisplayed(boolean isSequenceCorrect) {
        WaitUtils.waitUntilElementClickable(browser.getDriver(), checkSolutionButtonWebElement);
        if (isSequenceCorrect) {
            assertThat(messageCodeBlockWebElement.getText())
                    .isEqualTo(OK_MESSAGE_TEXT);
        } else {
            assertThat(messageCodeBlockWebElement.getText())
                    .isEqualTo(NOT_OK_MESSAGE_TEXT);
        }
    }

    public void clickCorrectSequence() {
        List<ExerciseOneButtons> sequence = getListOfExpectedButtonSequence();
        for (ExerciseOneButtons singleClick : sequence) {
            if (singleClick == ExerciseOneButtons.b1) {
                clickFirstButton();
            } else {
                clickSecondButton();
            }
        }
    }

    public void clickSequenceWithoutElementNumber(int skippedElementNumber) {
        List<ExerciseOneButtons> sequence = getListOfExpectedButtonSequence();
        int actualElementNumber = 0;
        for (ExerciseOneButtons singleClick : sequence) {
            if (singleClick == ExerciseOneButtons.b1 && actualElementNumber != skippedElementNumber) {
                clickFirstButton();
            } else if (singleClick == ExerciseOneButtons.b2 && actualElementNumber != skippedElementNumber) {
                clickSecondButton();
            }
            actualElementNumber++;
        }
    }

    public void clickOppositeSequence() {
        List<ExerciseOneButtons> sequence = getListOfExpectedButtonSequence();
        for (ExerciseOneButtons singleClick : sequence) {
            if (singleClick == ExerciseOneButtons.b1) {
                clickSecondButton();
            } else {
                clickFirstButton();
            }
        }
    }

    public void clickCheckSolutionButton() {
        WaitUtils.waitUntilElementClickable(browser.getDriver(), checkSolutionButtonWebElement);
        checkSolutionButtonWebElement.click();
        waitUtils.waitForInvisibilityOfStringInElement(messageCodeBlockWebElement, "b1");
        waitUtils.waitForInvisibilityOfStringInElement(messageCodeBlockWebElement, "b2");
        waitUtils.waitForInvisibilityOfStringInElement(messageCodeBlockWebElement, "Trail");
    }

    private static void clickFirstButton() {
        WaitUtils.waitUntilElementVisible(browser.getDriver(), firstButtonWebElement);
        WaitUtils.waitUntilElementClickable(browser.getDriver(), firstButtonWebElement);
        firstButtonWebElement.click();
    }

    private static void clickSecondButton() {
        WaitUtils.waitUntilElementVisible(browser.getDriver(), secondButtonWebElement);
        WaitUtils.waitUntilElementClickable(browser.getDriver(), secondButtonWebElement);
        secondButtonWebElement.click();
    }

    private static List<ExerciseOneButtons> getListOfExpectedButtonSequence() {
        WaitUtils.waitUntilElementClickable(browser.getDriver(), sequenceInfoCodeBlockWebElement);
        WaitUtils.waitUntilElementVisible(browser.getDriver(), secondButtonWebElement);
        return Arrays.stream(sequenceInfoCodeBlockWebElement.getText().split("(?<=\\G.{" + 2 + "})"))
            .map(ExerciseOneButtons::valueOf)
            .collect(Collectors.toList());
    }

    private static void clickRandomButton() {
        ExerciseOneButtons button = getRandomButton();
        if (button == ExerciseOneButtons.b1) {
            clickFirstButton();
        } else {
            clickSecondButton();
        }
    }

    private static void commonPageElementsVisibility() {
        WaitUtils.waitUntilElementVisible(browser.getDriver(), firstButtonWebElement);
        WaitUtils.waitUntilElementVisible(browser.getDriver(), secondButtonWebElement);
    }

}
