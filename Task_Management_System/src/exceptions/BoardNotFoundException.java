package exceptions;

public class BoardNotFoundException extends RuntimeException{

    public BoardNotFoundException(String text){
        super(text);
    }
}
