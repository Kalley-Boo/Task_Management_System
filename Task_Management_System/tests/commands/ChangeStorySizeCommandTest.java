package commands;

import Models.Enums.Priority;
import Models.Enums.TaskSize;
import core.BoardRepositoryImpl;
import core.contracts.BoardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class ChangeStorySizeCommandTest {
    BoardRepository boardRepository = new BoardRepositoryImpl();
    private ChangeStorySizeCommand changeStorySizeCommand;
    private static final String STORY_NAME = "StoryNameTest";
    private static final String SIZE = "MEDIUM";

    @BeforeEach
    public void setUp() {
        this.boardRepository = new BoardRepositoryImpl();
        this.changeStorySizeCommand = new ChangeStorySizeCommand(boardRepository);
    }

    @Test
    public void execute_ShouldReturnCorrectResult(){
        boardRepository.createUnassignedStory(STORY_NAME, "Description", Priority.LOW, TaskSize.SMALL);
        String result = changeStorySizeCommand.execute(Arrays.asList(STORY_NAME, SIZE));
        assertEquals(String.format(ChangeStorySizeCommand.SIZE_UPDATED, STORY_NAME, Priority.valueOf(SIZE)), result);


    }
}
