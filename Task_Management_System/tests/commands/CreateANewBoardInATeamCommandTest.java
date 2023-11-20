package commands;

import commands.createCommands.CreateANewBoardInATeamCommand;
import core.BoardRepositoryImpl;
import core.contracts.BoardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateANewBoardInATeamCommandTest {

    BoardRepository boardRepository = new BoardRepositoryImpl();
    private CreateANewBoardInATeamCommand createANewBoardInATeamCommand;
    @BeforeEach
    public void setUp() {
        this.boardRepository = new BoardRepositoryImpl();
        this.createANewBoardInATeamCommand = new CreateANewBoardInATeamCommand(boardRepository);}

        @Test
        public void execute_ShouldReturnCorrectResult(){
            String boardName = "Telerik";
            String teamName = "Power5";
            boardRepository.createTeam(teamName);
            String result = createANewBoardInATeamCommand.execute(Arrays.asList(boardName, teamName));
            assertEquals(String.format(String.format(CreateANewBoardInATeamCommand.BOARD_CREATED,boardName,teamName)), result);
        }
}
