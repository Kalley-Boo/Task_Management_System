package Models;
import Models.Contracts.Comment;
import Models.Contracts.HistoryLog;
import Models.Contracts.Person;
import Models.Enums.Priority;
import Models.Enums.TaskSize;
import Models.Enums.TaskStatus;

import java.util.List;


public class StoryImpl extends TaskImpl {

    private static final String PRINT_template = """
            Id: + %d +\s
             + Title: %s +\s
             + Description: %s + \s
             + Priority: %s + \s
             + Task size: %s + \s
             + Assignee: %s + \n
             + Status: %s + \n
             """;
    private static final String NO_COMMENTS = "There are no comments for this bug";
    private static final String COMMENTS_HEADER = "---COMMENTS---";
    private Priority priority;
    private TaskSize size;
    private TaskStatus status;
    private PersonImpl assignee;
    public StoryImpl(String title, String description, Priority priority, TaskSize size, TaskStatus status, PersonImpl assignee, List<CommentImpl> comments, List<String> history) {
        super(title, description);
        setPriority(priority);
        setSize(size);
        setStatus(status);
    }

    public Priority getPriority() {
        return priority;
    }

    private void setPriority(Priority priority) {
        this.priority = priority;
    }

    public TaskSize getSize() {
        return size;
    }

    private void setSize(TaskSize size) {
        this.size = size;
    }
    public void setStatus(TaskStatus status ){
        this.status=status;
    }

    @Override
    public void addComment(Comment comment) {super.addComment(comment);
    }

    @Override
    public void addChange(HistoryLog historyLog) {super.addHistoryLog(historyLog);
    }
    public Person getAssignee() {
        return assignee;
    }
    private void setAssignee(PersonImpl assignee) {
        this.assignee = assignee;
    }
    public TaskStatus getStatus(){
        return status;
    }

    @Override
    public List<HistoryLog> getHistory() {
        return getHistory();
    }

    @Override
    public String print() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format(PRINT_template, super.getId(), super.getTitle(), super.getDescription(),
                this.priority.toString(), this.size.toString(), this.assignee.getName(), this.status.toString()));
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
}
