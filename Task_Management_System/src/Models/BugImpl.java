package Models;

import Models.Contracts.*;
import Models.Enums.Priority;
import Models.Enums.Severity;
import Models.Enums.StatusBug;
import exceptions.InvalidInputException;

import java.util.ArrayList;
import java.util.List;


public class BugImpl extends TaskImpl implements Bug {

    public static final String PRINT_template = """
            Id: %d \s
              Title: %s \s
              Description: %s \s
              Priority: %s \s
              Severity: %s \s
              Assignee: %s \n
              Status: %s \n
             """;
    public static final String NO_COMMENTS = "There are no comments for this bug";
    public static final String COMMENTS_HEADER = "---COMMENTS---";
    public static final String UNASSIGNED = "Unassigned";
    public static final String INVALID_INPUT_MESSAGE = "The %s can not be NULL";
    public static final String PRIORITY_CHANGED = "Priority changed from %s to %s";
    public static final String SEVERITY_CHANGED = "Severity changed from %s to %s";
    public static final String STATUS_CHANGED = "Status changed from %s to %s";
    public static final String STEPS_TO_REPRODUCE_CHANGED = "Steps to reproduce changed from %s to %s";
    public static final String ASSIGNEE_CHANGED = "Assignee changed from %s to %s";
    public static final String STEPS = "steps";
    public static final String PRIORITY = "priority";
    public static final String SEVERITY = "severity";
    public static final String STATUS = "status";
    public static final String ASSIGNEE = "assignee";
    public static final String COMMENT = "comment";
    public static final String BUG_WAS_CREATED = "A bug with title %s was created.";

    private List<String> stepsToReproduce;
    private Priority priority;
    private Severity severity;
    private Person assignee;
    private StatusBug status;


    //create a bug with an assignee
    public BugImpl(int id, String title, String description, List<String> stepsToReproduce, Priority priority, Severity severity, Person assignee) {
        super(id, title, description);
        setStepsToReproduce(stepsToReproduce);
        setPriority(priority);
        setSeverity(severity);
        this.status = StatusBug.ACTIVE;
        setAssignee(assignee);
        addChange(new HistoryLogImpl(String.format(BUG_WAS_CREATED, title)));
    }

    // create unassigned bug
    public BugImpl(int id, String title, String description, List<String> stepsToReproduce, Priority priority, Severity severity) {
        super(id, title, description);
        setStepsToReproduce(stepsToReproduce);
        setPriority(priority);
        setSeverity(severity);
        this.status = StatusBug.ACTIVE;
        addChange(new HistoryLogImpl(String.format(BUG_WAS_CREATED, title)));
    }

    public void editPriority(Priority newPriority) {
        Priority oldPriority = this.priority;
        this.priority = newPriority;
        String changeLog = String.format(PRIORITY_CHANGED, oldPriority, newPriority);
        addChange(new HistoryLogImpl(changeLog));
    }

    public void editSeverity(Severity newSeverity) {
        Severity oldSeverity = this.severity;
        this.severity = newSeverity;
        String changeLog = String.format(SEVERITY_CHANGED, oldSeverity, newSeverity);
        addChange(new HistoryLogImpl(changeLog));
    }

    public void editStatus(StatusBug newStatus) {
        StatusBug oldStatus = this.status;
        setStatus(newStatus);
        String changeLog = String.format(STATUS_CHANGED, oldStatus, newStatus);
        addChange(new HistoryLogImpl(changeLog));
    }

    public void editStepsToReproduce(List<String> newSteps) {
        List<String> oldSteps = new ArrayList<>(this.stepsToReproduce);
        this.stepsToReproduce = new ArrayList<>(newSteps);
        String changeLog = String.format(STEPS_TO_REPRODUCE_CHANGED, oldSteps, newSteps);
        addChange(new HistoryLogImpl(changeLog));
    }

    public void editAssignee(Person newAssignee) {
        Person oldAssignee = this.assignee;
        String oldName;
        String newName;
        if (oldAssignee == null) {
            oldName = "unassigned";
        } else oldName = oldAssignee.getName();
        if (newAssignee == null) {
            newName = "unassigned";
        } else {
            newName = newAssignee.getName();
        }
        this.assignee = newAssignee;
        String changeLog = String.format(ASSIGNEE_CHANGED, oldName, newName);
        addChange(new HistoryLogImpl(changeLog));
    }

    private void setStepsToReproduce(List<String> steps) {
        if (steps == null) {
            throw new InvalidInputException(String.format(INVALID_INPUT_MESSAGE, STEPS));
        }
        this.stepsToReproduce = steps;
    }

    private void setPriority(Priority priority) {
        if (priority == null) {
            throw new InvalidInputException(String.format(INVALID_INPUT_MESSAGE, PRIORITY));
        }
        this.priority = priority;
    }

    private void setSeverity(Severity severity) {
        if (severity == null) {
            throw new InvalidInputException(String.format(INVALID_INPUT_MESSAGE, SEVERITY));
        }
        this.severity = severity;
    }

    private void setStatus(StatusBug status) {
        if (status == null) {
            throw new InvalidInputException(String.format(INVALID_INPUT_MESSAGE, STATUS));
        }
        this.status = status;
    }

    private void setAssignee(Person assignee) {
        if (assignee == null) {
            throw new InvalidInputException(String.format(INVALID_INPUT_MESSAGE, ASSIGNEE));
        }
        this.assignee = assignee;
    }

    @Override
    public String print() {
        String assignee;
        if (this.assignee == null) {
            assignee = UNASSIGNED;
        } else {
            assignee = this.assignee.getName();
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format(PRINT_template, super.getId(), super.getTitle(),
                super.getDescription(), this.priority.toString(), this.severity.toString(),
                assignee, this.status.toString()));

        if (super.getComments().isEmpty()) {
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


    @Override
    public void updateAssignee(Person person) {
        this.assignee = person;
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
    public String getTitle() {
        return super.getTitle();
    }

    @Override
    public void addChange(HistoryLog historyLog) {
        super.addHistoryLog(historyLog);
    }

}