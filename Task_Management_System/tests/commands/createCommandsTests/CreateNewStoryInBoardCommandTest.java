package commands.createCommandsTests;

import commands.contracts.Command;
import commands.createCommands.*;
import core.BoardRepositoryImpl;
import core.contracts.BoardRepository;
import exceptions.InvalidInputException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CreateNewStoryInBoardCommandTest {

    private static final String STORY_NAME = "StoryNameTest";
    private static final String DESCRIPTION = "TestDescription";
    private static final String SIZE = "LARGE";
    private static final String PRIORITY = "LOW";
    private static final String ASSIGNEE = "Unassigned";
    private final BoardRepository boardRepository = new BoardRepositoryImpl();
    private List<String> argsCreateUnassigned;
    private List<String> argsCreateAssigned;


    @BeforeEach
    public void setUp() {
        List<String> teamArgs = new ArrayList<>();
        teamArgs.add("Team1");
        List<String> boardArgs = new ArrayList<>();
        boardArgs.add("Board");
        boardArgs.add("Team1");
        CreateTeamCommand teamCommand = new CreateTeamCommand(boardRepository);
        teamCommand.execute(teamArgs);
        CreateANewBoardInATeamCommand command = new CreateANewBoardInATeamCommand(boardRepository);
        command.execute(boardArgs);
        argsCreateUnassigned = new ArrayList<>();
        argsCreateUnassigned.add(STORY_NAME);
        argsCreateUnassigned.add(DESCRIPTION);
        argsCreateUnassigned.add(PRIORITY);
        argsCreateUnassigned.add(SIZE);
        argsCreateUnassigned.add(ASSIGNEE);
        argsCreateUnassigned.add("Board");
        Command createPerson = new CreatePersonCommand(boardRepository);
        List<String> createPersonArgs = new ArrayList<>();
        createPersonArgs.add("Spass");
        createPerson.execute(createPersonArgs);
        argsCreateAssigned = new ArrayList<>();
        argsCreateAssigned.add(STORY_NAME);
        argsCreateAssigned.add(DESCRIPTION);
        argsCreateAssigned.add(PRIORITY);
        argsCreateAssigned.add(SIZE);
        argsCreateAssigned.add("Spass");
        argsCreateAssigned.add("Board");
    }

    @Test
    public void CreateNewStoryInBoard_ShouldCreateANewUnassignedBug_WhenValidArgs() {
        Command create = new CreateNewStoryInBoardCommand(boardRepository);
        create.execute(argsCreateUnassigned);
        Assertions.assertEquals(1, boardRepository.getStories().size());
    }

    @Test
    public void CreateNewStoryInBoard_ShouldCreateANewAssignedBug_WhenValidArgs() {
        Command create = new CreateNewStoryInBoardCommand(boardRepository);
        create.execute(argsCreateAssigned);
        Assertions.assertEquals(1, boardRepository.getStories().size());
    }

    @Test
    public void CreateNewStoryInBoard_ShouldThrowAnException_WhenBugAlreadyExists() {
        Command create = new CreateNewStoryInBoardCommand(boardRepository);
        create.execute(argsCreateUnassigned);
        Assertions.assertThrows(InvalidInputException.class, () -> create.execute(argsCreateUnassigned));
    }

    @Test
    public void getArguments_should_return_a_list() {
        Assertions.assertEquals(new CreateNewStoryInBoardCommand(boardRepository).getExpectedArguments().size(), 6);
    }
}
