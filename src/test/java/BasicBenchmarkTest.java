import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class BasicBenchmarkTest {

    @Test
    void basic_benchmark() {
        int[] arrayJava = TestUtils.generateRandomArray(100_000, 0, 10000);
        int[] arrayIPS4o = Arrays.copyOf(arrayJava, arrayJava.length);
        int[] arrayIPS4oVT = Arrays.copyOf(arrayJava, arrayJava.length);
        int[] arrayIPS4oIVT = Arrays.copyOf(arrayJava, arrayJava.length);

        // Benchmark Java sort
        long javaStartTime = System.currentTimeMillis();
        Arrays.sort(arrayJava);
        long javaEndTime = System.currentTimeMillis();
        long javaExecutionTime = javaEndTime - javaStartTime;

        // Benchmark Java Parallel arrays sort
        long javaPAStartTime = System.currentTimeMillis();
        Arrays.parallelSort(arrayJava);
        long javaPAEndTime = System.currentTimeMillis();
        long javaPAExecutionTime = javaPAEndTime - javaPAStartTime;

        // Benchmark IPS4O sort
        long ips4oStartTime = System.currentTimeMillis();
        IPS4ONormalThreadSorter.sort(arrayIPS4o);
        long ips4oEndTime = System.currentTimeMillis();
        long ips4oExecutionTime = ips4oEndTime - ips4oStartTime;

        // Benchmark IPS4O Virtual Thread sort
        long ips4oVirtualStartTime = System.currentTimeMillis();
        IPS4OVirtualThreadSorter.sort(arrayIPS4oVT);
        long ips4oVirtualEndTime = System.currentTimeMillis();
        long ips4oVirtualExecutionTime = ips4oVirtualEndTime - ips4oVirtualStartTime;

        // Benchmark IPS4O Improved Virtual Thread sort
        long ips4oIVirtualStartTime = System.currentTimeMillis();
        IPS4OVirtualThreadSorter.sort(arrayIPS4oIVT);
        long ips4oIVirtualEndTime = System.currentTimeMillis();
        long ips4oIVirtualExecutionTime = ips4oIVirtualEndTime - ips4oIVirtualStartTime;

        System.out.println("Java: " + javaExecutionTime + " (ms)");
        System.out.println("Java PA: " + javaPAExecutionTime + " (ms)");
        System.out.println("IPS4o T: " + ips4oExecutionTime + " (ms)");
        System.out.println("IPS4o VT: " + ips4oVirtualExecutionTime + " (ms)");
        System.out.println("IPS4o IVT: " + ips4oIVirtualExecutionTime + " (ms)");

    }
}
