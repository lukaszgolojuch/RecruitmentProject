package stepDefinitions;

import io.cucumber.java.en.Given;
import setup.Initialization;
import setup.TestContext;

import static utils.InformationProviderUtils.getExerciseFixedAddress;
import static utils.InformationProviderUtils.getTestingBrowser;

public class InitializationStepDefinitions {

    public static Initialization initialization;

    public InitializationStepDefinitions(TestContext context) {
        initialization = new Initialization(context);
    }

    @Given("user is on exercise number {int} page")
    public void clientIsOnPageWithSpecifiedNumber(Integer exerciseNumber) {
        switch (exerciseNumber) {
            case 1:
                initialization.setupBrowser(getExerciseFixedAddress() + "/exercise1", getTestingBrowser());
                break;
            default:
                throw new IllegalArgumentException("No such page implemented");
        }
    }
}
