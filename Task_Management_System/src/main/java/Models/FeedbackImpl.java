package Models;

import Models.Contracts.Task;
import Models.Enums.TaskStatus;

import java.util.List;

public class FeedbackImpl extends TaskImpl {

    private int rating;
    private TaskStatus status;


    public FeedbackImpl(int id, String title, String description, int rating, TaskStatus status, List<CommentImpl> comments, List<String> history) {
        super(id, title, description, comments, history);
        setRating(rating);
        setStatus(status);
    }

    public int getRating() {
        return rating;
    }

    private void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public TaskStatus getStatus() {
        return status;
    }

    @Override
    public void setStatus(TaskStatus status) {
        this.status = status;
    }


    @Override
    public void addComment(String comment) {

    }

    @Override
    public void addChange(String change) {

    }
}
