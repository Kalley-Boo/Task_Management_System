package Models;
import Models.Contracts.Comment;
import Models.Contracts.HistoryLog;
import Models.Contracts.Person;
import Models.Contracts.Story;
import Models.Enums.Priority;
import Models.Enums.StatusStory;
import Models.Enums.TaskSize;
import Models.Enums.TaskStatus;
import exceptions.InvalidInputException;
import util.Parser;

import javax.swing.plaf.basic.BasicIconFactory;
import java.util.List;


public class StoryImpl extends TaskImpl implements Story {

    private static final String PRINT_template = """
            Id: + %d +\s
             + Title: %s +\s
             + Description: %s + \s
             + Priority: %s + \s
             + Task size: %s + \s
             + Assignee: %s + \n
             + Status: %s + \n
             """;
    private static final String NO_COMMENTS = "There are no comments for this story";
    private static final String COMMENTS_HEADER = "---COMMENTS---";
    private static final String INVALID_INPUT_MESSAGE = "The %s can not be NULL";
    private static final String UNASSIGNED = "Unassigned";
    private Priority priority;
    private TaskSize size;
    private StatusStory status;
    private PersonImpl assignee;

    //create a Story with an assignee
    public StoryImpl(int id, String title, String description, Priority priority, TaskSize size, PersonImpl assignee) {
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

    public void editPriority(Priority newPriority) {
        Priority oldPriority = this.priority;
        this.priority = newPriority;
        String changeLog = String.format("Priority changed from %s to %s", oldPriority, newPriority);
        addChange(new HistoryLogImpl(changeLog));
    }

    public void editSize(TaskSize newSize) {
        TaskSize oldSize = this.size;
        this.size = newSize;
        String changeLog = String.format("Task size changed from %s to %s", oldSize, newSize);
        addChange(new HistoryLogImpl(changeLog));
    }

    public void editStatus(StatusStory newStatus) {
        StatusStory oldStatus = this.status;
        this.status = newStatus;
        String changeLog = String.format("Status changed from %s to %s", oldStatus, newStatus);
        addChange(new HistoryLogImpl(changeLog));
    }

    public void editAssignee(PersonImpl newAssignee) {
        Person oldAssignee = this.assignee;
        this.assignee = newAssignee;
        String changeLog = String.format("Assignee changed from %s to %s", oldAssignee.getName(), newAssignee.getName());
        addChange(new HistoryLogImpl(changeLog));
    }


    @Override
    public void addChange(HistoryLog historyLog) {
        super.addHistoryLog(historyLog);
    }

    @Override
    public String print() {
        StringBuilder stringBuilder = new StringBuilder();
        String assignee;
        if (this.assignee == null){
            assignee = UNASSIGNED;
        }else {
            assignee = this.assignee.getName();
        }
        stringBuilder.append(String.format(PRINT_template, super.getId(), super.getTitle(), super.getDescription(),
                this.priority.toString(), this.size.toString(), assignee, this.status.toString()));
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

    @Override
    public Priority getPriority() {
        return null;
    }

    @Override
    public TaskSize getSize() {
        return null;
    }

    @Override
    public StatusStory getStatus() {
        return null;
    }

    @Override
    public Person getAssignee() {
        return null;
    }

    private void setPriority(Priority priority) {
        if (assignee == null){
            throw new InvalidInputException(String.format(INVALID_INPUT_MESSAGE, "priority"));
        }
        this.priority = priority;
    }

    private void setSize(TaskSize size) {
        if (assignee == null){
            throw new InvalidInputException(String.format(INVALID_INPUT_MESSAGE, "size"));
        }
        this.size = size;
    }

    private void setStatus(StatusStory status) {
        if (assignee == null){
            throw new InvalidInputException(String.format(INVALID_INPUT_MESSAGE, "status"));
        }
        this.status = status;
    }

    private void setAssignee(PersonImpl assignee) {
        if (assignee == null){
            throw new InvalidInputException(String.format(INVALID_INPUT_MESSAGE, "assignee"));
        }
        this.assignee = assignee;
    }

    //TODO edit priority, size, status, assignee methods, log the changes
}
