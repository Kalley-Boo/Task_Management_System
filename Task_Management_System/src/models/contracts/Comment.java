package models.contracts;

public interface Comment extends Printable {
    String getContent();

    Person getAuthor();
}
