package Models.Contracts;

import Models.Enums.StatusFeedback;

public interface Feedback {
    void updateRating(int newRating);
    void updateStatus(StatusFeedback newStatus);
    int getRating();
    StatusFeedback getStatus();
    void setRating(int rating);
    void setStatus(StatusFeedback status);
    String getName();
}
