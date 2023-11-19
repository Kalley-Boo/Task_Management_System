package commands.showCommands;

import Models.Contracts.Person;
import commands.contracts.Command;
import core.contracts.BoardRepository;

import java.util.List;

public class ShowAllPeopleCommand implements Command {

    private static final String ALL_PEOPLE_HEADER = "---All people with their tasks---";

    private final BoardRepository boardRepository;

    public ShowAllPeopleCommand(BoardRepository boardRepository){
        this.boardRepository = boardRepository;
    }

    private String showAllPeople(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(ALL_PEOPLE_HEADER).append("\n");
        for (Person p : boardRepository.getPeople()) {
            stringBuilder.append(p.print());
            stringBuilder.append("\n");
        }
        stringBuilder.append(ALL_PEOPLE_HEADER);
        return new String(stringBuilder);
    }

    @Override
    public String execute(List<String> parameters) {
        return showAllPeople();
    }
}