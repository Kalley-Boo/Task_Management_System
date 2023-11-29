package filteringAndSorting;

import commands.FilteringAndSorting.SortBugsByPriority;
import core.BoardRepositoryImpl;
import core.contracts.BoardRepository;
import models.PersonImpl;
import models.contracts.Person;
import models.enums.Priority;
import models.enums.Severity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


public class SortBugsByPriorityTest {
    private BoardRepository boardRepository;
    private SortBugsByPriority sortBugsByPriority;

    @BeforeEach
    public void setUp() {
        this.boardRepository = new BoardRepositoryImpl();
        this.sortBugsByPriority = new SortBugsByPriority(boardRepository);
    }

    @Test
    public void testSortBugsByPriority() {
        List<String> parameters = new ArrayList<>();
        parameters.add("Hello");
        Person person1 = new PersonImpl("IvanOne");
        Person person2 = new PersonImpl("IvanIvan");
        Person person3 = new PersonImpl("IvanOne");

        boardRepository.createAssignedBug("BugTestOne",
                "Ajshdadjhfabdfk", parameters,
                Priority.HIGH, Severity.MINOR, person1);
        boardRepository.createAssignedBug("BugTestTwo",
                "Ajshdadjhfabdfk", parameters,
                Priority.LOW, Severity.MINOR, person2);
        boardRepository.createAssignedBug("BugTestThree",
                "Ajshdadjhfabdfk", parameters,
                Priority.MEDIUM, Severity.MINOR, person3);
        String result = sortBugsByPriority.execute(new ArrayList<>());
        Assertions.assertEquals("""
                Bug's title: BugTestOne
                The bug's priority: HIGH
                Bug's title: BugTestThree
                The bug's priority: MEDIUM
                Bug's title: BugTestTwo
                The bug's priority: LOW""", result.trim());
    }

}
