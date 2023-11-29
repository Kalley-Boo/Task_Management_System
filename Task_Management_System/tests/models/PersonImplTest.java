package models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class PersonImplTest {
    public static final String INVALID_NAME = "Bob";
    public String validName = "JohnDoe";
    private PersonImpl person;

    @BeforeEach
    public void setUp() {
        person = new PersonImpl("John Doe");
    }

    @Test
    public void testSetName_ValidName_Success() {
        PersonImpl person = new PersonImpl(validName);
        Assertions.assertEquals(validName, person.getName());
    }

    @Test
    public void testSetName_InvalidName_Exception() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new PersonImpl("John"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new PersonImpl("LongNameThatExceedsTheMaxLength"));
    }

    @Test
    public void testSetNameInvalid() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new PersonImpl(INVALID_NAME));
    }

    @Test
    public void testAddNullTask() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> person.addTask(null));
    }

}