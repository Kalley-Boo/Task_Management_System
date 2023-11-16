package commands;

import Models.Contracts.Team;
import commands.contracts.Command;
import core.contracts.BoardRepository;

import java.util.List;

public class ShowTeamMembersCommand implements Command {
    private static final int EXPECTED_PARAM_COUNT = 1;
    private static final String INVALID_PARAM_COUNT = String.format(
            "The show team members command expects %d parameters.",
            EXPECTED_PARAM_COUNT);
    private final BoardRepository boardRepository;

    public ShowTeamMembersCommand(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public String showTeamMembers(String name){
        Team foundTeam = this.boardRepository.findTeamByName(name);
        return foundTeam.displayMembers();
    }

    @Override
    public String execute(List<String> parameters) {
        if (parameters.size() != EXPECTED_PARAM_COUNT){
            throw new IllegalArgumentException(INVALID_PARAM_COUNT);
        }
        return showTeamMembers(parameters.get(0));
    }
}
