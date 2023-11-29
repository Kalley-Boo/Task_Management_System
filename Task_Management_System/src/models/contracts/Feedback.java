package models.contracts;

import models.enums.StatusFeedback;

public interface Feedback {
    void updateRating(int newRating);

    void updateStatus(StatusFeedback newStatus);

    int getRating();

    StatusFeedback getStatus();

    String getTitle();
}
