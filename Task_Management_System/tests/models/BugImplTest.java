package models;
import models.enums.Priority;
import models.enums.Severity;
import models.enums.StatusBug;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;


public class BugImplTest {
    @Test
    public void constructor_Should_ThrowException_When_TitleIsOutOfRange() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new BugImpl(
                        2, "Problem", "Problem when logging", new ArrayList<String>(), Priority.LOW, Severity.MINOR, new PersonImpl("Ivan")));
    }
    @Test
    public void constructor_Should_ThrowException_When_DescriptionIsOutOfRange() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new BugImpl(
                        2, "Problem", "logging", new ArrayList<String>(), Priority.LOW, Severity.MINOR, new PersonImpl("Ivan")));
    }
    @Test
    public void testEditPriority() {
        BugImpl bug = new BugImpl(2, "Problem with the system", "Problems with logging", new ArrayList<String>(), Priority.LOW, Severity.MINOR, new PersonImpl("Ivan Petkov"));
        bug.editPriority(Priority.MEDIUM);
        assertEquals(bug.getPriority(), Priority.MEDIUM);
    }
    @Test
    public void testEditSeverity() {
        BugImpl bug = new BugImpl(2, "Problem with the system", "Problems with logging", new ArrayList<String>(), Priority.LOW, Severity.MINOR, new PersonImpl("Ivan Petkov"));
        bug.editSeverity(Severity.MAJOR);
        assertEquals(bug.getSeverity(), Severity.MAJOR);
    }
    @Test
    public void testEditStatus() {
        BugImpl bug = new BugImpl(2, "Problem with the system", "Problems with logging", new ArrayList<String>(), Priority.LOW, Severity.MINOR, new PersonImpl("Ivan Petkov"));
        bug.editStatus(StatusBug.DONE);
        assertEquals(bug.getTaskStatus(), StatusBug.DONE);
    }

    @Test
    public void EditStepsToReproduce_ShouldEditTheStepsToReproduce(){
        BugImpl bug = new BugImpl(2, "Problem with the system", "Problems with logging", new ArrayList<String>(), Priority.LOW, Severity.MINOR, new PersonImpl("Ivan Petkov"));
        List<String> newSteps = new ArrayList<>();
        newSteps.add("test");
        bug.editStepsToReproduce(newSteps);
        Assertions.assertEquals(newSteps, bug.getStepsToReproduce());
    }


//    @Test
//    public void testEditAssignee(){
//        BugImpl bug = new BugImpl(2, "Problem with the system", "Problems with logging", new ArrayList<String>(), Priority.LOW, Severity.MINOR, new PersonImpl("Ivan Petkov"));
//        PersonImpl person = new PersonImpl("Petar Ivanov");
//        bug.editAssignee(person);
//        assertEquals(bug.getAssignee(), person.getTitle());
//    }

//    @Test
//    public void testEditAssignee() {
//        BugImpl bug = new BugImpl(2, "Problem with the system", "Problems with logging", new ArrayList<String>(), Priority.LOW, Severity.MINOR, new PersonImpl("Ivan Petkov"));
//        bug.editAssignee(new PersonImpl("Dimitar Dimitrov"));
//        assertEquals(bug.getAssignee(), "Dimitar Dimitrov");
//    }


}
