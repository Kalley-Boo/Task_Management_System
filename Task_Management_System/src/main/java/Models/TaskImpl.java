package Models;

import Models.Contracts.Printable;
import Models.Contracts.Task;
import Models.Enums.TaskStatus;
import util.Validator;

import java.util.ArrayList;
import java.util.List;

public abstract  class TaskImpl implements Task {
    public static final int TITLE_MIN_VALUE = 10;
    public static final int TITLE_MAX_VALUE = 100;
    private static final String TITLE_ERROR_MESSAGE = "Title should has between 10 and 100 symbols.";
    public static final int DESCRIPTION_MIN_VALUE = 10;
    public static final int DESCRIPTION_MAX_VALUE = 500;
    private static final String DESCRIPTION_ERROR_MESSAGE = "Description should have between 10 and 500 symbols.";
    private String title;
    private String description;
    private int id;
    private List<CommentImpl> comments = new ArrayList<>();
    private List<String> history = new ArrayList<>();
    private String historyToBeAdded;

    public TaskImpl(int id, String title, String description, List<CommentImpl> comments, List<String> history){
        setId(id);
        setTitle(title);
        setComments();
        setDescription(description);
        setHistory();
    }
    private void setComments() {
        this.comments = comments;
    }
    private void setHistory(){this.history= history;}
    private void setId(int id) {
        this.id = id;
    }
    public List<CommentImpl> getComments(){
        return comments;
    }
    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public List<String> getHistory() {
        return history;
    }

    @Override
    public void setTitle(String title) {
        Validator.validateStringLength(title, TITLE_MIN_VALUE, TITLE_MAX_VALUE, TITLE_ERROR_MESSAGE);
        this.title = title;

    }

    @Override
    public void setDescription(String description) {
        Validator.validateStringLength(description, DESCRIPTION_MIN_VALUE, DESCRIPTION_MAX_VALUE, DESCRIPTION_ERROR_MESSAGE);
        this.description = description;

    }

    @Override
    public void addComment(CommentImpl comment) {
        comments.add(comment);
    }
    public void addHistory(String historyToBeAdded){
        history.add(historyToBeAdded);
    }

}

