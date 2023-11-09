package Models;

import Models.Contracts.Task;
import Models.Enums.TaskStatus;
import Models.Enums.TaskType;

public abstract  class TaskImpl implements Task {
    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public TaskType getType() {
        return null;
    }

    @Override
    public TaskStatus getStatus() {
        return null;
    }

    @Override
    public String getAssignee() {
        return null;
    }

    @Override
    public String getInfo() {
        return null;
    }

    @Override
    public void addComment(Comment comment) {

    }

    @Override
    public void removeComment(Comment comment) {

    }
}
