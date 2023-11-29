package commands.changeCommandsTests;

import commands.changeCommands.ChangePriorityOfABug;
import commands.createCommands.CreateANewBoardInATeamCommand;
import commands.createCommands.CreateNewBugInBoardCommand;
import commands.createCommands.CreateTeamCommand;
import core.BoardRepositoryImpl;
import core.contracts.BoardRepository;
import models.enums.Priority;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ChangePriorityOfABugTest {

    BoardRepository boardRepository;
    private ChangePriorityOfABug changePriorityOfABug;
    private List<String> argsChange;
    private static final String BUG_NAME = "BugNameTest";
    private static final String DESCRIPTION = "TestDescription";
    private static final String STEPS = "one,two";
    private static final String PRIORITY = "LOW";
    private static final String SEVERITY = "MINOR";
    private static final String ASSIGNEE = "Unassigned";
    private static final String PRIORITY_NEW = "high";


    @BeforeEach
    public void setUp() {
        this.boardRepository = new BoardRepositoryImpl();
        this.changePriorityOfABug = new ChangePriorityOfABug(boardRepository);
        List<String> teamArgs = new ArrayList<>();
        teamArgs.add("Team1");
        List<String> boardArgs = new ArrayList<>();
        boardArgs.add("Board");
        boardArgs.add("Team1");
        CreateTeamCommand teamCommand = new CreateTeamCommand(boardRepository);
        teamCommand.execute(teamArgs);
        CreateANewBoardInATeamCommand command = new CreateANewBoardInATeamCommand(boardRepository);
        command.execute(boardArgs);
        List<String> argsCreate = new ArrayList<>();
        argsCreate.add(BUG_NAME);
        argsCreate.add(DESCRIPTION);
        argsCreate.add(STEPS);
        argsCreate.add(PRIORITY);
        argsCreate.add(SEVERITY);
        argsCreate.add(ASSIGNEE);
        argsCreate.add("Board");
        CreateNewBugInBoardCommand create = new CreateNewBugInBoardCommand(boardRepository);
        create.execute(argsCreate);
        argsChange = new ArrayList<>();
        argsChange.add(BUG_NAME);
        argsChange.add(PRIORITY_NEW);
    }

    @Test
    public void ChangeStatusOfABug_ShouldChangeTheStatus_When_ValidInput() {
        Priority old = boardRepository.findBugByTitle(BUG_NAME).getPriority();
        changePriorityOfABug.execute(argsChange);
        Priority updated = boardRepository.findBugByTitle(BUG_NAME).getPriority();
        Assertions.assertNotEquals(old, updated);
    }

    @Test
    public void getArguments_should_return_a_list() {
        Assertions.assertEquals(new ChangePriorityOfABug(boardRepository).getExpectedArguments().size(), 2);
    }
}
