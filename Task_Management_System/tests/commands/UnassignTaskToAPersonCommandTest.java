package commands;

import commands.contracts.Command;
import commands.createCommands.*;
import commands.otherCommands.UnassignTaskToAPersonCommand;
import core.BoardRepositoryImpl;
import core.contracts.BoardRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class UnassignTaskToAPersonCommandTest {
    private static final String BUG_NAME = "BugNameTest";
    private static final String DESCRIPTION = "TestDescription";
    private static final String STEPS = "one,two";
    private static final String PRIORITY = "LOW";
    private static final String SEVERITY = "MINOR";
    private static final String ASSIGNEE = "Testt";
    private static final String STORY_NAME = "StoryNameTest";
    private static final String SIZE = "LARGE";
    private final BoardRepository boardRepository = new BoardRepositoryImpl();
    private List<String> argsCreate;
    private List<String> argsCreateAssignedStory;

    private UnassignTaskToAPersonCommand unassignTaskToAPersonCommand;

    @BeforeEach
    public void setUp() {
        List<String> personArgs = new ArrayList<>();
        personArgs.add(ASSIGNEE);
        Command createPerson = new CreatePersonCommand(boardRepository);
        createPerson.execute(personArgs);
        List<String> teamArgs = new ArrayList<>();
        teamArgs.add("Team1");
        List<String> boardArgs = new ArrayList<>();
        boardArgs.add("Board");
        boardArgs.add("Team1");
        CreateTeamCommand teamCommand = new CreateTeamCommand(boardRepository);
        teamCommand.execute(teamArgs);

        CreateANewBoardInATeamCommand command = new CreateANewBoardInATeamCommand(boardRepository);
        command.execute(boardArgs);
        argsCreate = new ArrayList<>();
        argsCreate.add(BUG_NAME);
        argsCreate.add(DESCRIPTION);
        argsCreate.add(STEPS);
        argsCreate.add(PRIORITY);
        argsCreate.add(SEVERITY);
        argsCreate.add(ASSIGNEE);
        argsCreate.add("Board");
        CreateNewBugInBoardCommand create = new CreateNewBugInBoardCommand(boardRepository);
        create.execute(argsCreate);

        argsCreateAssignedStory = new ArrayList<>();
        argsCreateAssignedStory.add(STORY_NAME);
        argsCreateAssignedStory.add(DESCRIPTION);
        argsCreateAssignedStory.add(PRIORITY);
        argsCreateAssignedStory.add(SIZE);
        argsCreateAssignedStory.add("Testt");
        argsCreateAssignedStory.add("Board");
        Command create2 = new CreateNewStoryInBoardCommand(boardRepository);
        create2.execute(argsCreateAssignedStory);
    }

    @Test
    public void UnassignTask_ShouldUnassign_WhenBugIsPassed(){
        Command unassign = new UnassignTaskToAPersonCommand(boardRepository);
        List<String> unassignArgs = new ArrayList<>();
        unassignArgs.add(BUG_NAME);
        unassign.execute(unassignArgs);
        Assertions.assertNull(boardRepository.getBugs().get(0).getAssignee());
    }

    @Test
    public void UnassignTask_ShouldUnassign_WhenStoryIsPassed(){
        Command unassign = new UnassignTaskToAPersonCommand(boardRepository);
        List<String> unassignArgs = new ArrayList<>();
        unassignArgs.add(STORY_NAME);
        unassign.execute(unassignArgs);
        Assertions.assertNull(boardRepository.getStories().get(0).getAssignee());
    }

    @Test
    public void getArguments_should_return_a_list() {
        Assertions.assertEquals(new UnassignTaskToAPersonCommand(boardRepository).getExpectedArguments().size(), 1);
    }
}