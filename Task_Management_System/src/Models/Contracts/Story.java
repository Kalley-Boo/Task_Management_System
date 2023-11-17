package Models.Contracts;

import Models.Enums.Priority;
import Models.Enums.StatusStory;
import Models.Enums.TaskSize;
import Models.Enums.TaskStatus;
import Models.PersonImpl;

public interface Story {

     Priority getPriority();
     TaskSize getSize();
     StatusStory getStatus();
     Person getAssignee();
}
