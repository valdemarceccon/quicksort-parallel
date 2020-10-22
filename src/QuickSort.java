public class QuickSort extends Thread {
    private final int start;
    private final int end;
    private final int[] arr;
    public static MutexInt m = new MutexInt(0);
    private final MutexInt mi;

    private QuickSort(MutexInt mi, int start, int end, int[] arr) {
        this.mi = mi;
        this.start = start;
        this.end = end;
        this.arr = arr;
    }

    public QuickSort(int[] arr) {
        this(new MutexInt(0), 0, arr.length - 1, arr);
    }

    @Override
    public void run() {
        if (start >= end)
            return;

        int p = partition(arr, start, end);

        QuickSort left = new QuickSort(mi, start, p - 1, arr);
        QuickSort right = new QuickSort(mi, p + 1, end, arr);

        try {
            if (mi.getValue() <= Main.MAX_THREADS) {
                mi.add(2);
                left.start();
                right.start();

                left.join();
                right.join();
            } else {
                left.run();
                right.run();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static int partition(int[] arr, int start, int end) {
        int i = start, j = end - 1;

        while (i <= j) {
            if (arr[i] <= arr[end]) {
                i++;
            } else if (arr[j] >= arr[end]) {
                j--;
            } else {
                Util.trocar(arr, i, j);
                j--;
                i++;
            }
        }
        j++;

        Util.trocar(arr, j, end);

        return j;
    }
}
