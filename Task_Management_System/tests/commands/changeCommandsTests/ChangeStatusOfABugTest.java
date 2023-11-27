package commands.changeCommandsTests;

import commands.changeCommands.ChangeStatusOfABug;
import commands.contracts.Command;
import commands.createCommands.CreateANewBoardInATeamCommand;
import commands.createCommands.CreateNewBugInBoardCommand;
import commands.createCommands.CreatePersonCommand;
import commands.createCommands.CreateTeamCommand;
import commands.createCommandsTests.CreateNewBugInBoardCommandTest;
import core.BoardRepositoryImpl;
import core.contracts.BoardRepository;
import models.enums.StatusBug;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ChangeStatusOfABugTest {

    BoardRepository boardRepository;
    private ChangeStatusOfABug changeStatusOfABug;
    private List<String> argsChange;
    private static final String BUG_NAME = "BugNameTest";
    private static final String DESCRIPTION = "TestDescription";
    private static final String STEPS = "one,two";
    private static final String PRIORITY = "LOW";
    private static final String SEVERITY = "MINOR";
    private static final String ASSIGNEE = "Unassigned";
    private static final String STATUS = "DONE";



    @BeforeEach
    public void setUp() {
        this.boardRepository = new BoardRepositoryImpl();
        this.changeStatusOfABug= new ChangeStatusOfABug(boardRepository);
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
        argsChange.add(STATUS);
    }

    @Test
    public void ChangeStatusOfABug_ShouldChangeTheStatus_When_ValidInput(){
        StatusBug old = boardRepository.findBugByTitle(BUG_NAME).getTaskStatus();
        changeStatusOfABug.execute(argsChange);
        StatusBug updated = boardRepository.findBugByTitle(BUG_NAME).getTaskStatus();
        Assertions.assertNotEquals(old, updated);
    }

    @Test
    public void getArguments_should_return_a_list(){
        Assertions.assertEquals(new ChangeStatusOfABug(boardRepository).getExpectedArguments().size(), 2);
    }
}
