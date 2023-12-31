package commands.createCommandsTests;

import commands.contracts.Command;
import commands.createCommands.*;
import core.BoardRepositoryImpl;
import core.contracts.BoardRepository;
import exceptions.InvalidInputException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CreateNewFeedbackInBoardCommandTest {

    private static final String FEEDBACK_NAME = "FeedbackNameTest";
    private static final String DESCRIPTION = "TestDescription";
    private static final String RATING = "2";
    private final BoardRepository boardRepository = new BoardRepositoryImpl();
    private List<String> argsCreate;


    @BeforeEach
    public void setUp() {
        List<String> teamArgs = new ArrayList<>();
        teamArgs.add("Team1");
        List<String> boardArgs = new ArrayList<>();
        boardArgs.add("Board");
        boardArgs.add("Team1");
        CreateTeamCommand teamCommand = new CreateTeamCommand(boardRepository);
        teamCommand.execute(teamArgs);
        CreateANewBoardInATeamCommand command = new CreateANewBoardInATeamCommand(boardRepository);
        command.execute(boardArgs);
        argsCreate = new ArrayList<>();
        argsCreate.add(FEEDBACK_NAME);
        argsCreate.add(DESCRIPTION);
        argsCreate.add(RATING);
    }

    @Test
    public void CreateNewBugInBoard_ShouldCreateANewFeedBack_WhenValidArgs() {
        Command create = new CreateNewFeedbackInBoardCommand(boardRepository);
        create.execute(argsCreate);
        Assertions.assertEquals(1, boardRepository.getFeedbacks().size());
    }
    @Test
    public void CreateNewFeedbackInBoard_ShouldThrowAnException_WhenBugAlreadyExists() {
        Command create = new CreateNewFeedbackInBoardCommand(boardRepository);
        create.execute(argsCreate);
        Assertions.assertThrows(InvalidInputException.class, () -> create.execute(argsCreate));
    }

    @Test
    public void getArguments_should_return_a_list(){
        Assertions.assertEquals(new CreateNewFeedbackInBoardCommand(boardRepository).getExpectedArguments().size(), 3);
    }
}
