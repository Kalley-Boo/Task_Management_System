package core;

import commands.contracts.Command;
import core.contracts.BoardRepository;
import core.contracts.CommandFactory;
import core.contracts.Engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EngineImpl implements Engine {

    private static final String TERMINATION_COMMAND = "Exit";
    private static final String EMPTY_COMMAND_ERROR = "Command cannot be empty.";

    private final CommandFactory commandFactory;
    private final BoardRepository boardRepository;

    public EngineImpl(){
        this.commandFactory = new CommandFactoryImpl();
        this.boardRepository = new BoardRepositoryImpl();
    }
    @Override
    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                String inputLine = scanner.nextLine();
                if (inputLine.isBlank()) {
                    System.out.println(EMPTY_COMMAND_ERROR);
                    continue;
                }
                if (inputLine.equalsIgnoreCase(TERMINATION_COMMAND)) {
                    break;
                }
                processCommand(inputLine);
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
    public void processCommand(String inputLine) {
        String commandName = extractCommandName(inputLine);
        Command command = commandFactory.createCommand(commandName, boardRepository);
        List<String> parameters = extractCommandParameters(inputLine);
        String executionResult = command.execute(parameters);
        System.out.println(executionResult);
    }

    @Override
    public String extractCommandName(String inputLine) {
        return inputLine.split(" ")[0];
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
