import org.junit.jupiter.api.Assertions;

import java.util.Arrays;

public class SortAssertions {

    public static void assertSorted(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                Assertions.fail("The array is not sorted.");
            }
        }
    }
}
