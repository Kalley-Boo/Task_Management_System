package commands.showCommands;

import models.contracts.Team;
import commands.contracts.Command;
import core.contracts.BoardRepository;

import java.util.ArrayList;
import java.util.List;

public class ShowAllTeamsCommand implements Command {
    public static final String ALL_TEAMS_BANNER = "---All teams--- \n";
    private final BoardRepository boardRepository;

    public ShowAllTeamsCommand(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    private String showAllTeams() {
        StringBuilder stringBuilder = new StringBuilder();
        List<Team> teams = boardRepository.getTeams();
        stringBuilder.append(ALL_TEAMS_BANNER);

        for (Team t : teams) {
            stringBuilder.append(t.getName()).append(" ");
        }
        return new String(stringBuilder);
    }

    public String execute(List<String> parameters) {
        return showAllTeams();
    }

    @Override
    public List<String> getExpectedArguments() {
        return new ArrayList<>();
    }
}
