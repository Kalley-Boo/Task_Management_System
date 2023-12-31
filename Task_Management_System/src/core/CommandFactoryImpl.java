package core;

import commands.FilteringAndSorting.*;
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
            case 2 -> new CreateNewBugInBoardCommand(boardRepository);
            case 3 -> new CreateNewStoryInBoardCommand(boardRepository);
            case 4 -> new CreateNewFeedbackInBoardCommand(boardRepository);
            case 5 -> new CreateTeamCommand(boardRepository);
            case 6 -> new CreateANewBoardInATeamCommand(boardRepository);
            //-------------CHANGE-----------
            case 7 -> new ChangeStoryPriorityCommand(boardRepository);
            case 8 -> new ChangeStorySizeCommand(boardRepository);
            case 9 -> new ChangeStoryStatusCommand(boardRepository);
            case 10 -> new ChangeRatingOfAFeedbackCommand(boardRepository);
            case 11 -> new ChangeStatusOfAFeedbackCommand(boardRepository);
            case 12 -> new ChangePriorityOfABug(boardRepository);
            case 13 -> new ChangeSeverityOfABug(boardRepository);
            case 14 -> new ChangeStatusOfABug(boardRepository);
            //------------OTHERS------------
            case 15 -> new AddCommentToTaskCommand(boardRepository);
            case 16 -> new AddPersonToATeamCommand(boardRepository);
            case 17 -> new AssignTaskToAPersonCommand(boardRepository);
            case 18 -> new UnassignTaskToAPersonCommand(boardRepository);
            //------------SHOW---------------
            case 19 -> new ShowTeamsActivityCommand(boardRepository);
            case 20 -> new ShowAllPeopleCommand(boardRepository);
            case 21 -> new ShowAllTeamsCommand(boardRepository);
            case 22 -> new ShowPersonActivityCommand(boardRepository);
            case 23 -> new ShowTeamMembersCommand(boardRepository);
            case 24 -> new ShowAllTeamBoardsCommand(boardRepository);
            case 25 -> new ShowBoardSActivityCommand(boardRepository);
            case 26 -> new ShowTaskActivityCommand(boardRepository);
            case 27 -> new FilterTasksByTitle(boardRepository);
            case 28 -> new SortTasksByTitle(boardRepository);
            case 29 -> new ShowAllTasksCommand(boardRepository);
            //------------SORTING--------------
            case 30 -> new FilterBugByAssignee(boardRepository);
            case 31 -> new FilterBugByStatus(boardRepository);
            case 32 -> new FilterFeedbackByStatus(boardRepository);
            case 33 -> new FilterStoryByAssignee(boardRepository);
            case 34 -> new FilterStoryByStatus(boardRepository);
            case 35 -> new ListBugsWithAssignee(boardRepository);
            case 36 -> new ListStoriesWithAssignee(boardRepository);
            case 37 -> new SortBugsByPriority(boardRepository);
            case 38 -> new SortBugsBySeverity(boardRepository);
            case 39 -> new SortBugsByTitle(boardRepository);
            case 40 -> new SortFeedbackByTitle(boardRepository);
            case 41 -> new SortStoryByPriority(boardRepository);
            case 42 -> new SortStoryBySize(boardRepository);
            case 43 -> new SortStoryByTitle(boardRepository);
            default ->
                    throw new UnsupportedOperationException(String.format(COMMAND_NOT_SUPPORTED_MESSAGE, commandNumber));
        };
    }
}
