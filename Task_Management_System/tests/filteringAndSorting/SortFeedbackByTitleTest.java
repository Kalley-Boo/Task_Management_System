package filteringAndSorting;

import commands.FilteringAndSorting.SortBugsByTitle;
import commands.FilteringAndSorting.SortFeedbackByTitle;
import core.BoardRepositoryImpl;
import core.contracts.BoardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SortFeedbackByTitleTest {
    private BoardRepository boardRepository;
    private SortFeedbackByTitle sortFeedbackByTitle;

    @BeforeEach
    public void setUp() {
        this.boardRepository = new BoardRepositoryImpl();
        this.sortFeedbackByTitle = new SortFeedbackByTitle(boardRepository);
    }

    @Test
    public void testSortFeedbackByTitle(){
        boardRepository.createFeedback("Feedback1000", "jahdfakfhak", 2);
        boardRepository.createFeedback("Feedback2000", "jahdfakfhak", 2);
        boardRepository.createFeedback("Feedback3000", "jahdfakfhak", 2);

        ArrayList param = new ArrayList<>();
        String result = sortFeedbackByTitle.execute(param);
        assertEquals("Feedback's title: Feedback1000\n" +
                        "Feedback's title: Feedback2000\n" +
                        "Feedback's title: Feedback3000\n",
                result);
    }
}
