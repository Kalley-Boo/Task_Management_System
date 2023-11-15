package Models;
import Models.Contracts.Comment;
import Models.Contracts.HistoryLog;
import Models.Contracts.Person;
import Models.Enums.Priority;
import Models.Enums.TaskSize;
import Models.Enums.TaskStatus;

import java.util.List;


public class StoryImpl extends TaskImpl {
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
        return null;//TODO
    }
}
