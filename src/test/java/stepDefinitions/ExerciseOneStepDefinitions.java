package stepDefinitions;

import browsers.Browser;
import io.cucumber.java.After;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.ExerciseOnePage;
import setup.TestContext;

public class ExerciseOneStepDefinitions {

    private final TestContext testContext;

    private ExerciseOnePage exerciseOne;

    public ExerciseOneStepDefinitions(TestContext context) {
        this.testContext = context;
        Browser browser;
        browser = context.getBrowser();
        exerciseOne = new ExerciseOnePage(browser);
    }

    public ExerciseOnePage getExerciseOne() {
        if (!testContext.getBrowser().getDriver().getWindowHandle().equals(exerciseOne.getCurrentWindowHandler())){
            exerciseOne = new ExerciseOnePage(testContext.getBrowser());
        }
        return exerciseOne;
    }

    @When("user clicks correct sequence of buttons")
    public void userClicksCorrectSequenceOfButtons() {
        getExerciseOne().clickCorrectSequence();
    }

    @When("user clicks submit button to check solution")
    public void userClicksSubmitButtonToCheckSolution() {
        getExerciseOne().clickCheckSolutionButton();
    }

    @When("user click opposite then expected sequence")
    public void userClicksOppositeThenExpectedSequence() {
        getExerciseOne().clickOppositeSequence();
    }

    @When("user clicks incorrect sequence of buttons with two additional clicks in the beginning and in the end")
    public void userClicksIncorrectTwoAdditionalClicksInThebeginningAndInTheEnd() {
        getExerciseOne().clickSequenceWithAdditionalClick(true,true);
    }

    @Then("information about correct answer should be displayed")
    public void informationAboutCorrectAnswerShouldBeDisplayed() {
        getExerciseOne().checkIfExpectedMessageDisplayed(true);
    }

    @Then("information about incorrect answer should be displayed")
    public void informationAboutIncorrect_answer_should_be_displayed() {
        getExerciseOne().checkIfExpectedMessageDisplayed(false);
    }

    @When("user skips first click")
    public void userSkipsFirstClick() {
        getExerciseOne().clickSequenceWithoutElementNumber(0);
    }

    @When("user skips last click")
    public void userSkipsLastClick() {
        getExerciseOne().clickSequenceWithoutElementNumber(2);
    }

    @When("user clicks incorrect sequence of buttons with single additional click in the beginning")
    public void userClicksIncorrectSequenceOfButtonsWithSingleAdditionalClickInTheBeginning() {
        getExerciseOne().clickSequenceWithAdditionalClick(true,false);
    }

    @When("user clicks incorrect sequence of buttons with single additional click in the end")
    public void userClicksIncorrectSequenceOfButtonsWithSingleAdditionalClickInTheEnd() {
        getExerciseOne().clickSequenceWithAdditionalClick(false,true);
    }

    @After
    public void closeChromeBrowser() {
        testContext.getBrowser().getDriver().quit();
    }
}
