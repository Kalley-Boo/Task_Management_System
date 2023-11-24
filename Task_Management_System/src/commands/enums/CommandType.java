package commands.enums;

public enum CommandType {

    CREATEPERSON("Create Person"),
    CREATENEWBUGINBOARD("Create a new Bug in board"),
    CREATENEWSTORYINBOARD("Create a new Story in board"),
    CREATENEWFEEDBACKINBOARD("Create a new Feedback in board"),
    CREATETEAM("Create a team"),
    CREATEANEWBOARDINATEAM("Create a new Board in a team"),

    CHANGESTORYPRIORITY("Change Story Priority"),
    CHANGESTORYSIZE("Change Story Size"),
    CHANGESTORYSTATUS("Change Story Status"),
    CHANGERATINGOFAFEEDBACK("Change Rating of a Feedback"),
    CHANGESTATUSOFAFEEDBACK("Change Status of a Feedback"),
    CHANGEPRIORITYOFABUG("Change Priority of a Bug"),
    CHANGESEVERITYOFABUG("Change Severity of a Bug"),
    CHANGESTATUSOFABUG("Change Status of a Bug"),

    ADDCOMMENTTOTASK("Add Comment to a Task"),
    ADDPERSONTOATEAM("Add Person to a Team"),
    ASSIGNTASKTOAPERSON("Assign Task to a Person"),
    UNASSIGNTASKTOAPERSON("Unassign Task from a Person"),

    SHOTEAMSACTIVITYCOMMAND("Show Team's Activity Command"),
    SHOWALLPEOPLE("Show All People"),
    SHOWALLTEAMS("Show All Teams"),
    SHOWPERSONCACTIVITY("Show Person's Activity"),
    SHOWTEAMMEMBERS("Show Team's Members"),
    SHOWALLTEAMBOARDS("Show Team's Boards"),
    SHOWBOARDSACTIVITY("Show Board's Activity"),
    SHOWTASKSACTIVITY("Show a Task's activity log"),
    FILTERTASKS("Filter tasks by title"),
    SORTTASKS("Sort tasks by title"),
    SHOWALLTASKS("Show all tasks"),
    //-
    FILTERBUGBYASSIGNEE("Filter bug by assignee"),
    FILTERBUGBYSTATUS("Filter bug by status"),
    FILTERFEEDBACKBYSTATUS("Filter feedback by status"),
    FILTERSTORYBYASSIGNEE("Filter story by assignee"),
    FILTERSTORYBYSTATUS("Filter story by status"),
    LISTBUGSWITHASSIGNEE("List bugs with assignee"),
    LISTSTORIESWITHASSIGNEE("List stories with assignee"),
    SORTBUGSBYPRIORITY("Sort bugs by priority"),
    SORTBUGSBYSEVERITY("Sort bugs by severity"),
    SORTBUGSBYTITLE("Sort bugs by title"),
    SORTFEEDBACKBYTITLE("Sort feedback by title"),
    SORTSTORYBYPRIORITY("Sort story by priority"),
    SORTSTORYBYSIZE("Sort story by size"),
    SORTSTORYBYTITLE("Sort story by title")

    ;

    private final String command;

    CommandType(String s) {
        this.command = s;
    }

    public String getCommand() {
        return command;
    }
}
