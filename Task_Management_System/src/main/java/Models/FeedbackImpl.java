package Models;

import Models.Contracts.Comment;
import Models.Contracts.Feedback;
import Models.Contracts.Person;
import Models.Enums.TaskStatus;

import java.util.List;

public class FeedbackImpl extends TaskImpl implements Feedback {

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public TaskStatus getStatus() {
        return null;
    }

    @Override
    public Person getAssignee() {
        return null;
    }

    @Override
    public List<String> getHistory() {
        return null;
    }

    @Override
    public void setTitle(String title) {

    }

    @Override
    public void setDescription(String description) {

    }

    @Override
    public void setStatus(TaskStatus status) {

    }

    @Override
    public void setAssignee(Person assignee) {

    }

    @Override
    public void addComment(String comment) {

    }

    @Override
    public void addChange(String change) {

    }
}
