package Models.Contracts;

import Models.BoardImpl;

public interface Team {
     public String displayHistory();
     String getName();
     public void addMember(Person member);
     public void addBoard(BoardImpl board);
     public String displayMembers();
     public void displayBoards();
}
