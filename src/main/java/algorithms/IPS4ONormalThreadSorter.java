package algorithms;

import java.util.Arrays;

public class IPS4ONormalThreadSorter {

    private static final int THRESHOLD = 1000; // Adjust this value based on your input size

    public static void sort(int[] array) {
        sort(array, 0, array.length - 1);
    }

    private static void sort(int[] array, int left, int right) {
        if (right - left <= THRESHOLD) {
            Arrays.sort(array, left, right + 1); // Sequential sorting for small sub-arrays
        } else {
            int pivot = partition(array, left, right);
            Thread leftThread = new Thread(() -> sort(array, left, pivot - 1));
            Thread rightThread = new Thread(() -> sort(array, pivot + 1, right));

            leftThread.start();
            rightThread.start();

            try {
                leftThread.join();
                rightThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
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
