package utils;

import java.util.ResourceBundle;

public class InformationProviderUtils {

    private static final String EXERCISE_FIXED_ADDRESS = "Exercise.fixed.address";
    private static final String TESTING_BROWSER = "Testing.browser";

    private static String getRequiredData(final String key) {
        return ResourceBundle.getBundle("informationProvider").getString(key);
    }

    private InformationProviderUtils(){
    }

    public static String getExerciseFixedAddress() {
       return getRequiredData(EXERCISE_FIXED_ADDRESS);
    }
    public static String getTestingBrowser() {
        return getRequiredData(TESTING_BROWSER);
    }
}
