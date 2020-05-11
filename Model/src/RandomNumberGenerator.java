import java.util.Random;

public enum RandomNumberGenerator {
    ;

    private static final Random generator = new Random();

    public static int numberBetween(int minimumValue, int maximumValue) {
        return minimumValue + generator.nextInt(maximumValue - minimumValue + 1);
    }

}
