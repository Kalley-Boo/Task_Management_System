package Models.Contracts;

import Models.BoardImpl;

import java.util.List;

public interface Team {
     public String displayHistory();
     String getName();
     public void addMember(Person member);
     public void addBoard(Board board);
     public String displayMembers();
     public void displayBoards();
     public List<Board> getBoards();
}
