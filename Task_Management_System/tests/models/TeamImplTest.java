package models;

import Models.Contracts.Person;
import Models.PersonImpl;
import Models.TeamImpl;
import org.junit.Test;
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
        Person person = new PersonImpl("John Doe"); // Assuming you have a PersonImpl class
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
}
