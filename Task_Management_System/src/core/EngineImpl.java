package core;

import commands.contracts.Command;
import commands.enums.CommandType;
import core.contracts.BoardRepository;
import core.contracts.CommandFactory;
import core.contracts.Engine;
import exceptions.CommandInterruptedException;
import util.Parser;
import util.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EngineImpl implements Engine {

    private static final String SELECT_COMMAND = "Please select a command number";
    private static final String EMPTY_COMMAND_ERROR = "Command cannot be empty.";
    private static final String COMMAND_ERROR = "You must select from the list of existing commands by typing in its number from the list of commands!";
    private static final String ENTER_ARGUMENT_MESSAGE = "Please enter %s:";
    private static final String INTERRUPT_COMMAND = "back";
    private static final String COMMAND_INTERRUPTED_MESSAGE = "You interrupted the command";
    private static final String MENU_OPTIONS = """
            -----------------------------------------------------------------------------
            Please select from the following options by typing the number of the command:
            1. Show commands\s
            2. Select command\s
            3. Exit
            -----------------------------------------------------------------------------""";
    public static final String PROGRAM_INTERRUPTED_MESSAGE = "You exited the application";
    public static final String INVALID_COMMAND = "Please enter a valid option";
    private final static int MIN_MENU_OPTION = 1;
    private final static int MAX_MENU_OPTION = 3;

    private final CommandFactory commandFactory;
    private final BoardRepository boardRepository;

    public EngineImpl() {
        this.commandFactory = new CommandFactoryImpl();
        this.boardRepository = new BoardRepositoryImpl();
    }

    @Override
    public void start() {
        while (true) {
            try {
                selectFromMenu();
            } catch (CommandInterruptedException ex) {
                System.out.println(ex.getMessage());
                break;
            } catch (Exception ex) {
                if (ex.getMessage() != null && !ex.getMessage().isEmpty()) {
                    System.out.println(ex.getMessage());
                } else {
                    System.out.println(ex.getMessage());
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
        if (expectedArguments.isEmpty()) {
            String executionResult = command.execute(new ArrayList<>());
            System.out.println(executionResult);
            return;
        }
        System.out.println("To interrupt the command type 'back'.");
        while (true) {
            try {
                List<String> args = collectArguments(expectedArguments);
                String executionResult = command.execute(args);
                System.out.println(executionResult);
                break;
            } catch (CommandInterruptedException ex) {
                System.out.println(ex.getMessage());
                return;
            } catch (RuntimeException ex) {
                if (ex.getMessage() != null && !ex.getMessage().isEmpty()) {
                    System.out.println(ex.getMessage());
                } else {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }

    @Override
    public List<String> collectArguments(List<String> expectedArguments) {
        List<String> args = new ArrayList<>();
        for (String argument : expectedArguments) {
            System.out.printf((ENTER_ARGUMENT_MESSAGE) + "%n", argument);
            Scanner sc = new Scanner(System.in);
            String arg = sc.nextLine().trim();
            if (arg.equalsIgnoreCase(INTERRUPT_COMMAND)) {
                throw new CommandInterruptedException(COMMAND_INTERRUPTED_MESSAGE);
            }
            args.add(arg);
        }
        return args;
    }

    @Override
    public void showCommandsOptions() {
        int counter = 1;
        CommandType[] commandTypes = CommandType.values();

        for (CommandType commandType : commandTypes) {
            System.out.println(counter++ + ". " + commandType.getCommand());

//            if (counter % 2 == 1) {
//                System.out.println();
//            } else if (counter <= commandTypes.length) {
//                System.out.print("                  ");
//            }
        }
    }

    @Override
    public int extractCommandNumber(String commandInput) {
        return Parser.tryParseInt(commandInput.trim(), COMMAND_ERROR);
    }

    public void selectFromMenu() {
        int command;
        while (true) {
            System.out.println(MENU_OPTIONS);
            try {
                Scanner sc = new Scanner(System.in);
                command = sc.nextInt();
                Validator.validateIntRange(command, MIN_MENU_OPTION, MAX_MENU_OPTION);
                menuCommandProcess(command);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    private void menuCommandProcess(int menuCommandNumber) {
        switch (menuCommandNumber) {
            case 1 -> showCommandsOptions();
            case 2 -> {
                int commandNumber = selectCommand();
                processCommand(commandNumber);
            }
            case 3 -> throw new CommandInterruptedException(PROGRAM_INTERRUPTED_MESSAGE);
            default -> throw new IllegalArgumentException(INVALID_COMMAND);
        }
    }
}
