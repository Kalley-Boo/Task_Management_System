package core;

import commands.createCommands.*;
import commands.otherCommands.AddCommentToTaskCommand;
import commands.changeCommands.*;
import commands.contracts.Command;
import commands.enums.CommandType;
import commands.otherCommands.AssignTaskToAPersonCommand;
import commands.otherCommands.UnassignTaskToAPersonCommand;
import commands.showCommands.*;
import core.contracts.BoardRepository;
import core.contracts.CommandFactory;
import util.Parser;

public class CommandFactoryImpl implements CommandFactory {
    private static final String COMMAND_NOT_SUPPORTED_MESSAGE = "Command %s is not supported.";

    @Override
    public Command createCommand(int commandNumber, BoardRepository boardRepository) {

        switch (commandNumber) {
            case 1:
                return new CreateNewBugInBoardCommand(boardRepository);
            case 2:
                return new CreateNewStoryInBoardCommand(boardRepository);
            case 3:
                return new CreateNewFeedbackInBoardCommand(boardRepository);
            case 4:
                return new CreateTeamCommand(boardRepository);
            case 5:
                return new ShowTeamsActivityCommand(boardRepository);
            case 6:
                return new CreatePersonCommand(boardRepository);
            case 7:
                return new CreateBoardCommand(boardRepository);
            case 8:
                return new ShowAllPeopleCommand(boardRepository);
            case 9:
                return new ShowAllTeamsCommand(boardRepository);
            case 10:
                return new ShowPersonActivityCommand(boardRepository);
            case 11:
                return new AddCommentToTaskCommand(boardRepository);
            case 12:
                return new ShowTeamMembersCommand(boardRepository);
            case 13:
                return new ChangeStoryPriorityCommand(boardRepository);
            case 14:
                return new ChangeStorySizeCommand(boardRepository);
            case 15:
                return new CreateANewBoardInATeamCommand(boardRepository);
            case 16:
                return new ShowAllTeamBoardsCommand(boardRepository);
            case 17:
                return new ShowBoardSActivityCommand(boardRepository);
            case 18:
                return new ChangeRatingOfAFeedbackCommand(boardRepository);
            case 19:
                return new ChangeStatusOfAFeedbackCommand(boardRepository);
            case 20:
                return new AssignTaskToAPersonCommand(boardRepository);
            case 21:
                return new UnassignTaskToAPersonCommand(boardRepository);
            case 22:
                return new ChangeStoryStatusCommand(boardRepository);
            default:

                throw new UnsupportedOperationException(String.format(COMMAND_NOT_SUPPORTED_MESSAGE, commandNumber));
        }
    }
}
