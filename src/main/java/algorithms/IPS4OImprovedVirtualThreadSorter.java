package algorithms;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class IPS4OImprovedVirtualThreadSorter {

    private static final int THRESHOLD = 1000; // Adjust this value based on your input size

    public static void sort(int[] array) {
        ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor();

        try {
            sort(array, 0, array.length - 1, executorService)
                    .join(); // Wait for the completion of the sorting tasks
        } finally {
            executorService.shutdown();
            try {
                executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private static CompletableFuture<Void> sort(int[] array, int left, int right, ExecutorService executorService) {
        if (right - left <= THRESHOLD) {
            Arrays.sort(array, left, right + 1); // Sequential sorting for small sub-arrays
            return CompletableFuture.completedFuture(null);
        } else {
            int pivot = partition(array, left, right);

            CompletableFuture<Void> leftTask = CompletableFuture.supplyAsync(() ->
                            sort(array, left, pivot - 1, executorService), executorService)
                    .thenCompose(result -> CompletableFuture.completedFuture(null));
            CompletableFuture<Void> rightTask = CompletableFuture.supplyAsync(() ->
                            sort(array, pivot + 1, right, executorService), executorService)
                    .thenCompose(result -> CompletableFuture.completedFuture(null));

            return CompletableFuture.allOf(leftTask, rightTask);
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
