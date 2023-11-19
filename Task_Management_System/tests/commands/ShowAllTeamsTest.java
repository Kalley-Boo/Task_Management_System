package commands;
import commands.ShowAllTeamsCommand;
import commands.contracts.Command;
import core.BoardRepositoryImpl;
import core.contracts.BoardRepository;
import Models.TeamImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ShowAllTeamsTest {

    private BoardRepository boardRepository;
    private Command showAllTeamsCommand;

    @BeforeEach
    void setUp() {
        this.boardRepository = new BoardRepositoryImpl();
        this.showAllTeamsCommand = new ShowAllTeamsCommand(boardRepository);
    }

    @Test
    void execute_Should_ReturnTeamsInfo_When_TeamsExist() {
        TeamImpl team1 = new TeamImpl("Team 1");
        TeamImpl team2 = new TeamImpl("Team 2");

        boardRepository.getTeams().add(team1);
        boardRepository.getTeams().add(team2);

        String result = showAllTeamsCommand.execute(List.of());
        System.out.println("Result: " + result);

        Assertions.assertTrue(result.contains(team1.getName()));
        Assertions.assertTrue(result.contains(team2.getName()));
        //TODO
    }

    @Test
    void execute_Should_ReturnEmptyString_When_NoTeamsExist() {
        String result = showAllTeamsCommand.execute(List.of());
        Assertions.assertEquals(ShowAllTeamsCommand.ALL_TEAMS_BANNER, result);
    }

}