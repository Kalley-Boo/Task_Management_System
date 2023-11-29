package commands.showCommandsTests;

import models.contracts.Board;
import commands.showCommands.ShowAllTeamBoardsCommand;
import core.BoardRepositoryImpl;
import core.contracts.BoardRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;


public class ShowAllTeamBoardsCommandTest {
    BoardRepository boardRepository = new BoardRepositoryImpl();
    private ShowAllTeamBoardsCommand showAllTeamBoardsCommand;

    @BeforeEach
    public void setUp() {
        this.boardRepository = new BoardRepositoryImpl();
        this.showAllTeamBoardsCommand = new ShowAllTeamBoardsCommand(boardRepository);}

        @Test
        void execute_ShouldReturnCorrectResult_WhenCalledWithValidParameters(){
       String teamName = "Power5";
       String boardName = "newBoard";
       boardRepository.createTeam(teamName);
            Board board1 = boardRepository.createBoard(boardName);
            boardRepository.findTeamByName(teamName).addBoard(board1);
            String result = showAllTeamBoardsCommand.execute(List.of(teamName));
            String result2 = "These are all boards for team with name Power5.\n---BOARDS---\n---Board---\nnewBoard\nThere are no tasks on this board.\n";
            Assertions.assertEquals(result2, result);
        }
    }

