package filteringAndSorting;

import commands.FilteringAndSorting.SortStoryByTitle;
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


public class SortStoryByTitleTest {
    private BoardRepository boardRepository;
    private SortStoryByTitle sortStoryByTitle;

    @BeforeEach
    public void setUp() {
        this.boardRepository = new BoardRepositoryImpl();
        this.sortStoryByTitle = new SortStoryByTitle(boardRepository);
    }

    @Test
    public void testSortStoryByTitle(){
        Person person1 = new PersonImpl("IvanOne");
        Person person2 = new PersonImpl("IvanIvan");
        Person person3 = new PersonImpl("IvanOne");

        boardRepository.createAssignedStory("StoryStory", "hjssadadgfajf",
                Priority.HIGH, TaskSize.MEDIUM, person1);
        boardRepository.createAssignedStory("2StoryKtory", "hjs21erdgfajf",
                Priority.HIGH, TaskSize.MEDIUM, person2);
        boardRepository.createAssignedStory("ThirdStory", "hjsdfewfewgfajf",
                Priority.HIGH, TaskSize.MEDIUM, person3);

        List<String> param = new ArrayList<>();
        String result = sortStoryByTitle.execute(param);
        Assertions.assertEquals("""
                        Story's title: 2StoryStory
                        Story's title: StoryStory
                        Story's title: ThirdStory
                        """
                , result);
    }
}
