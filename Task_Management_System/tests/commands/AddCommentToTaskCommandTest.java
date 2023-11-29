package commands;

import models.CommentImpl;
import models.contracts.Comment;
import models.contracts.Person;
import models.contracts.Task;
import models.enums.Priority;
import models.enums.TaskSize;
import models.PersonImpl;
import commands.otherCommands.AddCommentToTaskCommand;
import core.BoardRepositoryImpl;
import core.contracts.BoardRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;


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
        Person author = new PersonImpl("testT");
        boardRepository.createPerson("testT");
        Comment comment = new CommentImpl(commentContent, author);
        boardRepository.createUnassignedStory(taskName, "HelloWorld", Priority.LOW, TaskSize.MEDIUM);
        Task foundTask = this.boardRepository.findTaskByTitle(taskName);
        foundTask.addComment(comment);
        String result = addCommentToTaskCommand.execute(Arrays.asList(taskName, commentContent, "testT"));

        Assertions.assertEquals(String.format(AddCommentToTaskCommand.COMMENT_ADDED, taskName), result);
    }

    @Test
    public void getArguments_should_return_a_list() {
        Assertions.assertEquals(new AddCommentToTaskCommand(boardRepository).getExpectedArguments().size(), 3);
    }
}





