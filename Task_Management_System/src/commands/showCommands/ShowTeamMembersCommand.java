package commands.showCommands;

import Models.Contracts.Team;
import commands.contracts.Command;
import core.contracts.BoardRepository;
import util.Validator;

import java.util.List;

public class ShowTeamMembersCommand implements Command {
    private static final int EXPECTED_PARAM_COUNT = 1;
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
        Validator.validateArgumentsCount(parameters, EXPECTED_PARAM_COUNT);
        return showTeamMembers(parameters.get(0));
    }
}
