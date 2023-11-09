package Models.Contracts;

import Models.Enums.TaskStatus;
import Models.Enums.TaskType;

public interface Task{
    String getTitle();
    String getDescription();
    TaskType getType();
    TaskStatus getStatus();
    String getAssignee();
    String getInfo();
}
