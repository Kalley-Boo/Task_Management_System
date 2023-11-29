package exceptions;

public class BugNotFoundException extends RuntimeException {
    public BugNotFoundException(String message) {
        super(message);
    }
}
