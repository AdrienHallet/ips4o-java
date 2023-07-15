import algorithms.IPS4ONormalThreadSorter;
import benchmark.Utils;
import org.junit.jupiter.api.Test;

class IPS4ONormalThreadSorterTest {

    @Test
    void sort_test_successful() {
        int[] array = Utils.generateRandomArray(100_000, 0, 10000);
        IPS4ONormalThreadSorter.sort(array);
        SortAssertions.assertSorted(array);
    }
}