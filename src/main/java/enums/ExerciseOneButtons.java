package enums;

import java.util.Random;

public enum ExerciseOneButtons {
    b1,
    b2;

    private static final Random random = new Random();

    public static ExerciseOneButtons getRandomButton()  {
        ExerciseOneButtons[] buttons = values();
        return buttons[random.nextInt(buttons.length)];
    }
}
