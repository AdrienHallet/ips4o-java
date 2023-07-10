import java.util.Arrays;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class IPS4OImprovedVirtualThreadSorter {

    private static final int THRESHOLD = 1000; // Adjust this value based on your input size

    public static void sort(int[] array) {
        ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor();
        sort(array, 0, array.length - 1, executorService);
    }

    private static void sort(int[] array, int left, int right, ExecutorService executorService) {
        if (right - left <= THRESHOLD) {
            Arrays.sort(array, left, right + 1); // Sequential sorting for small sub-arrays
        } else {
            int pivot = partition(array, left, right);

            try (executorService) {
                Future<?> submit = executorService.submit(() -> sort(array, left, pivot - 1, executorService));
                Future<?> submit1 = executorService.submit(() -> sort(array, pivot + 1, right, executorService));

                submit1.get();
                submit.get();
            } catch (ExecutionException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static int partition(int[] array, int left, int right) {
        int pivot = array[right];
        int i = left - 1;

        for (int j = left; j < right; j++) {
            if (array[j] <= pivot) {
                i++;
                swap(array, i, j);
            }
        }

        swap(array, i + 1, right);
        return i + 1;
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
