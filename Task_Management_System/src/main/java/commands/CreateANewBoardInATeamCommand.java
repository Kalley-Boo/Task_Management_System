package commands;
import Models.BoardImpl;
import commands.contracts.Command;
import core.BoardRepositoryImpl;
import core.contracts.BoardRepository;
import exceptions.InvalidInputException;

import java.util.List;

public class CreateANewBoardInATeamCommand implements Command {

    private static final String BOARD_CREATED = "Board with name %s was created in team with name%s!";
    public static final int EXPECTED_PARAMETERS_COUNT = 2;
    private static final String INVALID_PARAMETERS_COUNT_MESSAGE = String.format(
            "CreateANewBoardInATeam command expects %d parameters.",
            EXPECTED_PARAMETERS_COUNT);
    private BoardRepository boardRepository;


    public CreateANewBoardInATeamCommand(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        if (parameters.size() != EXPECTED_PARAMETERS_COUNT){
            throw new InvalidInputException(INVALID_PARAMETERS_COUNT_MESSAGE);
        }
        
        String boardName = parameters.get(0);
        String teamName = parameters.get(1);
        createANewBoardInATeam(boardName, teamName);
        return String.format(BOARD_CREATED, boardName, teamName);
    }
    private void createANewBoardInATeam(String boardName, String teamName){
        boardRepository.createANewBoardInATeam(boardName, teamName);
    }
}
