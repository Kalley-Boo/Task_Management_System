package util;

public abstract class Validator {

    private static final String VALUE_OUT_OF_RANGE = "Must be between %d and %d";//to edit

    public static void validateIntRange(int value, int min, int max){
        if (value < min || value > max){
            throw new IllegalArgumentException(String.format(VALUE_OUT_OF_RANGE, min, max));
        }
    }
}
