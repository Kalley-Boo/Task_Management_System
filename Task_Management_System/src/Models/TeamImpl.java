package Models;

import Models.Contracts.Person;
import Models.Contracts.Team;

import java.util.ArrayList;
import java.util.List;

public class TeamImpl  implements Team {
    public static final String MEMBER_CANT_BE_NULL = "Member cannot be null";
    public static final String PERSON_EXISTS = "Person with the same name already exists in the team";
    public static final String BOARD_CANT_BE_NUL = "Board cannot be null";
    public static final String BOARD_EXISTS = "Board with the same name already exists";

    private String name;
    private List<String> members;
    private List<Board> boards;

    public TeamImpl(String name){
        setName(name);
        this.members = new ArrayList<>();
        this.boards = new ArrayList<>();
    }//constructor

    //----------------------------------------methods--------------------------------------------

    public void displayMembers() {
        if (members.isEmpty()) {
            System.out.println("No members in the team " + name);
        } else {
            System.out.println("Members of team " + name + ":");
            for (String member : members) {
                System.out.println(member + " ");
            }
        }
    }

    public void displayBoards() {
        if (boards.isEmpty()) {
            System.out.println("No boards in team " + name);
        } else {
            System.out.println("Boards in team " + name + ":");
//            Comment out when board.getName is made
//            for (Board board : boards) {
//                System.out.println(board.getName());
//            }
        }
    }

    public void addMember(Person member) {
        if(member == null) {
            throw new IllegalArgumentException(MEMBER_CANT_BE_NULL);
        }
        if(members.contains(member.getName())) { //Not sure if we will keep it, since maybe we allow many people with the same name
            throw new IllegalArgumentException(PERSON_EXISTS);
        }
        members.add(member.getName());
    }

    public void addBoard(Board board){
        if(board == null){
            throw new IllegalArgumentException(BOARD_CANT_BE_NUL);
        }
//        Uncomment line underneath when board.getName is made
//        if(boards.contains(board.getName())){
//            throw new IllegalArgumentException(BOARD_EXISTS);
//        }
    }

    //-----------------------------------setters and getters----------------------------------------

    private void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}

