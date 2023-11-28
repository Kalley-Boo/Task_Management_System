package models;

import models.contracts.Comment;
import models.contracts.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CommentImplTest {

    @Test
    public void testGetContent() {
        String content = "This is a test comment.";
        Person author = new PersonImpl("John Doe");
        Comment comment = new CommentImpl(content, author);

        Assertions.assertEquals(content, comment.getContent());
    }

    @Test
    public void testGetAuthor() {
        String content = "This is a test comment.";
        Person author = new PersonImpl("Jane Smith");
        Comment comment = new CommentImpl(content, author);

        Assertions.assertEquals(author, comment.getAuthor());
    }

    @Test
    public void testPrint() {
        String content = "This is a test comment.";
        Person author = new PersonImpl("John Doe");

        Comment comment = new CommentImpl(content, author);

        String expectedPrintOutput = String.format("Comment: %s%nAuthor: %s%n", content, "John Doe");
        Assertions.assertEquals(expectedPrintOutput, comment.print());
    }
}
