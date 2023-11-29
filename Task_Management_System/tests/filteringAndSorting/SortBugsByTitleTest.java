package filteringAndSorting;

import commands.FilteringAndSorting.SortBugsByTitle;
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


public class SortBugsByTitleTest {
    private BoardRepository boardRepository;
    private SortBugsByTitle sortBugsByTitle;
    @BeforeEach
    public void setUp() {
        this.boardRepository = new BoardRepositoryImpl();
        this.sortBugsByTitle = new SortBugsByTitle(boardRepository);
    }

    @Test
    public void sortBugsBtTitletest(){
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
                Priority.LOW, Severity.MINOR, person2);
        boardRepository.createAssignedBug("BugTestThree",
                "Ajshdadjhfabdfk", parameters,
                Priority.MEDIUM, Severity.MINOR, person3);
        String result = sortBugsByTitle.execute(new ArrayList<>());
        Assertions.assertEquals("""
                Bug's title: BugTestOne
                Bug's title: BugTestThree
                Bug's title: BugTestTwo""", result.trim());
    }
}
