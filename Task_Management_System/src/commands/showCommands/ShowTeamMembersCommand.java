package commands.showCommands;

import Models.Contracts.Team;
import commands.contracts.Command;
import core.contracts.BoardRepository;
import util.Validator;

import java.util.ArrayList;
import java.util.List;

public class ShowTeamMembersCommand implements Command {
    public static final String THE_TEAM = "the team's name";
    private final List<String> expectedArguments;
    private static final int EXPECTED_PARAM_COUNT = 1;
    private final BoardRepository boardRepository;

    public ShowTeamMembersCommand(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
        expectedArguments = new ArrayList<>();
        expectedArguments.add(THE_TEAM);
    }

    public String showTeamMembers(String name) {
        Team foundTeam = this.boardRepository.findTeamByName(name);
        return foundTeam.displayMembers();
    }

    @Override
    public String execute(List<String> parameters) {
        Validator.validateArgumentsCount(parameters, EXPECTED_PARAM_COUNT);
        return showTeamMembers(parameters.get(0));
    }

    @Override
    public List<String> getExpectedArguments() {
        return expectedArguments;
    }
}
