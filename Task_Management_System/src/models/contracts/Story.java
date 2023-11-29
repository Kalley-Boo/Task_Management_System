package models.contracts;

import models.enums.Priority;
import models.enums.StatusStory;
import models.enums.TaskSize;

public interface Story extends Assigneable {
    Priority getPriority();

    TaskSize getSize();

    StatusStory getStatus();

    Person getAssignee();

    String getTitle();

    void editAssignee(Person newAssignee);

    void editStatus(StatusStory newStatus);

    void editSize(TaskSize newSize);

    void editPriority(Priority newPriority);
}
