package commands.createCommandsTests;

import commands.createCommands.CreateANewBoardInATeamCommand;
import core.BoardRepositoryImpl;
import core.contracts.BoardRepository;
import exceptions.InvalidInputException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;


public class CreateANewBoardInATeamCommandTest {

    BoardRepository boardRepository = new BoardRepositoryImpl();
    private CreateANewBoardInATeamCommand createANewBoardInATeamCommand;

    @BeforeEach
    public void setUp() {
        this.boardRepository = new BoardRepositoryImpl();
        this.createANewBoardInATeamCommand = new CreateANewBoardInATeamCommand(boardRepository);
    }

    @Test
    public void execute_ShouldReturnCorrectResult() {
        String boardName = "Telerik";
        String teamName = "Power5";
        boardRepository.createTeam(teamName);
        String result = createANewBoardInATeamCommand.execute(Arrays.asList(boardName, teamName));
        Assertions.assertEquals(String.format(String.format(CreateANewBoardInATeamCommand.BOARD_CREATED, boardName, teamName)), result);
    }

    @Test
    public void execute_ShouldThrowException_When_BoardAlreadyExists() {
        String boardName = "Telerik";
        String teamName = "Power5";
        boardRepository.createTeam(teamName);
        createANewBoardInATeamCommand.execute(Arrays.asList(boardName, teamName));
        Assertions.assertThrows(InvalidInputException.class, () -> createANewBoardInATeamCommand.execute(Arrays.asList(boardName, teamName)));
    }

    @Test
    public void getArguments_should_return_a_list() {
        Assertions.assertEquals(new CreateANewBoardInATeamCommand(boardRepository).getExpectedArguments().size(), 2);
    }
}
