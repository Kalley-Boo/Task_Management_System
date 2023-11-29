package commands.changeCommandsTests;

import models.enums.Priority;
import models.enums.TaskSize;
import commands.changeCommands.ChangeStoryPriorityCommand;
import core.BoardRepositoryImpl;
import core.contracts.BoardRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;

public class ChangeStoryPriorityCommandTest {
    BoardRepository boardRepository = new BoardRepositoryImpl();
    private ChangeStoryPriorityCommand changeStoryPriorityCommand;
    private static final String STORY_NAME = "StoryNameTest";
    private static final String PRIORITY = "HIGH";

    @BeforeEach
    public void setUp() {
        this.boardRepository = new BoardRepositoryImpl();
        this.changeStoryPriorityCommand = new ChangeStoryPriorityCommand(boardRepository);
    }

    @Test
    public void execute_ShouldReturnCorrectResult(){
        boardRepository.createUnassignedStory(STORY_NAME, "Description", Priority.LOW,
                TaskSize.MEDIUM);
        String result = changeStoryPriorityCommand.execute(Arrays.asList(STORY_NAME, PRIORITY));
        Assertions.assertEquals(String.format(ChangeStoryPriorityCommand.PRIORITY_UPDATED, STORY_NAME, Priority.valueOf(PRIORITY)), result);
    }

    @Test
    public void getArguments_should_return_a_list(){
        Assertions.assertEquals(new ChangeStoryPriorityCommand(boardRepository).getExpectedArguments().size(), 2);
    }
}
