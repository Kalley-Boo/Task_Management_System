package filteringAndSorting;

import commands.FilteringAndSorting.FilterBugByAssignee;
import core.BoardRepositoryImpl;
import core.contracts.BoardRepository;
import models.BugImpl;
import models.PersonImpl;
import models.contracts.Bug;
import models.contracts.Person;
import models.enums.Priority;
import models.enums.Severity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FilterBugByAssigneeTest {
    private BoardRepository boardRepository;
    private FilterBugByAssignee filterBugByAssignee;
    @BeforeEach
    public void setUp(){
        this.boardRepository = new BoardRepositoryImpl();
        this.filterBugByAssignee = new FilterBugByAssignee(boardRepository);
    }

    @Test
    public void testFilterBuysByAssignee(){
        ArrayList<String> parameters = new ArrayList<>();
        parameters.add("Hello");
        Person person1 = new PersonImpl("IvanOne");
        Person person2 = new PersonImpl("IvanIvan");
        Person person3 = new PersonImpl("IvanOne");

        boardRepository.createAssignedBug( "BugTestOne",
                "Ajshdadjhfabdfk", parameters,
                Priority.HIGH, Severity.MINOR, person1);
        boardRepository.createAssignedBug("BugTestTwo",
                "Ajshdadjhfabdfk", parameters,
                Priority.HIGH, Severity.MINOR, person2);
        boardRepository.createAssignedBug("BugTestThree",
                "Ajshdadjhfabdfk", parameters,
                Priority.HIGH, Severity.MINOR, person3);

        ArrayList list = new ArrayList<>();
        list.add("IvanOne");
        String result = filterBugByAssignee.execute(list);
        assertEquals("BugTestOne\nBugTestThree", result);
    }
}
