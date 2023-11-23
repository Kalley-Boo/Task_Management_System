package commands.otherCommands;

import Models.CommentImpl;
import Models.Contracts.Comment;
import Models.Contracts.Person;
import Models.Contracts.Task;
import commands.contracts.Command;
import core.contracts.BoardRepository;
import util.Validator;

import java.util.ArrayList;
import java.util.List;

public class AddCommentToTaskCommand implements Command {
    public static final String TASK_NAME = "the task name";
    public static final String THE_CONTENT = "the content of the comment";
    public static final String THE_NAME_OF_THE_AUTHOR = "the name of the author";
    private final List<String> expectedArguments;
    private static final int EXPECTED_PARAMETERS_COUNT = 3;
    public static final String COMMENT_ADDED = "Comment added to task %s";
    private final BoardRepository boardRepository;

    public AddCommentToTaskCommand(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
        expectedArguments = new ArrayList<>();
        expectedArguments.add(TASK_NAME);
        expectedArguments.add(THE_CONTENT);
        expectedArguments.add(THE_NAME_OF_THE_AUTHOR);
    }

    private String addCommentToTask(String taskName, Comment comment) {
        Task foundTask = this.boardRepository.findTaskByTitle(taskName);
        foundTask.addComment(comment);
        return String.format(COMMENT_ADDED, taskName);
    }

    @Override
    public String execute(List<String> parameters) {
        Validator.validateArgumentsCount(parameters, EXPECTED_PARAMETERS_COUNT);
        String taskName = parameters.get(0);
        String commentContent = parameters.get(1);
        Person author = boardRepository.findPersonByName(parameters.get(2));
        Comment comment = new CommentImpl(commentContent, author);
        return addCommentToTask(taskName, comment);
    }

    @Override
    public List<String> getExpectedArguments() {
        return expectedArguments;
    }
}
