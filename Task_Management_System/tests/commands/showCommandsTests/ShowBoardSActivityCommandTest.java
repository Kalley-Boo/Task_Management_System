package commands.showCommandsTests;

import commands.showCommands.ShowBoardSActivityCommand;
import core.BoardRepositoryImpl;
import core.contracts.BoardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class ShowBoardSActivityCommandTest {
    BoardRepository boardRepository = new BoardRepositoryImpl();
    private ShowBoardSActivityCommand showBoardSActivityCommand;

    @BeforeEach
    public void setUp() {
        this.boardRepository = new BoardRepositoryImpl();
        this.showBoardSActivityCommand = new ShowBoardSActivityCommand(boardRepository);}

    @Test
    void execute_ShouldReturnCorrectResult_WhenCalledWithValidParameters(){
        String boardName = "newBoard";
        boardRepository.createBoard(boardName);
        String result1 = showBoardSActivityCommand.execute(List.of(boardName));
        String result2 = "The history log is empty";
        assertEquals(result2, result1);

    }

}
