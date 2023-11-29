package filteringAndSorting;

import commands.FilteringAndSorting.SortBugsBySeverity;
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


public class SortBugsBySeverityTest {
    private BoardRepository boardRepository;
    private SortBugsBySeverity sortBugBySeverity;

    @BeforeEach
    public void setUp() {
        this.boardRepository = new BoardRepositoryImpl();
        this.sortBugBySeverity = new SortBugsBySeverity(boardRepository);
    }

    @Test
    public void filterBugByStatus() {
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
                Priority.HIGH, Severity.MAJOR, person2);
        boardRepository.createAssignedBug("BugTestThree",
                "Ajshdadjhfabdfk", parameters,
                Priority.HIGH, Severity.CRITICAL, person3);
        String result = sortBugBySeverity.execute(new ArrayList<>());
        Assertions.assertEquals("""
                Bug's title: BugTestThree
                The bug's severity: CRITICAL
                Bug's title: BugTestTwo
                The bug's severity: MAJOR
                Bug's title: BugTestOne
                The bug's severity: MINOR""", result.trim());
    }
}
