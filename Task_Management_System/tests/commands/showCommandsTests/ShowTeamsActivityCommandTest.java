package commands.showCommandsTests;

import commands.showCommands.ShowTeamsActivityCommand;
import core.BoardRepositoryImpl;
import core.contracts.BoardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShowTeamsActivityCommandTest {
    BoardRepository boardRepository = new BoardRepositoryImpl();
    private ShowTeamsActivityCommand showTeamsActivityCommand;
    private final LocalDateTime timestamp;

    public ShowTeamsActivityCommandTest() {
        this.timestamp = LocalDateTime.now();;
    }

    @BeforeEach
    public void setUp() {
        this.boardRepository = new BoardRepositoryImpl();
        this.showTeamsActivityCommand = new ShowTeamsActivityCommand(boardRepository);
    }

    @Test
    void execute_Should_ReturnTeamsActivity_When_TeamsExist() {
        boardRepository.createTeam("Team 1");
        List<String> testList = new ArrayList<>();
        testList.add("Team 1");

        String result = showTeamsActivityCommand.execute(testList);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String formattedTimestamp = timestamp.format(formatter);
        String expected =String.format("Time: %s \nEvent: Name set to: Team 1",formattedTimestamp);
        assertEquals(expected, result);
    }

}
