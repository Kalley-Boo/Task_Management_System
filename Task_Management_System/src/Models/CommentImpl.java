package Models;

import Models.Contracts.Comment;

public class CommentImpl implements Comment {
    private final String author;
    private String content;

    public CommentImpl(String content, String author) {
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
    public String getAuthor() {
        return author;
    }

    @Override
    public String print() {
        return String.format("Comment: %s%nAuthor: %s%n", getContent(), getAuthor());
    }
}
