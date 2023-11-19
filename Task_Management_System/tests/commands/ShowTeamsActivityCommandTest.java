package commands;

import Models.Contracts.Team;
import Models.TeamImpl;
import commands.contracts.Command;
import core.BoardRepositoryImpl;
import core.contracts.BoardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShowTeamsActivityCommandTest {
    BoardRepository boardRepository = new BoardRepositoryImpl();

    @BeforeEach
    public void setUp() {
        this.boardRepository = new BoardRepositoryImpl();
        TeamImpl team = new TeamImpl("TestTeam");
        team.addBoard(boardRepository.createBoard("TestBoard"));
        boardRepository.createTeam("TestTeam");
    }

    //TODO
}
