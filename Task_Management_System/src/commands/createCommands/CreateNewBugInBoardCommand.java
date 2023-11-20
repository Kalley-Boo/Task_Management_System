package commands.createCommands;

import commands.contracts.Command;
import core.contracts.BoardRepository;

import java.util.List;

public class CreateNewBugInBoardCommand implements Command {

    //public static final int EXPECTED_PARAMETERS_COUNT = ;
    private final BoardRepository boardRepository;

    public CreateNewBugInBoardCommand(BoardRepository boardRepository){
        this.boardRepository = boardRepository;
    }
    // TODO how to get the steps to reproduce from the console

    private String createBugInBoard(){
        return null;
    }

    @Override
    public String execute(List<String> parameters) {
        return null;

    }
}
