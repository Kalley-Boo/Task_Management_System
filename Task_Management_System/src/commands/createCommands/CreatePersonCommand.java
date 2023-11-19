package commands.createCommands;

import commands.contracts.Command;
import core.contracts.BoardRepository;
import exceptions.InvalidInputException;
import util.Validator;

import java.util.List;

public class CreatePersonCommand implements Command {

    private static final String PERSON_CREATED = "Person with name %s was created!";
    public static final int EXPECTED_PARAMETERS_COUNT = 1;

    private final BoardRepository boardRepository;

    public CreatePersonCommand(BoardRepository boardRepository){
        this.boardRepository = boardRepository;
    }

    private String createPerson(String name){
        boardRepository.createPerson(name);
        return String.format(PERSON_CREATED, name);
    }//TODO needs to be specified in which team

    @Override
    public String execute(List<String> parameters) {
        Validator.validateArgumentsCount(parameters, EXPECTED_PARAMETERS_COUNT);
        String name = parameters.get(0);
        return createPerson(name);
    }
}