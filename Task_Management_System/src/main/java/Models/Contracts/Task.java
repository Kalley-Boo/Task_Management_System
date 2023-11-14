package Models.Contracts;

import Models.CommentImpl;
import Models.Enums.TaskStatus;

import java.util.List;

public interface Task{
    int getId();
    String getTitle();
    String getDescription();
    TaskStatus getStatus();
    List<String> getHistory();

    void setTitle(String title);
    void setDescription(String description);
    void setStatus(TaskStatus status);
    void addComment(String comment);
    void addChange(String change);

    void addComment(CommentImpl comment);
}
