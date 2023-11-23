package commands;

import commands.contracts.Command;
import commands.createCommands.CreateTeamCommand;
import core.BoardRepositoryImpl;
import core.contracts.BoardRepository;
import exceptions.InvalidInputException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CreateTeamCommandTest {
    BoardRepository boardRepository;
    List<String> validInput;
    List<String> invalidInput;

    @BeforeEach
    void setRepo() {
        this.boardRepository = new BoardRepositoryImpl();
        this.validInput = new ArrayList<>();
        validInput.add("TestTeam");
        this.invalidInput = new ArrayList<>();
        invalidInput.add("Test");
        invalidInput.add("Input1");
    }

    @Test
    public void execute_Should_CreateNewTeam_When_ValidParameters() {
        Command command = new CreateTeamCommand(boardRepository);
        String result = command.execute(validInput);
        Assertions.assertEquals(1, boardRepository.getTeams().size());
        Assertions.assertEquals(String.format(CreateTeamCommand.TEAM_WAS_CREATED, "TestTeam"), result);
    }

    @Test
    public void execute_Should_throwException_When_InvalidParameters() {
        Command command = new CreateTeamCommand(boardRepository);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            command.execute(invalidInput);
        });
        Assertions.assertEquals(0, boardRepository.getTeams().size());
    }

    @Test
    public void execute_Should_ThrowExceptionOnDuplicateTeamName() {
        Command command = new CreateTeamCommand(boardRepository);
        command.execute(validInput);
        Command command1 = new CreateTeamCommand(boardRepository);
        Assertions.assertThrows(InvalidInputException.class, () -> {
            command1.execute(validInput);
        });
    }
}
