package Models;

import Models.Contracts.*;
import Models.Enums.Priority;
import Models.Enums.Severity;
import Models.Enums.StatusBug;
import Models.Enums.TaskStatus;
import commands.contracts.Command;
import exceptions.InvalidInputException;

import java.util.ArrayList;
import java.util.List;

public class BugImpl extends TaskImpl implements Bug {

    private static final String PRINT_template = """
            Id: + %d +\s
             + Title: %s +\s
             + Description: %s + \s
             + Priority: %s + \s
             + Severity: %s + \s
             + Assignee: %s + \n
             + Status: %s + \n
             """;
    private static final String NO_COMMENTS = "There are no comments for this bug";
    private static final String COMMENTS_HEADER = "---COMMENTS---";
    private static final String UNASSIGNED = "Unassigned";
    private static final String INVALID_INPUT_MESSAGE = "The %s can not be NULL";

    private List<String> stepsToReproduce;
    private Priority priority;
    private Severity severity;
    private Person assignee;
    private StatusBug status;

    //create a bug with an assignee
    public BugImpl(int id, String title, String description, List<String> stepsToReproduce, Priority priority, Severity severity, PersonImpl assignee) {
        super(id, title, description);
        setStepsToReproduce(stepsToReproduce);
        setPriority(priority);
        setSeverity(severity);
        this.status = StatusBug.ACTIVE;
        setAssignee(assignee);
    }

    // create unassigned bug
    public BugImpl(int id, String title, String description, List<String> stepsToReproduce, Priority priority, Severity severity) {
        super(id, title, description);
        setStepsToReproduce(stepsToReproduce);
        setPriority(priority);
        setSeverity(severity);
        this.status = StatusBug.ACTIVE;
    }

    private void setStepsToReproduce(List<String> steps){
        if (steps == null){
            throw new InvalidInputException(String.format(INVALID_INPUT_MESSAGE, "steps"));
        }
        this.stepsToReproduce = steps;
    }

    private void setPriority(Priority priority){
        if (assignee == null){
            throw new InvalidInputException(String.format(INVALID_INPUT_MESSAGE, "priority"));
        }
        this.priority = priority;
    }

    private void setSeverity(Severity severity){
        if (severity == null){
            throw new InvalidInputException(String.format(INVALID_INPUT_MESSAGE, "severity"));
        }
        this.severity = severity;
    }
    private void setStatus(StatusBug status){
        if (status == null){
            throw new InvalidInputException(String.format(INVALID_INPUT_MESSAGE, "status"));
        }
        this.status = status;
    }

    private void setAssignee(Person assignee){
        if (assignee == null){
            throw new InvalidInputException(String.format(INVALID_INPUT_MESSAGE, "assignee"));
        }
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
    public StatusBug getTaskStatus() {
        return this.status;
    }

    @Override
    public void addChange(HistoryLog historyLog) {
        super.addHistoryLog(historyLog);
    }

    public void addComment(Comment comment){
        if (comment == null){
            throw new InvalidInputException(String.format(INVALID_INPUT_MESSAGE, "comment"));
        }
        super.addComment(comment);
    }

    public void addHistoryLog(HistoryLog historyLog){
        super.addHistoryLog(historyLog);
    }

    @Override
    public String print() {
        String assignee;
        if (this.assignee == null){
            assignee = UNASSIGNED;
        }else {
            assignee = this.assignee.getName();
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format(PRINT_template, super.getId(), super.getTitle(), super.getDescription(),
                this.priority.toString(), this.severity.toString(), assignee, this.status.toString()));
        if (super.getComments().isEmpty()){
            stringBuilder.append(NO_COMMENTS);
            return new String(stringBuilder);
        }
        stringBuilder.append("\n").append(COMMENTS_HEADER).append("\n");
        for (Comment comment : super.getComments()) {
            stringBuilder.append(comment.print()).append("\n");
        }
        stringBuilder.append(COMMENTS_HEADER);
        return new String(stringBuilder);
    }

    //TODO edit priority, severity, status, steps to reproduce, assignee EDITING METHODS, log the changes
}