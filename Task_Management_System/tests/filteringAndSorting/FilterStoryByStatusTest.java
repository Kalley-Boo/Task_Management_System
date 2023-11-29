package filteringAndSorting;

import commands.FilteringAndSorting.FilterStoryByStatus;
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


public class FilterStoryByStatusTest {
    private BoardRepository boardRepository;
    private FilterStoryByStatus filterStoryByStatus;

    @BeforeEach
    public void setUp() {
        this.boardRepository = new BoardRepositoryImpl();
        this.filterStoryByStatus = new FilterStoryByStatus(boardRepository);
    }

    @Test
    public void testFilterStoryByStatus(){
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
        param.add("NOT_DONE");
        String result = filterStoryByStatus.execute(param);
        Assertions.assertEquals("""
                        StoryStory
                        NOT_DONE
                        2StoryKtory
                        NOT_DONE
                        ThirdStory
                        NOT_DONE
                        """
                , result);

    }
}
