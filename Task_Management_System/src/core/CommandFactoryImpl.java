package core;

import commands.*;
import commands.contracts.Command;
import core.contracts.BoardRepository;
import core.contracts.CommandFactory;
import util.Parser;

public class CommandFactoryImpl implements CommandFactory {
    private static final String COMMAND_NOT_SUPPORTED_MESSAGE = "Command %s is not supported.";

    @Override
    public Command createCommand(String commandTypeValue, BoardRepository boardRepository) {
        CommandType commandType = Parser.tryParseCommandType(commandTypeValue);

        switch (commandType) {
            case CREATETASK:
                //
            case CREATETEAM:
                return new CreateTeamCommand(boardRepository);
            case SHOWTEAMSACTIVITYCOMMAND:
                return new ShowTeamsActivityCommand(boardRepository);
            case CREATEPERSON:
                return new CreatePersonCommand(boardRepository);
            case CREATEBOARD:
                return new CreateBoardCommand(boardRepository);
            case SHOWALLPEOPLE:
                return new ShowAllPeopleCommand(boardRepository);
            case SHOWALLTEAMS:
                return new ShowAllTeamsCommand(boardRepository);
            case SHOWPERSONCACTIVITY:
                return new ShowPersonActivityCommand(boardRepository);
            case ADDCOMMENTTOTASK:
                return new AddCommentToTaskCommand(boardRepository);
            case SHOWTEAMMEMBERS:
                return new ShowTeamMembersCommand(boardRepository);
            case CHANGESTORYPRIORITY:
                return new ChangeStoryPriorityCommand(boardRepository);
            case CHANGESTORYSIZE:
                return new ChangeStorySizeCommand(boardRepository);
            case CREATEANEWBOARDINATEAM:
                return new CreateANewBoardInATeamCommand(boardRepository);
            case SHOWALLTEAMBOARDS:
                return new ShowAllTeamBoardsCommand(boardRepository);
            case SHOWBOARDSACTIVITY:
                return new ShowBoardSActivityCommand(boardRepository);
            case CHANGERATINGOFAFEEDBACK:
                return new ChangeRatingOfAFeedbackCommand(boardRepository);
            case CHANGESTATUSOFAFEEDBACK:
                return new ChangeStatusOfAFeedback(boardRepository);
            case ASSIGNTASKTOAPERSON:
                return new AssignTaskToAPersonCommand(boardRepository);
            case UNASSIGNTASKTOAPERSON:
                return new UnassignTaskToAPersonCommand(boardRepository);
            case CHANGESTORYSTATUS:
                return new ChangeStoryStatusCommand(boardRepository);
            default:

                throw new UnsupportedOperationException(String.format(COMMAND_NOT_SUPPORTED_MESSAGE, commandTypeValue));
        }
    }
}
