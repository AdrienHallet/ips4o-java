package benchmark;

import algorithms.IPS4OImprovedVirtualThreadSorter;
import algorithms.IPS4ONormalThreadSorter;
import algorithms.IPS4OVirtualThreadSorter;
import org.openjdk.jmh.annotations.*;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 5, time = 1)
@Fork(5)
public class Suite {

    private static final int[] DATA = Utils.generateRandomArray(100_000, 0, 1000);


    @Benchmark
    public int[] java_sort() {
        int[] toSort = Arrays.copyOf(DATA, DATA.length);
        Arrays.sort(toSort);
        return toSort;
    }

    @Benchmark
    public int[] java_parallel_sort() {
        int[] toSort = Arrays.copyOf(DATA, DATA.length);
        Arrays.parallelSort(toSort);
        return toSort;
    }

    @Benchmark
    public int[] ipso4_normal_sort() {
        int[] toSort = Arrays.copyOf(DATA, DATA.length);
        IPS4ONormalThreadSorter.sort(toSort);
        return toSort;
    }

    @Benchmark
    public int[] ipso4_virtual_threads_sort() {
        int[] toSort = Arrays.copyOf(DATA, DATA.length);
        IPS4OVirtualThreadSorter.sort(toSort);
        return toSort;
    }

    @Benchmark
    public static int[] ipso4_improved_virtual_threads_sort() {
        int[] toSort = Arrays.copyOf(DATA, DATA.length);
        IPS4OImprovedVirtualThreadSorter.sort(toSort);
        return toSort;
    }
}
