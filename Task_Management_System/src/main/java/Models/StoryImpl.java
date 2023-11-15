package Models;
import Models.Contracts.Person;
import Models.Contracts.Task;
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
    public void addComment(String comment) {

    }

    @Override
    public void addChange(String change) {

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
    public List<String> getHistory() {
        return null;
    }
}
