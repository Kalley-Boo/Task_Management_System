package commands;

import commands.contracts.Command;
import commands.createCommands.CreatePersonCommand;
import core.BoardRepositoryImpl;
import core.contracts.BoardRepository;
import exceptions.InvalidInputException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CreatePersonCommandTest {

    BoardRepository boardRepository;
    List<String> validInput;
    List<String> invalidInput;

    @BeforeEach
    void setRepo(){
        this.boardRepository = new BoardRepositoryImpl();
        this.validInput = new ArrayList<>();
        validInput.add("Test1");
        this.invalidInput = new ArrayList<>();
        invalidInput.add("Test");
        invalidInput.add("blabla");
    }

    @Test
    public void execute_Should_AddNewPersonToRepository_When_ValidParameters() {
        Command command = new CreatePersonCommand(boardRepository);
        command.execute(validInput);
        Assertions.assertEquals(1, boardRepository.getPeople().size());
    }

    @Test
    public void execute_Should_throwException_When_InValidParameters() {
        Command command = new CreatePersonCommand(boardRepository);
        Assertions.assertThrows(InvalidInputException.class, () -> {
            command.execute(invalidInput);
        });
        }
    }
