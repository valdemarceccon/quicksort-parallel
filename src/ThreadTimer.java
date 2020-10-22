public class ThreadTimer {
    private final Thread t;

    public ThreadTimer(Thread t) {
        this.t = t;
    }

    public long analisar() throws InterruptedException {
        long ini = System.currentTimeMillis();
        t.start();
        t.join();
        return System.currentTimeMillis() - ini;
    }
}
