package commands.createCommands;

import Models.Contracts.Team;
import commands.contracts.Command;
import core.contracts.BoardRepository;
import exceptions.InvalidInputException;
import util.Validator;

import java.util.ArrayList;
import java.util.List;

public class CreateTeamCommand implements Command {
    private final List<String> expectedArguments;
    public static final String TEAM_WAS_CREATED = "Team with name %s was created!";

    public static final int EXPECTED_PARAMETERS_COUNT = 1;
    public static final String TEAM_SAME_NAME_EXISTS = "Team with the same name already exists.";
    public static final String INVALID_NAME_LENGTH = "The length of the title must be 10-15";


    private final BoardRepository boardRepository;
    private final List<Team> teams;

    public CreateTeamCommand(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
        this.teams = boardRepository.getTeams();
        expectedArguments = new ArrayList<>();
        expectedArguments.add("a name (5-15 characters)");
    }

    private boolean teamExists(String name) {
        for (Team team : teams) {
            if (team.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    private String createTeam(String name){
        if (teamExists(name)) {
            throw new InvalidInputException(TEAM_SAME_NAME_EXISTS);
        }
        boardRepository.createTeam(name);
        return String.format(TEAM_WAS_CREATED, name);
    }

    @Override
    public String execute(List<String> parameters) {
        Validator.validateArgumentsCount(parameters, EXPECTED_PARAMETERS_COUNT);
        String name = parameters.get(0);
        Validator.validateStringLength(name, 5, 15, INVALID_NAME_LENGTH);
        return createTeam(name);
    }

    @Override
    public List<String> getExpectedArguments() {
        return expectedArguments;
    }
}
