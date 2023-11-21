package commands;

import Models.CommentImpl;
import Models.Contracts.Comment;
import Models.Contracts.Person;
import Models.Contracts.Task;
import Models.Enums.Priority;
import Models.Enums.TaskSize;
import Models.PersonImpl;
import commands.otherCommands.AddCommentToTaskCommand;
import core.BoardRepositoryImpl;
import core.contracts.BoardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddCommentToTaskCommandTest {
    BoardRepository boardRepository = new BoardRepositoryImpl();
    private AddCommentToTaskCommand addCommentToTaskCommand;

    @BeforeEach
    public void setUp() {
        this.boardRepository = new BoardRepositoryImpl();
        this.addCommentToTaskCommand = new AddCommentToTaskCommand(boardRepository);
    }
    @Test
    public void testExecute_ShouldReturnCorrectResult_WhenCalledWithValidParameters() {
        String taskName = "taskTestTest";
        String commentContent = "This is a comment.";
        //String author1 = "Author";
        Person author = new PersonImpl("testT");
        boardRepository.createPerson("testT");
        Comment comment = new CommentImpl(commentContent, author);
        boardRepository.createUnassignedStory(taskName, "HelloWorld", Priority.LOW, TaskSize.MEDIUM);
        Task foundTask = this.boardRepository.findTaskByTitle(taskName);
        foundTask.addComment(comment);
        String result = addCommentToTaskCommand.execute(Arrays.asList(taskName, commentContent, "testT"));

        assertEquals(String.format(AddCommentToTaskCommand.COMMENT_ADDED, taskName), result);
    }
}





