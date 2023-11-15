package commands;

import Models.Contracts.Person;
import commands.contracts.Command;
import core.contracts.BoardRepository;

import java.util.List;

public class ShowAllPeopleCommand implements Command {

    private final BoardRepository boardRepository;

    public ShowAllPeopleCommand(BoardRepository boardRepository){
        this.boardRepository = boardRepository;
    }

    private String showAllPeople(){
        StringBuilder stringBuilder = new StringBuilder();
        for (Person p : boardRepository.getPeople()) {
            stringBuilder.append(p.toString());// TODO rework when print method in PersonImpl(printable interface)
        }
        return new String(stringBuilder);
    }

    @Override
    public String execute(List<String> parameters) {
        return showAllPeople();
    }
}
