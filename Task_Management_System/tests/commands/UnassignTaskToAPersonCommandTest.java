package commands;

import models.enums.Priority;
import models.enums.TaskSize;
import commands.otherCommands.UnassignTaskToAPersonCommand;
import core.BoardRepositoryImpl;
import core.contracts.BoardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnassignTaskToAPersonCommandTest {
    BoardRepository boardRepository = new BoardRepositoryImpl();
    private UnassignTaskToAPersonCommand unassignTaskToAPersonCommand;

    @BeforeEach
    public void setUp() {
        this.boardRepository = new BoardRepositoryImpl();
        this.unassignTaskToAPersonCommand = new UnassignTaskToAPersonCommand(boardRepository);}

        @Test
        void execute_ShouldReturnCorrectResult_WhenCalledWithValidParameters(){
        String taskName = "New task name";
            String personName = "Dimitar";
            boardRepository.createUnassignedStory(taskName, "Assign task to a person", Priority.MEDIUM, TaskSize.SMALL);
            boardRepository.createPerson(personName);
            String result = unassignTaskToAPersonCommand.execute(Arrays.asList(personName, taskName));
            assertEquals(String.format(String.format(UnassignTaskToAPersonCommand.COMMAND_IS_DONE, taskName, personName)), result);
        }
    }

