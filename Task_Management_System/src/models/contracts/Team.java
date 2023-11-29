package models.contracts;

import java.util.List;

public interface Team {
    String displayHistory();

    String getName();

    void addMember(Person member);

    void addBoard(Board board);

    String displayMembers();

    void displayBoards();

    List<Board> getBoards();

    List<Person> getMembers();
}
