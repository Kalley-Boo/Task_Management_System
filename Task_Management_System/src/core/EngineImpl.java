package core;

import commands.contracts.Command;
import core.contracts.BoardRepository;
import core.contracts.CommandFactory;
import core.contracts.Engine;
import util.Parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EngineImpl implements Engine {

    private static final String TERMINATION_COMMAND = "Exit";
    private static final String TERMINATION_COMMAND_MESSAGE = "You exited the application";
    private static final String EMPTY_COMMAND_ERROR = "Command cannot be empty.";
    private static final String COMMAND_ERROR = "You must select from the list of existing commands by typing in its number!";
    private static final String ENTER_ARGUMENT_MESSAGE = "Please enter %s:";


    private final CommandFactory commandFactory;
    private final BoardRepository boardRepository;
    Scanner scanner = new Scanner(System.in);

    public EngineImpl(){
        this.commandFactory = new CommandFactoryImpl();
        this.boardRepository = new BoardRepositoryImpl();
    }
    @Override
    public void start() {

        while (true) {
            try {
                int commandNumber = selectCommand();
                if (commandNumber == 0){
                    continue;
                }
                if (commandNumber == -1){
                    break;
                }
                //arguments
            } catch (Exception ex) {
                if (ex.getMessage() != null && !ex.getMessage().isEmpty()) {
                    System.out.println(ex.getMessage());
                } else {
                    System.out.println(ex);
                }
            }
        }
    }

    @Override
    public int selectCommand() {
        String inputLine = scanner.nextLine();
        if (inputLine.isBlank()) {
            System.out.println(EMPTY_COMMAND_ERROR);
            return 0;
        }
        if (inputLine.equalsIgnoreCase(TERMINATION_COMMAND)) {
            System.out.println(TERMINATION_COMMAND_MESSAGE);
            return -1;
        }
        return extractCommandNumber(inputLine);
    }

    @Override
    public void processCommand(int commandNumber) {
        Command command = commandFactory.createCommand(commandNumber, boardRepository);
        List<String> expectedArguments = command.getExpectedArguments();
        if (expectedArguments.size() == 0){
            String executionResult = command.execute(new ArrayList<>());
            System.out.println(executionResult);
            return;
        }
        List<String> args = collectArguments(expectedArguments);
        String executionResult = command.execute(args);
        System.out.println(executionResult);
    }


    @Override
    public List<String> collectArguments(List<String> expectedArguments) {
        List<String> args = new ArrayList<>();
        for (String argument : expectedArguments) {
            System.out.println(String.format(ENTER_ARGUMENT_MESSAGE, argument));
            String arg = scanner.nextLine().trim();
            args.add(arg);
        }
        return args;
    }

    @Override
    public int extractCommandNumber(String commandInput) {
        return Parser.tryParseInt(commandInput.trim(), COMMAND_ERROR);
    }


    @Override
    public List<String> extractCommandParameters(String inputLine) {
        String[] commandParts = inputLine.split(" ");
        List<String> parameters = new ArrayList<>();
        for (int i = 1; i < commandParts.length; i++) {
            parameters.add(commandParts[i]);
        }
        return parameters;
    }
}
