package Models;

import Models.Contracts.Comment;
import Models.Contracts.Feedback;
import Models.Contracts.HistoryLog;
import Models.Contracts.Task;
import Models.Enums.StatusFeedback;
import Models.Enums.TaskStatus;
import util.Validator;

import java.util.List;

public class FeedbackImpl extends TaskImpl implements Feedback {

    private static final String PRINT_template = """
            Id: + %d +\s
             + Title: %s +\s
             + Description: %s + \s
             + Rating: %d + \s
             + Status: %s + \n
             """;
    private static final String NO_COMMENTS = "There are no comments for this feedback";
    private static final String COMMENTS_HEADER = "---COMMENTS---";

    private static final int MIN_RATING = 1;
    private static final int MAX_RATING = 10;

    private int rating;
    private StatusFeedback status;


    public FeedbackImpl(String title, String description, int rating) {
        super(title, description);
        setRating(rating);
        this.status = StatusFeedback.NEW;
    }

    private void setRating(int rating){
        Validator.validateIntRange(rating, MIN_RATING, MAX_RATING);
        this.rating = rating;
    }

    private void setStatus(StatusFeedback taskStatus){
        this.status = taskStatus;
    }

    @Override
    public int getRating() {
        return this.rating;
    }

    @Override
    public StatusFeedback getStatus() {
        return this.status;
    }

    @Override
    public String print() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format(PRINT_template, super.getId(), super.getTitle(), super.getDescription(),
                this.rating, this.status.toString()));
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
    public void addChange(HistoryLog historyLog) {
        //TODO
    }

    //TODO implement update rating and status, log the changes
}
