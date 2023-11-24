package commands;

import models.enums.Priority;
import models.enums.Severity;
import commands.otherCommands.AssignTaskToAPersonCommand;
import core.BoardRepositoryImpl;
import core.contracts.BoardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AssignTaskToAPersonCommandTest {
    BoardRepository boardRepository = new BoardRepositoryImpl();
    private AssignTaskToAPersonCommand assignTaskToAPersonCommand;

    @BeforeEach
    public void setUp() {
        this.boardRepository = new BoardRepositoryImpl();
        this.assignTaskToAPersonCommand = new AssignTaskToAPersonCommand(boardRepository);

    }
    @Test
    public void execute_ShouldReturnCorrectResult(){
        String taskName = "New task name";
        String personName = "Dimitar";
        boardRepository.createUnassignedBug(taskName, "Assign task to a person", Collections.singletonList("Should create task,Should assign to person"), Priority.MEDIUM, Severity.CRITICAL);
        boardRepository.createPerson(personName);
        String result = assignTaskToAPersonCommand.execute(Arrays.asList(personName, taskName));
        assertEquals(String.format(String.format(AssignTaskToAPersonCommand.COMMAND_IS_DONE,taskName, personName)), result);
    }

}
