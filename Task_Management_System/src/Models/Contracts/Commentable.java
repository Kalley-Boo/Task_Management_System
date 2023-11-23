package Models.Contracts;

import java.util.List;

public interface Commentable {
    List<Comment> getComments();
    void addComment(Comment comment);
}
