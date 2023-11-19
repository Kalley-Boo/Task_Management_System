package commands;

import Models.Enums.Priority;
import Models.Enums.TaskSize;
import core.BoardRepositoryImpl;
import core.contracts.BoardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

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
        boardRepository.createUnassignedStory(taskName, "Assign task to a person", Priority.MEDIUM, TaskSize.SMALL);
        boardRepository.createPerson(personName);
        String result = assignTaskToAPersonCommand.execute(Arrays.asList(personName, taskName));
        assertEquals(String.format(String.format(AssignTaskToAPersonCommand.COMMAND_IS_DONE,taskName, personName)), result);
    }

}
