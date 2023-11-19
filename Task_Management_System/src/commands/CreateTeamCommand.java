package commands;

import Models.Contracts.Team;
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
    public static final String TEAM_SAME_NAME_EXISTS = "Team with the same name already exists.";

    private final BoardRepository boardRepository;
    private final List<Team> teams;

    public CreateTeamCommand(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
        this.teams = boardRepository.getTeams();
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
        if (parameters.size() != EXPECTED_PARAMETERS_COUNT){
            throw new InvalidInputException(INVALID_PARAMETERS_COUNT_MESSAGE);
        }
        String name = parameters.get(0);
        return createTeam(name);
    }
}
