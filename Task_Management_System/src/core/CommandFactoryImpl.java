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

        switch (commandNumber) {
            //-------------CREATE------------
            case 1:
                return new CreatePersonCommand(boardRepository);
            case 2:
                //
            case 3:
                return new CreateNewBugInBoardCommand(boardRepository);
            case 4:
                return new CreateNewStoryInBoardCommand(boardRepository);
            case 5:
                return new CreateNewFeedbackInBoardCommand(boardRepository);
            case 6:
                return new CreateTeamCommand(boardRepository);
            case 7:
                return new CreateANewBoardInATeamCommand(boardRepository);
            //-------------CHANGE-----------
            case 8:
                return new ChangeStoryPriorityCommand(boardRepository);
            case 9:
                return new ChangeStorySizeCommand(boardRepository);
            case 10:
                return new ChangeStoryStatusCommand(boardRepository);
            case 11:
                return new ChangeRatingOfAFeedbackCommand(boardRepository);
            case 12:
                return new ChangeStatusOfAFeedbackCommand(boardRepository);
            case 13:
                return new ChangePriorityOfABug(boardRepository);
            case 14:
                return new ChangeSeverityOfABug(boardRepository);
            case 15:
                return new ChangeStatusOfABug(boardRepository);
                //------------OTHERS------------
            case 16:
                return new AddCommentToTaskCommand(boardRepository);
            case 17:
                return new AddPersonToATeamCommand(boardRepository);
            case 18:
                return new AssignTaskToAPersonCommand(boardRepository);
            case 19:
                return new UnassignTaskToAPersonCommand(boardRepository);
                //------------SHOW---------------
            case 20:
                return new ShowTeamsActivityCommand(boardRepository);
            case 21:
                return new ShowAllPeopleCommand(boardRepository);
            case 22:
                return new ShowAllTeamsCommand(boardRepository);
            case 23:
                return new ShowPersonActivityCommand(boardRepository);
            case 24:
                return new ShowTeamMembersCommand(boardRepository);
            case 25:
                return new ShowAllTeamBoardsCommand(boardRepository);
            case 26:
                return new ShowBoardSActivityCommand(boardRepository);
            default:

                throw new UnsupportedOperationException(String.format(COMMAND_NOT_SUPPORTED_MESSAGE, commandNumber));
        }
    }
}
