package Models;

import Models.Contracts.*;
import Models.Enums.Priority;
import Models.Enums.Severity;
import Models.Enums.StatusBug;
import Models.Enums.TaskStatus;
import commands.contracts.Command;

import java.util.ArrayList;
import java.util.List;

public class BugImpl extends TaskImpl implements Bug {

    private static final String EMPTY_STEPS_MESSAGE = "The steps to reproduce are empty";
    private List<String> stepsToReproduce;
    private Priority priority;
    private Severity severity;
    private Person assignee;
    private TaskStatus status;


    public BugImpl(String title, String description, List<String> stepsToReproduce, Priority priority, Severity severity, TaskStatus status, PersonImpl assignee, List<CommentImpl> comments, List<String> history) {
        super(title, description);
        setStepsToReproduce(stepsToReproduce);
        setPriority(priority);
        setSeverity(severity);
        setStatus(status);
        setAssignee(assignee);
    }

    private void setStepsToReproduce(List<String> steps){
        if (steps != null){
            this.stepsToReproduce = steps;
        }else {
            throw new IllegalArgumentException(EMPTY_STEPS_MESSAGE);
        }
    }

    private void setPriority(Priority priority){
        this.priority = priority;
    }

    private void setSeverity(Severity severity){
        this.severity = severity;
    }
    private void setStatus(TaskStatus taskStatus){
        this.status = taskStatus;
    }

    private void setAssignee(Person assignee){
        this.assignee = assignee;
    }


    @Override
    public List<String> getStepsToReproduce() {
        return this.stepsToReproduce;
    }

    @Override
    public Priority getPriority() {
        return this.priority;
    }

    @Override
    public Severity getSeverity() {
        return this.severity;
    }

    @Override
    public Person getAssignee() {
        return this.assignee;
    }

    @Override
    public TaskStatus getTaskStatus() {
        return this.status;
    }

    @Override
    public void addChange(HistoryLog historyLog) {
        super.addHistoryLog(historyLog);
    }

    public void addComment(Comment comment){
        super.addComment(comment);
    }

    public void addHistoryLog(HistoryLog historyLog){
        super.addHistoryLog(historyLog);
    }
}