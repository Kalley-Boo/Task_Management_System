package models.contracts;

import models.enums.Priority;
import models.enums.Severity;
import models.enums.StatusBug;

import java.util.List;

public interface Bug extends Assigneable {
    String getTitle();

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
