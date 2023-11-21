package Models.Contracts;

public interface Comment extends Printable{
    String getContent();

    Person getAuthor();
}
