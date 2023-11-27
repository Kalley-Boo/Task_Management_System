package commands.createCommandsTests;

import commands.changeCommands.ChangeSeverityOfABug;
import commands.createCommands.CreateANewBoardInATeamCommand;
import commands.createCommands.CreateNewBugInBoardCommand;
import commands.createCommands.CreateTeamCommand;
import core.BoardRepositoryImpl;
import core.contracts.BoardRepository;
import exceptions.InvalidInputException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CreateNewBugInBoardTest {

    private static final String BUG_NAME = "BugNameTest";
    private static final String DESCRIPTION = "TestDescription";
    private static final String STEPS = "one,two";
    private static final String PRIORITY = "LOW";
    private static final String SEVERITY = "MINOR";
    private static final String ASSIGNEE = "Unassigned";
    private static final String SEVERITY_NEW = "MAJOR";
    private final BoardRepository boardRepository = new BoardRepositoryImpl();
    private List<String> argsCreate;


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
        argsCreate = new ArrayList<>();
        argsCreate.add(BUG_NAME);
        argsCreate.add(DESCRIPTION);
        argsCreate.add(STEPS);
        argsCreate.add(PRIORITY);
        argsCreate.add(SEVERITY);
        argsCreate.add(ASSIGNEE);
        argsCreate.add("Board");
    }

    @Test
    public void CreateNewBugInBoard_ShouldCreateANewBug_WhenValidArgs() {
        CreateNewBugInBoardCommand create = new CreateNewBugInBoardCommand(boardRepository);
        create.execute(argsCreate);
        Assertions.assertEquals(1, boardRepository.getBugs().size());
    }
    @Test
    public void CreateNewBugInBoard_ShouldThrowAnException_WhenBugAlreadyExists() {
        CreateNewBugInBoardCommand create = new CreateNewBugInBoardCommand(boardRepository);
        create.execute(argsCreate);
        CreateNewBugInBoardCommand create2 = new CreateNewBugInBoardCommand(boardRepository);
        Assertions.assertThrows(InvalidInputException.class, () -> create.execute(argsCreate));
    }

    @Test
    public void getArguments_should_return_a_list(){
        Assertions.assertEquals(new CreateNewBugInBoardCommand(boardRepository).getExpectedArguments().size(), 7);
    }
}
