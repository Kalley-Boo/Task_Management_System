package models;

import models.enums.Priority;
import models.enums.Severity;
import models.enums.StatusBug;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
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
        Assertions.assertEquals(bug.getPriority(), Priority.MEDIUM);
    }

    @Test
    public void testEditSeverity() {
        BugImpl bug = new BugImpl(2, "Problem with the system", "Problems with logging", new ArrayList<String>(), Priority.LOW, Severity.MINOR, new PersonImpl("Ivan Petkov"));
        bug.editSeverity(Severity.MAJOR);
        Assertions.assertEquals(bug.getSeverity(), Severity.MAJOR);
    }

    @Test
    public void testEditStatus() {
        BugImpl bug = new BugImpl(2, "Problem with the system", "Problems with logging", new ArrayList<String>(), Priority.LOW, Severity.MINOR, new PersonImpl("Ivan Petkov"));
        bug.editStatus(StatusBug.DONE);
        Assertions.assertEquals(bug.getTaskStatus(), StatusBug.DONE);
    }

    @Test
    public void EditStepsToReproduce_ShouldEditTheStepsToReproduce() {
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
//       Assertions.assertEquals(bug.getAssignee(), person.getTitle());
//    }

//    @Test
//    public void testEditAssignee() {
//        BugImpl bug = new BugImpl(2, "Problem with the system", "Problems with logging", new ArrayList<String>(), Priority.LOW, Severity.MINOR, new PersonImpl("Ivan Petkov"));
//        bug.editAssignee(new PersonImpl("Dimitar Dimitrov"));
//       Assertions.assertEquals(bug.getAssignee(), "Dimitar Dimitrov");
//    }


}
