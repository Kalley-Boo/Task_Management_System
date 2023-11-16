package commands;

import Models.Contracts.Team;
import commands.contracts.Command;
import core.contracts.BoardRepository;

import java.util.List;

public class ShowAllTeamsCommand implements Command {

    public static final String ALL_TEAMS_BANNER = "---All teams---";
    private final BoardRepository boardRepository;

    public ShowAllTeamsCommand(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    private String showAllTeams(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(ALL_TEAMS_BANNER);
        for(Team t : boardRepository.getTeams()){
            stringBuilder.append(t.toString());
            stringBuilder.append(" ");
        }
        return new String(stringBuilder);
    }

    public String execute(List<String> parameters){
        return showAllTeams();
    }
}
