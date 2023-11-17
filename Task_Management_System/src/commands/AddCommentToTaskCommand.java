package commands;

import Models.CommentImpl;
import Models.Contracts.Comment;
import Models.Contracts.Task;
import commands.contracts.Command;
import core.contracts.BoardRepository;
import exceptions.InvalidInputException;

import java.util.List;

public class AddCommentToTaskCommand implements Command {
    private static final int EXPECTED_PARAMETERS_COUNT = 3;
    private static final String INVALID_PARAMETERS_COUNT_MESSAGE = String.format(
            "Add comment to task command expects %d parameters.",
            EXPECTED_PARAMETERS_COUNT);
    public static final String COMMENT_ADDED = "Comment added to task %s";
    private final BoardRepository boardRepository;


    public AddCommentToTaskCommand(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public String addCommentToTask(String taskName, Comment comment){
        Task foundTask = this.boardRepository.findTaskByTitle(taskName);
        foundTask.addComment(comment);
        return String.format(COMMENT_ADDED, taskName);
    }

    @Override
    public String execute(List<String> parameters) {
        if (parameters.size() != EXPECTED_PARAMETERS_COUNT) {
            throw new InvalidInputException(INVALID_PARAMETERS_COUNT_MESSAGE);
        }
        String taskName = parameters.get(0);
        String commentContent= parameters.get(1);
        String author = parameters.get(2);
        Comment comment = new CommentImpl(commentContent, author);
        return addCommentToTask(taskName, comment);
    }
}
