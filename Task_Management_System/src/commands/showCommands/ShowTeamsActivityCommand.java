package commands.showCommands;

import models.contracts.Team;
import commands.contracts.Command;
import core.contracts.BoardRepository;
import util.Validator;

import java.util.ArrayList;
import java.util.List;

public class ShowTeamsActivityCommand implements Command {
    public static final String THE_TEAM_S_NAME = "the team's name";
    private final List<String> expectedArguments;
    private static final int EXPECTED_PARAM_COUNT = 1;
    private final BoardRepository boardRepository;

    public ShowTeamsActivityCommand(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
        this.expectedArguments = new ArrayList<>();
        expectedArguments.add(THE_TEAM_S_NAME);
    }

    public String showTeamsActivity(String name) {
        Team foundTeam = this.boardRepository.findTeamByName(name);
        return foundTeam.displayHistory();
    }

    @Override
    public String execute(List<String> parameters) {
        Validator.validateArgumentsCount(parameters, EXPECTED_PARAM_COUNT);
        return showTeamsActivity(parameters.get(0));
    }

    @Override
    public List<String> getExpectedArguments() {
        return expectedArguments;
    }
}
