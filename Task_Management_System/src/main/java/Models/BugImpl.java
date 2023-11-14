package Models;

import Models.Contracts.Person;
import Models.Contracts.Task;
import Models.Enums.Priority;
import Models.Enums.Severity;
import Models.Enums.StatusBug;
import Models.Enums.TaskStatus;

import java.util.ArrayList;
import java.util.List;

public class BugImpl extends TaskImpl {
    private List<String> stepsToReproduce = new ArrayList<>();
    private Priority priority;
    private Severity severity;
    private PersonImpl assignee;
    private TaskStatus status;


    public BugImpl(int id, String title, String description, List<String> stepsToReproduce, Priority priority, Severity severity, TaskStatus status, PersonImpl assignee, List<CommentImpl> comments, List<String> history) {
        super(id, title, description, comments, history);
        setStepsToReproduce(stepsToReproduce);
        setPriority(priority);
        setSeverity(severity);
        setStatus(status);
        setAssignee(assignee);
    }

    public List<String> getStepsToReproduce() {
        return stepsToReproduce;
    }

    public void setStepsToReproduce(List<String> stepsToReproduce) {
        this.stepsToReproduce = stepsToReproduce;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Severity getSeverity() {
        return severity;
    }

    public void setSeverity(Severity severity) {
        this.severity = severity;
    }

    @Override
    public TaskStatus getStatus() {
        return status;
    }

    public Person getAssignee() {
        return assignee;
    }

    @Override
    public void setStatus(TaskStatus status) {
           this.status=status;
    }

    @Override
    public void addComment(String comment) {

    }

    @Override
    public void addChange(String change) {

    }

    public void setAssignee(PersonImpl assignee) {
        this.assignee = assignee;
    }


}
