package commands;

import Models.Enums.Priority;
import Models.Enums.TaskSize;
import commands.changeCommands.ChangeStoryPriorityCommand;
import core.BoardRepositoryImpl;
import core.contracts.BoardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

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
        assertEquals(String.format(ChangeStoryPriorityCommand.PRIORITY_UPDATED, STORY_NAME, Priority.valueOf(PRIORITY)), result);
    }
}
