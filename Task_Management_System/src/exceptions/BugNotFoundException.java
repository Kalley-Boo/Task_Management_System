package exceptions;

public class BugNotFoundException extends RuntimeException{
    public BugNotFoundException(String text){
        super(text);
    }
}
