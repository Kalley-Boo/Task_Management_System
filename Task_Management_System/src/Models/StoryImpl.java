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
    public StoryImpl(String title, String description, Priority priority, TaskSize size, PersonImpl assignee) {
        super(title, description);
        setPriority(priority);
        setSize(size);
        this.status = StatusStory.NOT_DONE;
        setAssignee(assignee);
    }

    //create unassigned Story
    public StoryImpl(String title, String description, Priority priority, TaskSize size) {
        super(title, description);
        setPriority(priority);
        setSize(size);
        this.status = StatusStory.NOT_DONE;
    }


    @Override
    public void addChange(HistoryLog historyLog) {
        //TODO
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
