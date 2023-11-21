package Models;

import Models.Contracts.Comment;
import Models.Contracts.Person;

public class CommentImpl implements Comment {
    private final Person author;
    private String content;

    public CommentImpl(String content, Person author) {
        setContent(content);
        this.author = author;
    }

    private void setContent(String content) {
        this.content = content;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public Person getAuthor() {
        return author;
    }

    @Override
    public String print() {
        return String.format("Comment: %s%nAuthor: %s%n", getContent(), getAuthor());
    }
}
