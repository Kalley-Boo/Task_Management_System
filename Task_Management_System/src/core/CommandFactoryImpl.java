package core;

import commands.createCommands.*;
import commands.otherCommands.AddCommentToTaskCommand;
import commands.changeCommands.*;
import commands.contracts.Command;
import commands.otherCommands.AddPersonToATeamCommand;
import commands.otherCommands.AssignTaskToAPersonCommand;
import commands.otherCommands.UnassignTaskToAPersonCommand;
import commands.showCommands.*;
import core.contracts.BoardRepository;
import core.contracts.CommandFactory;

public class CommandFactoryImpl implements CommandFactory {
    private static final String COMMAND_NOT_SUPPORTED_MESSAGE = "Command %s is not supported.";

    @Override
    public Command createCommand(int commandNumber, BoardRepository boardRepository) {

        return switch (commandNumber) {
            //-------------CREATE------------
            case 1 -> new CreatePersonCommand(boardRepository);
            case 2, 3 -> new CreateNewBugInBoardCommand(boardRepository);
            case 4 -> new CreateNewStoryInBoardCommand(boardRepository);
            case 5 -> new CreateNewFeedbackInBoardCommand(boardRepository);
            case 6 -> new CreateTeamCommand(boardRepository);
            case 7 -> new CreateANewBoardInATeamCommand(boardRepository);
            //-------------CHANGE-----------
            case 8 -> new ChangeStoryPriorityCommand(boardRepository);
            case 9 -> new ChangeStorySizeCommand(boardRepository);
            case 10 -> new ChangeStoryStatusCommand(boardRepository);
            case 11 -> new ChangeRatingOfAFeedbackCommand(boardRepository);
            case 12 -> new ChangeStatusOfAFeedbackCommand(boardRepository);
            case 13 -> new ChangePriorityOfABug(boardRepository);
            case 14 -> new ChangeSeverityOfABug(boardRepository);
            case 15 -> new ChangeStatusOfABug(boardRepository);
            //------------OTHERS------------
            case 16 -> new AddCommentToTaskCommand(boardRepository);
            case 17 -> new AddPersonToATeamCommand(boardRepository);
            case 18 -> new AssignTaskToAPersonCommand(boardRepository);
            case 19 -> new UnassignTaskToAPersonCommand(boardRepository);
            //------------SHOW---------------
            case 20 -> new ShowTeamsActivityCommand(boardRepository);
            case 21 -> new ShowAllPeopleCommand(boardRepository);
            case 22 -> new ShowAllTeamsCommand(boardRepository);
            case 23 -> new ShowPersonActivityCommand(boardRepository);
            case 24 -> new ShowTeamMembersCommand(boardRepository);
            case 25 -> new ShowAllTeamBoardsCommand(boardRepository);
            case 26 -> new ShowBoardSActivityCommand(boardRepository);
            default ->
                    throw new UnsupportedOperationException(String.format(COMMAND_NOT_SUPPORTED_MESSAGE, commandNumber));
        };
    }
}
