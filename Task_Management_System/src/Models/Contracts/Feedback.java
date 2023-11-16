package Models.Contracts;

import Models.Enums.TaskStatus;

public interface Feedback {

    int getRating();
    TaskStatus getStatus();
}
