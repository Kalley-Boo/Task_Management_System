package Models.Contracts;

import Models.Enums.Priority;
import Models.Enums.Severity;
import Models.Enums.StatusBug;
import Models.Enums.TaskStatus;
import Models.PersonImpl;

import java.util.List;

public interface Bug {

    List<String> getStepsToReproduce();
    Priority getPriority();
    Severity getSeverity();
    Person getAssignee();
    StatusBug getTaskStatus();
    void editPriority(Priority newPriority);
    void editSeverity(Severity newSeverity);
    void editStatus(StatusBug newStatus);
    void editStepsToReproduce(List<String> newSteps);
    void editAssignee(Person newAssignee);
    void addComment(Comment comment);
    void addHistoryLog(HistoryLog historyLog);
}
