package models;

import models.contracts.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;


public class TeamImplTest {
    private TeamImpl team;

    @BeforeEach
    public void setUp() {
        team = new TeamImpl("TestTeam");
    }

    @Test
    public void testGetName() {
        Assertions.assertEquals("TestTeam", team.getName());
    }

    @Test
    public void testAddMember() {
        Person person = new PersonImpl("JohnDoe");
        team.addMember(person);
        Assertions.assertTrue(team.displayMembers().contains("JohnDoe"));
    }

    @Test
    public void testAddNullMember() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> team.addMember(null));
    }

    @Test
    public void testAddDuplicateMember() {
        Person person = new PersonImpl("John Doe");
        team.addMember(person);
        Assertions.assertThrows(IllegalArgumentException.class, () -> team.addMember(person));
    }

    @Test
    public void testAddBoard() {
        BoardImpl board = new BoardImpl("TestBoard");
        team.addBoard(board);
        Assertions.assertTrue(team.getBoards().contains(board));
    }

    @Test
    public void testAddNullBoard() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> team.addBoard(null));
    }

    @Test
    public void testAddDuplicateBoard() {
        BoardImpl board = new BoardImpl("TestBoard");
        team.addBoard(board);
        Assertions.assertThrows(IllegalArgumentException.class, () -> team.addBoard(board));
    }
}
