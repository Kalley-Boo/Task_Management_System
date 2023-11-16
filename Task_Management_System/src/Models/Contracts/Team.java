package Models.Contracts;

import Models.BoardImpl;

public interface Team {
     String getName();
     public void addMember(Person member);
     public void addBoard(BoardImpl board);
     public void displayMembers();
     public void displayBoards();
}
