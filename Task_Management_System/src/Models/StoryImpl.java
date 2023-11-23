package Models;

import Models.Contracts.Comment;
import Models.Contracts.HistoryLog;
import Models.Contracts.Person;
import Models.Contracts.Story;
import Models.Enums.Priority;
import Models.Enums.StatusStory;
import Models.Enums.TaskSize;
import exceptions.InvalidInputException;


public class StoryImpl extends TaskImpl implements Story {

    public static final String PRINT_template = """
            Id: + %d +\s
             + Title: %s +\s
             + Description: %s + \s
             + Priority: %s + \s
             + Task size: %s + \s
             + Assignee: %s + \n
             + Status: %s + \n
             """;
    public static final String NO_COMMENTS = "There are no comments for this story";
    public static final String COMMENTS_HEADER = "---COMMENTS---";
    public static final String INVALID_INPUT_MESSAGE = "The %s can not be NULL";
    public static final String UNASSIGNED = "Unassigned";
    public static final String PRIORITY_CHANGED = "Priority changed from %s to %s";
    public static final String TASK_SIZE_CHANGED = "Task size changed from %s to %s";
    public static final String STATUS_CHANGED = "Status changed from %s to %s";
    public static final String ASSIGNEE_CHANGED = "Assignee changed from %s to %s";
    public static final String PRIORITY = "priority";
    public static final String SIZE = "size";
    public static final String STATUS = "status";
    public static final String ASSIGNEE = "assignee";
    private Priority priority;
    private TaskSize size;
    private StatusStory status;
    private Person assignee;

    //create a Story with an assignee
    public StoryImpl(int id, String title, String description, Priority priority, TaskSize size, Person assignee) {
        super(id, title, description);
        setPriority(priority);
        setSize(size);
        this.status = StatusStory.NOT_DONE;
        setAssignee(assignee);
    }

    //create unassigned Story
    public StoryImpl(int id, String title, String description, Priority priority, TaskSize size) {
        super(id, title, description);
        setPriority(priority);
        setSize(size);
        this.status = StatusStory.NOT_DONE;
    }


    private void setPriority(Priority priority) {
        if (priority == null) {
            throw new InvalidInputException(String.format(INVALID_INPUT_MESSAGE, PRIORITY));
        }
        this.priority = priority;
    }

    private void setSize(TaskSize size) {
        if (size == null) {
            throw new InvalidInputException(String.format(INVALID_INPUT_MESSAGE, SIZE));
        }
        this.size = size;
    }

    private void setStatus(StatusStory status) {
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
    public void editPriority(Priority newPriority) {
        Priority oldPriority = this.priority;
        setPriority(newPriority);
        String changeLog = String.format(PRIORITY_CHANGED, oldPriority, newPriority);
        addChange(new HistoryLogImpl(changeLog));
    }
    @Override
    public void editSize(TaskSize newSize) {
        TaskSize oldSize = this.size;
        setSize(newSize);
        String changeLog = String.format(TASK_SIZE_CHANGED, oldSize, newSize);
        addChange(new HistoryLogImpl(changeLog));
    }
    @Override
    public void editStatus(StatusStory newStatus) {
        StatusStory oldStatus = this.status;
        setStatus(newStatus);
        String changeLog = String.format(STATUS_CHANGED, oldStatus, newStatus);
        addChange(new HistoryLogImpl(changeLog));
    }
    @Override
    public void editAssignee(Person newAssignee) {
        Person oldAssignee = this.assignee;
        this.assignee = newAssignee;
        String changeLog = String.format(ASSIGNEE_CHANGED, oldAssignee.getName(), newAssignee.getName());
        addChange(new HistoryLogImpl(changeLog));
    }

    @Override
    public void addChange(HistoryLog historyLog) {
        super.addHistoryLog(historyLog);
    }

    @Override
    public Priority getPriority() {
        return priority;
    }

    @Override
    public TaskSize getSize() {
        return size;
    }

    @Override
    public StatusStory getStatus() {
        return status;
    }

    @Override
    public Person getAssignee() {
        return assignee;
    }
    @Override
    public String getName() {
        return getTitle();
    }

    @Override
    public void updateAssignee(Person person) {
        this.assignee = person;
    }

    @Override
    public String print() {
        StringBuilder stringBuilder = new StringBuilder();
        String assignee;
        if (this.assignee == null) {
            assignee = UNASSIGNED;
        } else {
            assignee = this.assignee.getName();
        }
        stringBuilder.append(String.format(PRINT_template, super.getId(),
                super.getTitle(), super.getDescription(), this.priority.toString(),
                this.size.toString(), assignee, this.status.toString()));

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
}
