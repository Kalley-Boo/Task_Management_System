package Models.Contracts;

import Models.Enums.Priority;
import Models.Enums.StatusStory;
import Models.Enums.TaskSize;

public interface Story extends Assigneable{
     Priority getPriority();
     TaskSize getSize();
     StatusStory getStatus();
     Person getAssignee();
     String getName();
     void editAssignee(Person newAssignee);
     void editStatus(StatusStory newStatus);
     void editSize(TaskSize newSize);
     void editPriority(Priority newPriority);
}
