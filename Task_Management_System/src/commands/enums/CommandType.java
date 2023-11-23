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
    ;

    private final String command;

    CommandType(String s) {
        this.command = s;
    }

    public String getCommand() {
        return command;
    }
}
