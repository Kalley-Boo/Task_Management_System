package Models.Contracts;

import Models.Board;

public interface Team {
     String getName();
     public void addMember(Person member);
     public void addBoard(Board board);
     public void displayMembers();
     public void displayBoards();
}
