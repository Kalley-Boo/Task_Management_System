package commands.changeCommandsTests;

import commands.changeCommands.ChangeRatingOfAFeedbackCommand;
import core.BoardRepositoryImpl;
import core.contracts.BoardRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;


public class ChangeRatingOfAFeedbackCommandTest {
    BoardRepository boardRepository = new BoardRepositoryImpl();
    private ChangeRatingOfAFeedbackCommand changeRatingOfAFeedback;
    private static final String FEEDBACK_NAME = "FeedbackNameTest";
    private static final String RATING = "4";


    @BeforeEach
    public void setUp() {
        this.boardRepository = new BoardRepositoryImpl();
        this.changeRatingOfAFeedback = new ChangeRatingOfAFeedbackCommand(boardRepository);
    }
    @Test
    public void execute_ShouldReturnCorrectResult(){
        boardRepository.createFeedback(FEEDBACK_NAME, "Problem when logging", 3);
        String result = changeRatingOfAFeedback.execute(Arrays.asList(FEEDBACK_NAME, RATING));
        Assertions.assertEquals(String.format(String.format(ChangeRatingOfAFeedbackCommand.COMMAND_IS_DONE, FEEDBACK_NAME, 4)),result);
    }

    @Test
    public void getArguments_should_return_a_list(){
        Assertions.assertEquals(new ChangeRatingOfAFeedbackCommand(boardRepository).getExpectedArguments().size(), 2);
    }
}
