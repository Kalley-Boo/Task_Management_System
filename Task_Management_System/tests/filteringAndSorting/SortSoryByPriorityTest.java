package filteringAndSorting;

import commands.FilteringAndSorting.SortStoryByPriority;
import core.BoardRepositoryImpl;
import core.contracts.BoardRepository;
import models.PersonImpl;
import models.contracts.Person;
import models.enums.Priority;
import models.enums.TaskSize;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


public class SortSoryByPriorityTest {
    private BoardRepository boardRepository;
    private SortStoryByPriority sortStoryByPriority;

    @BeforeEach
    public void setUp() {
        this.boardRepository = new BoardRepositoryImpl();
        this.sortStoryByPriority = new SortStoryByPriority(boardRepository);
    }

    @Test
    public void testSortStory(){
        Person person1 = new PersonImpl("IvanOne");
        Person person2 = new PersonImpl("IvanIvan");
        Person person3 = new PersonImpl("IvanOne");

        boardRepository.createAssignedStory("StoryStory", "hjssadadgfajf",
                Priority.HIGH, TaskSize.MEDIUM, person1);
        boardRepository.createAssignedStory("2StoryStory", "hjs21erdgfajf",
                Priority.HIGH, TaskSize.MEDIUM, person2);
        boardRepository.createAssignedStory("ThirdStory", "hjsdfewfewgfajf",
                Priority.LOW, TaskSize.MEDIUM, person3);

        List<String> param = new ArrayList<>();
        param.add("High");
        String result = sortStoryByPriority.execute(param);
        Assertions.assertEquals("""
                        Story's title: StoryStory
                        Story's priority: HIGH
                        Story's title: 2StoryStory
                        Story's priority: HIGH
                        Story's title: ThirdStory
                        Story's priority: LOW
                        """
                , result);
    }
}
