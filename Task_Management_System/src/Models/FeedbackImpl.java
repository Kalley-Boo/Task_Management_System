package Models;

import Models.Contracts.Comment;
import Models.Contracts.Feedback;
import Models.Contracts.HistoryLog;
import Models.Enums.StatusFeedback;
import exceptions.InvalidInputException;
import util.Validator;


public class FeedbackImpl extends TaskImpl implements Feedback {

    public static final String STATUS_UPDATED = "Status updated from %s to %s.";
    public static final String PRINT_template = """
            Id: + %d +\s
             + Title: %s +\s
             + Description: %s + \s
             + Rating: %d + \s
             + Status: %s + \n
             """;
    public static final String NO_COMMENTS = "There are no comments for this feedback";
    public static final String COMMENTS_HEADER = "---COMMENTS---";
    public static final String INVALID_INPUT_MESSAGE = "The %s can not be NULL";
    public static final int MIN_RATING = 1;
    public static final int MAX_RATING = 10;
    public static final String CHANGED_RATING = "Rating changed from %d to %d.";
    public static final String FEEDBACK_WAS_CREATED = "A feedback with title %s was created.";
    private int rating;
    private StatusFeedback status;

    public FeedbackImpl(int id, String title, String description, int rating) {
        super(id, title, description);
        setRating(rating);
        this.status = StatusFeedback.NEW;
        addChange(new HistoryLogImpl(String.format(FEEDBACK_WAS_CREATED, title)));
    }

    private void setRating(int rating) {
        Validator.validateIntRange(rating, MIN_RATING, MAX_RATING);
        this.rating = rating;
    }

    private void setStatus(StatusFeedback taskStatus) {
        if (taskStatus == null) {
            throw new InvalidInputException(String.format(INVALID_INPUT_MESSAGE, "status"));
        }
        this.status = taskStatus;
    }

    @Override
    public void updateRating(int newRating) {
        Validator.validateIntRange(newRating, MIN_RATING, MAX_RATING);
        int oldRating = this.rating;
        setRating(newRating);
        addChange(new HistoryLogImpl(String.format(CHANGED_RATING, oldRating, newRating)));
    }

    @Override
    public void updateStatus(StatusFeedback newStatus) {
        StatusFeedback oldStatus = this.status;
        setStatus(newStatus);
        addChange(new HistoryLogImpl(String.format(STATUS_UPDATED, oldStatus.toString(), newStatus.toString())));
    }

    @Override
    public String print() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format(PRINT_template, super.getId(), super.getTitle(), super.getDescription(),
                this.rating, this.status.toString()));

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

    @Override
    public void addChange(HistoryLog historyLog) {
        super.addHistoryLog(historyLog);
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
    public String getName() {
        return getTitle();
    }


}
