package models;

import models.contracts.Comment;
import models.contracts.Person;

public class CommentImpl implements Comment {
    public static final String COMMENT = "Comment: %s%nAuthor: %s%n";
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
        return String.format(COMMENT, getContent(), getAuthor().getName());
    }
}
