package filteringAndSorting;

import commands.FilteringAndSorting.SortStoryBySize;
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


public class SortStoryBySizeTest {
    private BoardRepository boardRepository;
    private SortStoryBySize sortStoryBySize;

    @BeforeEach
    public void setUp() {
        this.boardRepository = new BoardRepositoryImpl();
        this.sortStoryBySize = new SortStoryBySize(boardRepository);
    }

    @Test
    public void testSortStoryBySize(){
        Person person1 = new PersonImpl("IvanOne");
        Person person2 = new PersonImpl("IvanIvan");
        Person person3 = new PersonImpl("IvanOne");

        boardRepository.createAssignedStory("StoryStory", "hjssadadgfajf",
                Priority.HIGH, TaskSize.MEDIUM, person1);
        boardRepository.createAssignedStory("2StoryStory", "hjs21erdgfajf",
                Priority.HIGH, TaskSize.SMALL, person2);
        boardRepository.createAssignedStory("ThirdStory", "hjsdfewfewgfajf",
                Priority.HIGH, TaskSize.LARGE, person3);

        List<String> param = new ArrayList<>();
        String result = sortStoryBySize.execute(param);
        Assertions.assertEquals("""
                        Story's title: ThirdStory
                        Size: LARGE
                        Story's title: StoryStory
                        Size: MEDIUM
                        Story's title: 2StoryStory
                        Size: SMALL
                        """
                , result);
    }
}
