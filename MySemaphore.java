import java.util.ArrayList;

public class MySemaphore {
    private int places = 0;

    public MySemaphore(int places) {
        this.places = places;
    }

    public static ArrayList<MySemaphore> GetEmptySemaphores(int amount) {
        var list = new ArrayList<MySemaphore>();
        for (int i = 0; i < amount; i++) {
            list.add(new MySemaphore(0));
        }

        return list;
    }
    
    /**
     * passing (enter)
     */
    public synchronized void passeren() {
        if (places < 1){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        places--;
    }

    /**
     * release (leave)
     */
    public synchronized void vrijgave() {
        places++;
        notify();
    }

}
