import org.example.GnomeSort;
import org.example.ListUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.Assert.assertTrue;
import java.util.List;

public class SortTest {
    @ParameterizedTest
    @ValueSource(ints = {10000, 20000, 50000, 100000})
    @DisplayName("Test sorting with array size: ")
    public void testSorting(int listSize){
        int minElement = 1;
        int maxElement = listSize;
        String fileName = "List_" + listSize;
        String fileName2 = "List_SequentialSort_" + listSize;
        String fileName3 = "List_ParallelSort_" + listSize;
        List<Integer> list1 = ListUtil.generateList(listSize, minElement, maxElement);
        ListUtil.writeToFile(list1, fileName);
        List<Integer> list2 = ListUtil.readFromFile(fileName);

       // Sequential Sorting
        GnomeSort.sequentialGnomeSort(list1, 0, list1.size());
        assertTrue(ListUtil.isSorted(list1));
        ListUtil.writeToFile(list1, fileName2);

       // ParallelSort
        int threadNum = Runtime.getRuntime().availableProcessors();
        list2 = GnomeSort.parallelGnomeSort(list2, minElement, maxElement, threadNum, 3);
        assertTrue(ListUtil.isSorted(list2));
        ListUtil.writeToFile(list2, fileName3);

       // Check if lists are equal
        assertTrue(ListUtil.listEqual(list1, list2));
    }
}
