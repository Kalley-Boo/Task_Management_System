package commands.showCommandsTests;

import commands.showCommands.ShowBoardSActivityCommand;
import core.BoardRepositoryImpl;
import core.contracts.BoardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.Printer;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShowBoardSActivityCommandTest {
    BoardRepository boardRepository = new BoardRepositoryImpl();
    private ShowBoardSActivityCommand showBoardSActivityCommand;

    @BeforeEach
    public void setUp() {
        this.boardRepository = new BoardRepositoryImpl();
        this.showBoardSActivityCommand = new ShowBoardSActivityCommand(boardRepository);
    }

    @Test
    void execute_ShouldReturnCorrectResult_WhenCalledWithValidParameters() {
        String boardName = "newBoard";
        boardRepository.createBoard(boardName);
        String result1 = showBoardSActivityCommand.execute(List.of(boardName));
        assertEquals(Printer.historyPrinter(boardRepository.getBoards().get(0).getHistoryLog()), result1);

    }
}