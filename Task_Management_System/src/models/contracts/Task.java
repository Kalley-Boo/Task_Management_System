package models.contracts;


import java.util.List;

public interface Task extends Printable, Commentable {
    int getId();

    String getTitle();

    String getDescription();

    List<Comment> getComments();

    List<HistoryLog> getHistory();

    void addComment(Comment comment);

    void addHistoryLog(HistoryLog historyLog);
}
