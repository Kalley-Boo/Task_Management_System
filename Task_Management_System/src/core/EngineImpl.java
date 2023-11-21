package core;

import commands.contracts.Command;
import commands.enums.CommandType;
import core.contracts.BoardRepository;
import core.contracts.CommandFactory;
import core.contracts.Engine;
import util.Parser;
import util.Printer;
import util.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EngineImpl implements Engine {

    private static final String SELECT_COMMAND = "Please select a command number";
    private static final String TERMINATION_COMMAND_MESSAGE = "You exited the application";
    private static final String EMPTY_COMMAND_ERROR = "Command cannot be empty.";
    private static final String COMMAND_ERROR = "You must select from the list of existing commands by typing in its number!";
    private static final String ENTER_ARGUMENT_MESSAGE = "Please enter %s:";
    private static final String MENU_OPTIONS = """
            Please select from the following options by typing the number of the command:
            1. Show commands\s
            2. Select command\s
            3. Exit""";
    private final static int MIN_MENU_OPTION = 1;
    private final static int MAX_MENU_OPTION = 3;

    private final CommandFactory commandFactory;
    private final BoardRepository boardRepository;


    public EngineImpl(){
        this.commandFactory = new CommandFactoryImpl();
        this.boardRepository = new BoardRepositoryImpl();
    }
    @Override
    public void start() {
        while (true) {
            try {
                if (showMenu() == 3){
                    System.out.println(TERMINATION_COMMAND_MESSAGE);
                    break;
                }
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
        System.out.println(SELECT_COMMAND);
        Scanner sc = new Scanner(System.in);
        String inputLine = sc.nextLine();
        if (inputLine.isBlank()) {
            System.out.println(EMPTY_COMMAND_ERROR);
            return 0;
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
        while (true){
            try{
                List<String> args = collectArguments(expectedArguments);
                String executionResult = command.execute(args);
                System.out.println(executionResult);
                break;
            }catch (RuntimeException ex){
                if (ex.getMessage() != null && !ex.getMessage().isEmpty()) {
                    System.out.println(ex.getMessage());
                } else {
                    System.out.println(ex);
                }
            }
        }
    }


    @Override
    public List<String> collectArguments(List<String> expectedArguments) {
        List<String> args = new ArrayList<>();
        for (String argument : expectedArguments) {
            System.out.println(String.format(ENTER_ARGUMENT_MESSAGE, argument));
            Scanner sc = new Scanner(System.in);
            String arg = sc.nextLine().trim();
            args.add(arg);
        }
        return args;
    }

    @Override
    public void showCommandsOptions() {
        int counter = 1;

        CommandType[] commandTypes = CommandType.values();

        for (CommandType commandType : commandTypes) {
            System.out.println(counter++ + ". " + commandType);
        }
    }

    @Override
    public int extractCommandNumber(String commandInput) {
        return Parser.tryParseInt(commandInput.trim(), COMMAND_ERROR);
    }

    public int showMenu(){
        System.out.println(MENU_OPTIONS);
        int command = 0;
        while (true) {
            try {
                Scanner sc = new Scanner(System.in);
                command = sc.nextInt();
                Validator.validateIntRange(command, MIN_MENU_OPTION, MAX_MENU_OPTION);
                break;
            } catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }

        switch (command){
            case 1:
                showCommandsOptions();
                return 1;
            case 2:
                int commandNumber = selectCommand();
                if (commandNumber == 0){
                    return 2;
                }
                processCommand(commandNumber);
                return 2;
            case 3:
                return 3;
            default:
                return 4;
        }
    }
}
