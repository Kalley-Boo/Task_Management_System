package models;

import Models.PersonImpl;
import Models.TaskImpl;
import org.junit.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PersonImplTest {
    public String validName = "JohnDoe";
    public String validTask = "Task 1";
    public String validDescr = "Description 1" ;

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




}
