package Models.Contracts;

import Models.Enums.TaskStatus;
import Models.Enums.TaskType;
import Models.TeamMember;

import java.util.List;

public interface Task{
    int getId();
    String getTitle();
    String getDescription();
    TaskStatus getStatus();
    TeamMember getAssignee();
    List<String> getComments();
    List<String> getHistory();

    void setTitle(String title);
    void setDescription(String description);
    void setStatus(TaskStatus status);
    void setAssignee(TeamMember assignee);
    void addComment(String comment);
    void addChange(String change);
}
