package Models;

import Models.Contracts.Feedback;
import Models.Contracts.HistoryLog;
import Models.Contracts.Task;
import Models.Enums.TaskStatus;
import util.Validator;

import java.util.List;

public class FeedbackImpl extends TaskImpl implements Feedback {

    private static final int MIN_RATING = 1;
    private static final int MAX_RATING = 10;

    private int rating;
    private TaskStatus status;


    public FeedbackImpl(String title, String description, int rating, TaskStatus status, List<CommentImpl> comments, List<String> history) {
        super(title, description);
        setRating(rating);
        setStatus(status);
    }

    private void setRating(int rating){
        Validator.validateIntRange(rating, MIN_RATING, MAX_RATING);
        this.rating = rating;
    }

    private void setStatus(TaskStatus taskStatus){
        this.status = taskStatus;
    }

    @Override
    public int getRating() {
        return this.rating;
    }

    @Override
    public TaskStatus getStatus() {
        return this.status;
    }

    @Override
    public String print() {
        return null;//TODO
    }

    @Override
    public void addChange(HistoryLog historyLog) {
        //TODO
    }

    //TODO implement update rating and status
}
