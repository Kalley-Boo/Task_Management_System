package commands;
import commands.contracts.Command;
import commands.showCommands.ShowAllTeamsCommand;
import core.BoardRepositoryImpl;
import core.contracts.BoardRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ShowAllTeamsCommandTest {

    private BoardRepository boardRepository;
    private Command showAllTeamsCommand;

    @BeforeEach
    void setUp() {
        this.boardRepository = new BoardRepositoryImpl();
        this.showAllTeamsCommand = new ShowAllTeamsCommand(boardRepository);
    }

    @Test
    void execute_Should_ReturnTeamsInfo_When_TeamsExist() {
        boardRepository.createTeam("Team 1");
        boardRepository.createTeam("Team 2");

        String result = showAllTeamsCommand.execute(null);
        String expected = ShowAllTeamsCommand.ALL_TEAMS_BANNER + "Team 1 Team 2 ";
        assertEquals(expected, result);
    }

    @Test
    void execute_Should_ReturnEmptyString_When_NoTeamsExist() {
        String result = showAllTeamsCommand.execute(List.of());
        Assertions.assertEquals("---All teams--- \n", result);
    }

}