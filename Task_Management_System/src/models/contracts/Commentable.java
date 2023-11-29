package models.contracts;

import java.util.List;

public interface Commentable {
    List<Comment> getComments();

    void addComment(Comment comment);
}
