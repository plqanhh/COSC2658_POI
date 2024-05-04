
// src/PerformanceUtility.java
import ADTs.*;;

public class PerformanceUtility {

    public static void measureExecutionTime(Runnable task) {
        long startTime = System.nanoTime();
        task.run();
        long endTime = System.nanoTime();
        System.out.println("Execution Time: " + ((endTime - startTime) / 1e6) + " ms");
    }
}
