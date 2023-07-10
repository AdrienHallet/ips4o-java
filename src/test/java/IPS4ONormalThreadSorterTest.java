import org.junit.jupiter.api.Test;

import java.util.Arrays;

class IPS4ONormalThreadSorterTest {

    @Test
    void sort_test_successful() {
        int[] array = TestUtils.generateRandomArray(100_000, 0, 10000);
        IPS4ONormalThreadSorter.sort(array);
        SortAssertions.assertSorted(array);
    }
}