package Models.Contracts;

import Models.Enums.Priority;
import Models.Enums.Severity;
import Models.Enums.TaskStatus;
import Models.PersonImpl;

import java.util.List;

public interface Bug {

    List<String> getStepsToReproduce();
    Priority getPriority();
    Severity getSeverity();
    Person getAssignee();
    TaskStatus getTaskStatus();
}
