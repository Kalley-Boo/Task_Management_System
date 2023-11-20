package models;

import Models.BoardImpl;
import Models.Contracts.Person;
import Models.PersonImpl;
import Models.TeamImpl;
import org.testng.annotations.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class TeamImplTest {
    private TeamImpl team;

    @BeforeEach
    public void setUp() {
        team = new TeamImpl("TestTeam");
    }

    @Test
    public void testGetName() {
        assertEquals("TestTeam", team.getName());
    }

    @Test
    public void testAddMember() {
        Person person = new PersonImpl("John Doe");
        team.addMember(person);
        assertTrue(team.getMembers().contains("John Doe"));
    }

    @Test
    public void testAddNullMember(){
        try{
            team.addMember(null);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e){
            assertEquals("Member cannot be null", e.getMessage());
        }
    }

    @Test
    public void testAddDuplicateMember(){
        Person person = new PersonImpl("John Doe");
        team.addMember(person);

        try{
            team.addMember(person);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("Person with the same name already exists in the team", e.getMessage());
        }
    }

    @Test
    public void testAddBoard() {
        BoardImpl board = new BoardImpl("TestBoard");
        team.addBoard(board);
        assertTrue(team.getBoards().contains(board));
    }

    @Test
    public void testAddNullBoard() {
        try {
            team.addBoard(null);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("Board cannot be null", e.getMessage());
        }
    }

    @Test
    public void testAddDuplicateBoard() {
        BoardImpl board = new BoardImpl("TestBoard");
        team.addBoard(board);

        try {
            team.addBoard(board);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("Board with the same name already exists", e.getMessage());
        }
    }


}
