package commands.createCommands;

import commands.contracts.Command;
import core.contracts.BoardRepository;
import exceptions.InvalidInputException;
import util.Validator;

import java.util.ArrayList;
import java.util.List;

public class CreatePersonCommand implements Command {

    private final List<String> expectedArguments;
    private static final String PERSON_CREATED = "Person with name %s was created!";
    public static final int EXPECTED_PARAMETERS_COUNT = 1;

    private final BoardRepository boardRepository;

    public CreatePersonCommand(BoardRepository boardRepository){
        this.boardRepository = boardRepository;
        expectedArguments = new ArrayList<>();
        expectedArguments.add("a name");
    }

    private String createPerson(String name){
        boardRepository.createPerson(name);
        return String.format(PERSON_CREATED, name);
    }

    @Override
    public String execute(List<String> parameters) {
        Validator.validateArgumentsCount(parameters, EXPECTED_PARAMETERS_COUNT);
        String name = parameters.get(0);
        return createPerson(name);
    }

    @Override
    public List<String> getExpectedArguments() {
        return expectedArguments;
    }
}