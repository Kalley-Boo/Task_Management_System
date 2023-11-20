package commands.otherCommands;

import Models.CommentImpl;
import Models.Contracts.Comment;
import Models.Contracts.Task;
import commands.contracts.Command;
import core.contracts.BoardRepository;
import util.Validator;

import java.util.List;

public class AddCommentToTaskCommand implements Command {
    private static final int EXPECTED_PARAMETERS_COUNT = 3;
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
        Validator.validateArgumentsCount(parameters, EXPECTED_PARAMETERS_COUNT);
        String taskName = parameters.get(0);
        String commentContent= parameters.get(1);
        String author = parameters.get(2);
        Comment comment = new CommentImpl(commentContent, author);
        return addCommentToTask(taskName, comment);
    }
}
