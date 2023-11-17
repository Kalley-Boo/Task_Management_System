package Models;

import Models.Contracts.Board;
import Models.Contracts.Person;
import Models.Contracts.Printable;
import Models.Contracts.Team;

import java.util.ArrayList;
import java.util.List;

public class TeamImpl  implements Team, Printable {
    public static final String MEMBER_CANT_BE_NULL = "Member cannot be null";
    public static final String PERSON_EXISTS = "Person with the same name already exists in the team";
    public static final String BOARD_CANT_BE_NUL = "Board cannot be null";
    public static final String BOARD_EXISTS = "Board with the same name already exists";
    public static final String EMPTY_TEAM = "No members in the team ";
    public static final String NO_BOARDS_IN_TEAM = "No boards in team ";
    public static final String TEAM_HEADER = "---Team---";
    public static final String NO_MEMBERS_ERROR = "There are no members on this team.";
    public static final String MEMBERS_HEADER = "---Members---";
    public static final String BOARDS_HEADER = "---Boards---";
    public static final String NO_BOARDS_ERROR = "There are no boards in this team.";
    public static final String ADDED_TO_THIS_TEAM = "%s has been successfully added to this team!";
    public static final String SET_TO = "Name set to: %s";

    private String name;
    private List<Person> members;
    private List<Board> boards;
    private final List<HistoryLogImpl> history;

    public TeamImpl(String name){
        setName(name);
        this.history = new ArrayList<>();
        this.members = new ArrayList<>();
        this.boards = new ArrayList<>();
    }//constructor

    //----------------------------------------methods--------------------------------------------

    public String displayHistory() {
        StringBuilder stringBuilder = new StringBuilder();
        for (HistoryLogImpl log : history) {
            stringBuilder.append(log.viewInfo());
        }
        return new String(stringBuilder);
    }

    protected void logEvent(String event) {
        history.add(new HistoryLogImpl(event));
    }

    public String displayMembers() {
        StringBuilder stringBuilder = new StringBuilder();
        if (members.isEmpty()) {
            return new String(stringBuilder.append(EMPTY_TEAM).append(name));
        } else {
            stringBuilder.append("---Members---");
            for (Person member : members) {
                stringBuilder.append(member).append(" ");
            }
        }
        return new String(stringBuilder);
    }

    public void displayBoards() {
        if (boards.isEmpty()) {
            System.out.println(NO_BOARDS_IN_TEAM + name);
        } else {
            System.out.println("Boards in team " + name + ":");
            for (Board board : boards) {
                System.out.println(board.getName());
            }
        }
    }

    public void addMember(Person member) {
        if(member == null) {
            throw new IllegalArgumentException(MEMBER_CANT_BE_NULL);
        }
        for(Person person : this.members){
            if(member.getName().equals(person.getName())){
                throw new IllegalArgumentException(PERSON_EXISTS);
            }
        }
        logEvent(String.format(ADDED_TO_THIS_TEAM, member.getName()));
        members.add(member);
    }

    public void addBoard(Board board){
        if(board == null){
            throw new IllegalArgumentException(BOARD_CANT_BE_NUL);
        }
        if(boards.contains(board)){
            throw new IllegalArgumentException(BOARD_EXISTS);
        }
        logEvent(String.format("Board %s added to this team", board.getName()));
        boards.add(board);
    }

    //-----------------------------------setters and getters----------------------------------------

    private void setName(String name){
        logEvent(String.format(SET_TO, name));
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public List<Person> getMembers() {
        return members;
    }
    @Override
    public List<Board> getBoards() {
        return boards;
    }

    @Override
    public String print() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(TEAM_HEADER).append("\n");
        stringBuilder.append(this.name).append("\n");
        if(this.members.isEmpty()){
            return new String(stringBuilder.append(NO_MEMBERS_ERROR));
        }
        stringBuilder.append(MEMBERS_HEADER).append("\n");
        for(Person member : this.members){
            stringBuilder.append(member.print()).append(" ");
        }
        stringBuilder.append("\n");
        if(this.boards.isEmpty()){
            return new String(stringBuilder.append(NO_BOARDS_ERROR));
        }
        stringBuilder.append(BOARDS_HEADER).append("\n");
        for(Board board : this.boards){
            stringBuilder.append(board.print());
            stringBuilder.append("\n");
        }
        return new String(stringBuilder);
    }
}

