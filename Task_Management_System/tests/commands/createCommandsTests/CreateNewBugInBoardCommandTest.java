package commands.createCommandsTests;

import commands.createCommands.CreateNewBugInBoardCommand;
import core.BoardRepositoryImpl;
import core.contracts.BoardRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;



public class CreateNewBugInBoardCommandTest {
    BoardRepository boardRepository = new BoardRepositoryImpl();
    private CreateNewBugInBoardCommand createNewBugInBoardCommand;


    @BeforeEach
    public void setUp() {
        this.boardRepository = new BoardRepositoryImpl();
        this.createNewBugInBoardCommand = new CreateNewBugInBoardCommand(boardRepository);}
//with assignee
    @Test
    public void execute_ShouldReturnCorrectResult(){
        String title = "new Bug with the system";
        String description = "New bug that cause problems.";
        String steps = "First see what is the root, Second - take actions.";
        String priority = "HIGH";
        String severity = "CRITICAL";
        String person = "Dimitar";
        boardRepository.createPerson(person);
        String board = "New Board";
        boardRepository.createBoard(board);
        String result = createNewBugInBoardCommand.execute(Arrays.asList(title, description,steps,priority, severity, person,board));
        Assertions.assertEquals(String.format(String.format(CreateNewBugInBoardCommand.ASSIGNED_BUG_CREATED, title, person)), result);
    }
    //with assignee
    @Test
    public void execute_ShouldReturnCorrectResult_without_Assignee(){
        String title = "new Bug with the system";
        String description = "New bug that cause problems.";
        String steps = "First see what is the root, Second - take actions.";
        String priority = "HIGH";
        String severity = "CRITICAL";
        String board = "New Board";
        String person = "unassigned";
        boardRepository.createBoard(board);
        String result = createNewBugInBoardCommand.execute(Arrays.asList(title, description,steps,priority, severity,person, board));
        Assertions.assertEquals(String.format(String.format(CreateNewBugInBoardCommand.UNASSIGNED_BUG_CREATED, title)), result);
    }

}
