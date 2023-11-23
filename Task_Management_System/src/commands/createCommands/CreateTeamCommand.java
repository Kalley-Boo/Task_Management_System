package commands.createCommands;

import commands.contracts.Command;
import core.contracts.BoardRepository;
import exceptions.InvalidInputException;
import util.Validator;

import java.util.ArrayList;
import java.util.List;

public class CreateTeamCommand implements Command {
    public static final String A_NAME = "a name (5-15 characters)";
    public static final String TEAM_WAS_CREATED = "Team with name %s was created!";
    public static final int EXPECTED_PARAMETERS_COUNT = 1;
    public static final String TEAM_SAME_NAME_EXISTS = "Team with the same name already exists.";
    public static final String INVALID_NAME_LENGTH = "The length of the title must be 10-15";
    private final List<String> expectedArguments;
    private final BoardRepository boardRepository;

    public CreateTeamCommand(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
        expectedArguments = new ArrayList<>();
        expectedArguments.add(A_NAME);
    }

    private boolean teamExists(String name) {
        return boardRepository.getTeams().stream().anyMatch(team -> team.getName().equals(name));
    }

    private String createTeam(String name) {
        boardRepository.createTeam(name);
        return String.format(TEAM_WAS_CREATED, name);
    }

    @Override
    public String execute(List<String> parameters) {
        Validator.validateArgumentsCount(parameters, EXPECTED_PARAMETERS_COUNT);
        String name = parameters.get(0);
        Validator.validateStringLength(name, 5, 15, INVALID_NAME_LENGTH);
        if (teamExists(name)) {
            throw new InvalidInputException(TEAM_SAME_NAME_EXISTS);
        }
        return createTeam(name);
    }

    @Override
    public List<String> getExpectedArguments() {
        return expectedArguments;
    }
}
