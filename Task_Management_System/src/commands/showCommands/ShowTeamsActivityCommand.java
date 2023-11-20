package commands.showCommands;

import Models.Contracts.Team;
import commands.contracts.Command;
import core.contracts.BoardRepository;
import util.Validator;

import java.util.List;

public class ShowTeamsActivityCommand implements Command {

    private static final int EXPECTED_PARAM_COUNT = 1;
    private final BoardRepository boardRepository;

    public ShowTeamsActivityCommand(BoardRepository boardRepository){
        this.boardRepository = boardRepository;
    }

    public String showTeamsActivity(String name){
        Team foundTeam = this.boardRepository.findTeamByName(name);
        return foundTeam.displayHistory();
    }

    @Override
    public String execute(List<String> parameters) {
        Validator.validateArgumentsCount(parameters, EXPECTED_PARAM_COUNT);
        return showTeamsActivity(parameters.get(0));
    }
}
