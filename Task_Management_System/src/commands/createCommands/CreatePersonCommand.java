package commands.createCommands;

import commands.contracts.Command;
import core.contracts.BoardRepository;
import exceptions.InvalidInputException;
import exceptions.PersonNotFoundException;
import util.Validator;

import java.util.ArrayList;
import java.util.List;

public class CreatePersonCommand implements Command {

    public static final String A_NAME_5_15_CHARACTERS = "a name (5-15 characters)";
    private static final String PERSON_CREATED = "Person with name %s was created!";
    private static final String PERSON_ALREADY_EXISTS = "Person with name %s already exists";

    public static final int EXPECTED_PARAMETERS_COUNT = 1;
    public static final String INVALID_NAME_LENGTH = "The length of the title must be 10-15";
    private final List<String> expectedArguments;
    private final BoardRepository boardRepository;

    public CreatePersonCommand(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
        expectedArguments = new ArrayList<>();
        expectedArguments.add(A_NAME_5_15_CHARACTERS);
    }

    private boolean personExists(String name) {
        return boardRepository.getPeople().stream().anyMatch(person -> person.getName().equals(name));
    }

    private String createPerson(String name) {
        boardRepository.createPerson(name);
        return String.format(PERSON_CREATED, name);
    }

    @Override
    public String execute(List<String> parameters) {
        Validator.validateArgumentsCount(parameters, EXPECTED_PARAMETERS_COUNT);
        String name = parameters.get(0);
        Validator.validateStringLength(name, 5, 15, INVALID_NAME_LENGTH);
        if (personExists(name)){
            throw new InvalidInputException(String.format(PERSON_ALREADY_EXISTS, name));
        }
        return createPerson(name);
    }

    @Override
    public List<String> getExpectedArguments() {
        return expectedArguments;
    }
}