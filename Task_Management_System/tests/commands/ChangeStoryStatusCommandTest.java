package commands;

import Models.Enums.Priority;
import Models.Enums.StatusStory;
import Models.Enums.TaskSize;
import commands.changeCommands.ChangeStoryStatusCommand;
import core.BoardRepositoryImpl;
import core.contracts.BoardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChangeStoryStatusCommandTest {
    BoardRepository boardRepository = new BoardRepositoryImpl();
    private ChangeStoryStatusCommand changeStoryStatusCommand;
    private static final String STORY_NAME = "StoryNameTest";
    private static final String STATUS = "DONE";

    @BeforeEach
    public void setUp(){
        this.boardRepository = new BoardRepositoryImpl();
        this.changeStoryStatusCommand = new ChangeStoryStatusCommand(boardRepository);
    }
    @Test
    public void execute_ShouldReturnCorrectResult(){
        boardRepository.createUnassignedStory(STORY_NAME, "Description", Priority.LOW,
                TaskSize.MEDIUM);
        String result = changeStoryStatusCommand.execute(Arrays.asList(STORY_NAME, STATUS));
        assertEquals(String.format(ChangeStoryStatusCommand.STATUS_UPDATED, STORY_NAME,
                StatusStory.valueOf(STATUS)), result);
    }
}
