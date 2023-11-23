package Models.Contracts;

import Models.Enums.StatusFeedback;

public interface Feedback {
    void updateRating(int newRating);
    void updateStatus(StatusFeedback newStatus);
    int getRating();
    StatusFeedback getStatus();
    String getName();
}
