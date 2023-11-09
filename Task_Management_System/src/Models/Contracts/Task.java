package Models.Contracts;

import Models.Enums.TaskStatus;
import Models.Enums.TaskType;

public interface Task extends Commentable{
    String getTitle();
    String getDescription();
    TaskType getType();
    TaskStatus getStatus();
    String getAssignee();
    String getInfo();
    void addComment(Comment comment);
    void removeComment(Comment comment);
}
