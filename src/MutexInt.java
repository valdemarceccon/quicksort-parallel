import java.util.concurrent.Semaphore;

public class MutexInt {
    private int i;
    private final Semaphore s;

    public MutexInt(int i) {
        s = new Semaphore(1);
        this.i = i;
    }

    public void add(int v) {
        try {
            s.acquire();
            i=i+v;
            s.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getValue() {
        return i;
    }
}
