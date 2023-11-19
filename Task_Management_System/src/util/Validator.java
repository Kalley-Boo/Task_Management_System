package util;

import java.util.List;

public abstract class Validator {

    private static final String VALUE_OUT_OF_RANGE = "Must be between %d and %d";//to edit
    private static final String INVALID_NUMBER_OF_ARGUMENTS = "Invalid number of arguments. Expected: %d; received: %d.";


    public static void validateIntRange(int value, int min, int max){
        if (value < min || value > max){
            throw new IllegalArgumentException(String.format(VALUE_OUT_OF_RANGE, min, max));
        }
    }
    public static void validateValueInRange(double value, double min, double max, String errorMessage) {
        if (value < min || value > max) {
            throw new IllegalArgumentException(String.format(VALUE_OUT_OF_RANGE, min, max));
        }
    }
    public static void validateStringLength(String stringToValidate, int minLength, int maxLength, String errorMessage) {
        validateValueInRange(stringToValidate.length(), minLength, maxLength, errorMessage);
    }

    public static void validateArgumentsCount(List<String> list, int expectedNumberOfParameters) {
        if (list.size() < expectedNumberOfParameters) {
            throw new IllegalArgumentException(
                    String.format(INVALID_NUMBER_OF_ARGUMENTS, expectedNumberOfParameters, list.size())
            );
        }
    }


}
