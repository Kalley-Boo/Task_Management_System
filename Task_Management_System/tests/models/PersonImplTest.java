package models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PersonImplTest {
    public static final String INVALID_NAME = "Bob";
    public String validName = "JohnDoe";
    public String validTask = "Task 1";
    public String validDescr = "Description 1" ;
    private PersonImpl person;

    @BeforeEach
    public void setUp() {
        person = new PersonImpl("John Doe");
    }

    @Test
    public void testSetName_ValidName_Success() {
        PersonImpl person = new PersonImpl(validName);
        assertEquals(validName, person.getName());
    }

    @Test
    public void testSetName_InvalidName_Exception() {
        assertThrows(IllegalArgumentException.class, () -> new PersonImpl("John"));
        assertThrows(IllegalArgumentException.class, () -> new PersonImpl("LongNameThatExceedsTheMaxLength"));
    }

    @Test
    public void testSetNameInvalid() {
        try {
            PersonImpl person2 = new PersonImpl(INVALID_NAME);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("Please enter a valid name with 5 to 15 symbols", e.getMessage());
        }
    }

    @Test
    public void testAddNullTask() {
        try {
            person.addTask(null);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("Task cannot be null", e.getMessage());
        }
    }


}
