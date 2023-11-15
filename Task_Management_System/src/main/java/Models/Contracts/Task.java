package Models.Contracts;

import Models.Enums.TaskStatus;

import java.util.List;

public interface Task extends Printable{
    int getId();
    String getTitle();
    String getDescription();
    List<Comment> getComments();
    List<HistoryLog> getHistory();
    void addComment(Comment comment);
}
