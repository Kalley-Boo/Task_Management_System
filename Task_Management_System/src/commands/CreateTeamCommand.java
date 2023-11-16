package commands;

import commands.contracts.Command;
import core.contracts.BoardRepository;
import exceptions.InvalidInputException;

import java.util.List;

public class CreateTeamCommand implements Command {

    public static final String TEAM_WAS_CREATED = "Team with name %s was created!";
    public static final int EXPECTED_PARAMETERS_COUNT = 1;
    private static final String INVALID_PARAMETERS_COUNT_MESSAGE = String.format(
            "CreatePerson command expects %d parameters.",
            EXPECTED_PARAMETERS_COUNT);

    private final BoardRepository boardRepository;

    public CreateTeamCommand(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    private String createTeam(String name){
        boardRepository.createTeam(name);
        return String.format(TEAM_WAS_CREATED, name);
    }


    @Override
    public String execute(List<String> parameters) {
        if (parameters.size() != EXPECTED_PARAMETERS_COUNT){
            throw new InvalidInputException(INVALID_PARAMETERS_COUNT_MESSAGE);
        }
        String name = parameters.get(0);
        return createTeam(name);
    }
}
