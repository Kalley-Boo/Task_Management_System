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
    public static final String BOARDS_HEAEDR = "---Boards---";
    public static final String NO_BOARDS_ERROR = "There are no boards in this team.";

    private String name;
    private List<Person> members;
    private List<BoardImpl> boards;

    public TeamImpl(String name){
        setName(name);
        this.members = new ArrayList<>();
        this.boards = new ArrayList<>();
    }//constructor

    //----------------------------------------methods--------------------------------------------

    public void displayMembers() {
        if (members.isEmpty()) {
            System.out.println(EMPTY_TEAM + name);
        } else {
            System.out.println("Members of team " + name + ":");
            for (Person member : members) {
                System.out.println(member + " ");
            }
        }
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
        if(members.contains(member.getName())) {
            throw new IllegalArgumentException(PERSON_EXISTS);
        }
        members.add(member);
    }

    public void addBoard(BoardImpl board){
        if(board == null){
            throw new IllegalArgumentException(BOARD_CANT_BE_NUL);
        }
        if(boards.contains(board)){
            throw new IllegalArgumentException(BOARD_EXISTS);
        }
        boards.add(board);
    }

    //-----------------------------------setters and getters----------------------------------------

    private void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public List<Person> getMembers() {
        return members;
    }

    public List<BoardImpl> getBoards() {
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
        stringBuilder.append(BOARDS_HEAEDR).append("\n");
        for(Board board : this.boards){
            stringBuilder.append(board.print());
            stringBuilder.append("\n");
        }
        return new String(stringBuilder);
    }
}

