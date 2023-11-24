package commands;

import commands.changeCommands.ChangeStatusOfAFeedbackCommand;
import core.BoardRepositoryImpl;
import core.contracts.BoardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChangeStatusOfAFeedbackCommandTest {
    BoardRepository boardRepository = new BoardRepositoryImpl();
    private ChangeStatusOfAFeedbackCommand changeStatusOfAFeedbackCommand;
    private static final String FEEDBACK_NAME = "FeedbackNameTest";
    private static final String STATUS = "DONE";

    @BeforeEach
    public void setUp() {
        this.boardRepository = new BoardRepositoryImpl();
        this.changeStatusOfAFeedbackCommand= new ChangeStatusOfAFeedbackCommand(boardRepository);}

        @Test
        public void execute_ShouldReturnCorrectResult(){
            boardRepository.createFeedback(FEEDBACK_NAME, "Problem when logging", 3);

            String result = changeStatusOfAFeedbackCommand.execute(Arrays.asList(FEEDBACK_NAME, STATUS));
            assertEquals(String.format(String.format(ChangeStatusOfAFeedbackCommand.COMMAND_IS_DONE,STATUS)), result);
    }
}
