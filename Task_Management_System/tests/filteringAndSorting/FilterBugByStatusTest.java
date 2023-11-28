package filteringAndSorting;

import commands.FilteringAndSorting.FilterBugByStatus;
import core.BoardRepositoryImpl;
import core.contracts.BoardRepository;
import models.PersonImpl;
import models.contracts.Person;
import models.enums.Priority;
import models.enums.Severity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FilterBugByStatusTest {
    private BoardRepository boardRepository;
    private FilterBugByStatus filterBugByStatus;

    @BeforeEach
    public void setUp(){
        this.boardRepository = new BoardRepositoryImpl();
        this.filterBugByStatus = new FilterBugByStatus(boardRepository);
    }

    @Test
    public void filterBugByStatus() {
        ArrayList<String> parameters = new ArrayList<>();
        parameters.add("Hello");
        Person person1 = new PersonImpl("IvanOne");
        Person person2 = new PersonImpl("IvanIvan");
        Person person3 = new PersonImpl("IvanOne");

        boardRepository.createAssignedBug("BugTestOne",
                "Ajshdadjhfabdfk", parameters,
                Priority.HIGH, Severity.MINOR, person1);
        boardRepository.createAssignedBug("BugTestTwo",
                "Ajshdadjhfabdfk", parameters,
                Priority.HIGH, Severity.MINOR, person2);
        boardRepository.createAssignedBug("BugTestThree",
                "Ajshdadjhfabdfk", parameters,
                Priority.HIGH, Severity.MINOR, person3);

        ArrayList param = new ArrayList<>();
        param.add("Active");
        String result = filterBugByStatus.execute(param);
        assertEquals("BugTestOne\nACTIVE\nBugTestTwo\nACTIVE\nBugTestThree\nACTIVE\n", result);

    }
}
