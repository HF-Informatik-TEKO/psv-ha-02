import java.util.ArrayList;

public class MyThread extends Thread {
    
    private final String NAME;
    private final ArrayList<MySemaphore> SEMS;
    private final boolean IS_SLEEP;
    public int[] passeren;
    public int[] vrijgave;

    public MyThread(
        String name, 
        ArrayList<MySemaphore> sems,
        boolean isSleep
        ) {
        this.NAME = name;
        this.SEMS = sems;
        this.IS_SLEEP = isSleep;
    }

    @Override
    public void run() {
        passeren();
        sleepIfNecessary();
        work();        
        vrijgave();
    }
    
    private void work() {
        System.out.println(NAME + " is executed");
    }

    private void sleepIfNecessary() {
        if (IS_SLEEP) {
            try {
                // System.out.println(NAME + " is sleeping");
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void vrijgave() {
        if (vrijgave != null && vrijgave.length > 0) {
            for (int p : vrijgave) {
                SEMS.get(p).vrijgave();
            }
        }
    }

    private void passeren() {
        if (passeren != null && passeren.length > 0) {
            for (int p : passeren) {
                SEMS.get(p).passeren();
            }
        }
    }
}
