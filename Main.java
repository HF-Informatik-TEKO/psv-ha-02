import java.util.ArrayList;

/** Scenario
 * Start thread 1 and 2 at the same time.
 * Thread 2 sleeps for 100 ms.
 * Thread 1 executes immediately.
 * Thread 3 executes after Thread 1 has finished.
 * Thread 2 executes after finish waiting.
 * Thread 4 executes after Thread 2 has finished.
 * Thread 5 executes after Thread 3 and 4 has finished.
 */
public class Main {
    public static void main(String[] args) {

        var sems = MySemaphore.GetEmptySemaphores(4);
        var threads = new ArrayList<MyThread>();

        for (int i = 0; i < 5; i++) {
            var hasSleepFlag = i == 1;
            var t = new MyThread("Thread " + (i + 1), sems, hasSleepFlag);
            threads.add(t);
        }
        configureThreadExecutionLogic(threads);
        
        for (var t : threads) {
            t.start();
        }
    }

    private static void configureThreadExecutionLogic(ArrayList<MyThread> threads) {
        threads.get(0).vrijgave = new int[] { 0 };
        threads.get(2).passeren = new int[] { 0 };

        threads.get(1).vrijgave = new int[] { 1 };
        threads.get(3).passeren = new int[] { 1 };
        
        threads.get(2).vrijgave = new int[] { 2 };
        threads.get(3).vrijgave = new int[] { 3 };
        threads.get(4).passeren = new int[] { 2, 3 };
    }
}