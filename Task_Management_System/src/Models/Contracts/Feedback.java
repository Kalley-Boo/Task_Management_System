package Models.Contracts;

import Models.Enums.StatusFeedback;
import Models.Enums.TaskStatus;

public interface Feedback {

    int getRating();
    StatusFeedback getStatus();
}
