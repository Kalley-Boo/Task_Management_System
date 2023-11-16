package Models;

import Models.Contracts.Comment;
import Models.Contracts.HistoryLog;
import Models.Contracts.Task;
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
    private static int nextId = 1;
    private int id;
    private String title;
    private String description;
    private List<Comment> comments;
    private List<HistoryLog> historyLog;

    public TaskImpl(String title, String description){
        setId();
        setTitle(title);
        setDescription(description);
        this.comments = new ArrayList<>();
        this.historyLog = new ArrayList<>();
    }
    protected void setId() {
        this.id = nextId;
        nextId++;
    }

    public void setTitle(String title) {
        Validator.validateStringLength(title, TITLE_MIN_VALUE, TITLE_MAX_VALUE, TITLE_ERROR_MESSAGE);
        this.title = title;
    }

    public void setDescription(String description) {
        Validator.validateStringLength(description, DESCRIPTION_MIN_VALUE, DESCRIPTION_MAX_VALUE, DESCRIPTION_ERROR_MESSAGE);
        this.description = description;

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

    public List<Comment> getComments(){
        return comments;
    }

    public abstract void addChange(HistoryLog historyLog);

    public List<HistoryLog> getHistory(){
        return historyLog;
    }

    @Override
    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public void addHistoryLog(HistoryLog historyLog){
        this.historyLog.add(historyLog);
    }

    public abstract String print();

}