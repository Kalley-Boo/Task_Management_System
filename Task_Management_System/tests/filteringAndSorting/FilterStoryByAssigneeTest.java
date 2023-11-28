package filteringAndSorting;

import commands.FilteringAndSorting.FilterStoryByAssignee;
import core.BoardRepositoryImpl;
import core.contracts.BoardRepository;
import models.PersonImpl;
import models.contracts.Person;
import models.enums.Priority;
import models.enums.TaskSize;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FilterStoryByAssigneeTest {
    private BoardRepository boardRepository;
    private FilterStoryByAssignee filterStoryByAssignee;

    @BeforeEach
    public void setUp() {
        this.boardRepository = new BoardRepositoryImpl();
        this.filterStoryByAssignee = new FilterStoryByAssignee(boardRepository);
    }

    @Test
    public void testFilterStoryByAssignee(){
        Person person1 = new PersonImpl("IvanOne");
        Person person2 = new PersonImpl("IvanIvan");
        Person person3 = new PersonImpl("IvanOne");

        boardRepository.createAssignedStory("StoryStory", "hjssadadgfajf",
                Priority.HIGH, TaskSize.MEDIUM, person1);
        boardRepository.createAssignedStory("2StoryKtory", "hjs21erdgfajf",
                Priority.HIGH, TaskSize.MEDIUM, person2);
        boardRepository.createAssignedStory("ThirdStory", "hjsdfewfewgfajf",
                Priority.HIGH, TaskSize.MEDIUM, person3);

        ArrayList param = new ArrayList<>();
        param.add("IvanOne");
        String result = filterStoryByAssignee.execute(param);
        assertEquals("Story's name: StoryStory\n" +
                        "Story's name: ThirdStory\n"
                , result);

    }
}
