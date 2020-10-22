import java.util.concurrent.Semaphore;

public class QuickSortParallel extends Thread {
    private final int start;
    private final int end;
    private final int[] arr;
    private final Semaphore mi;

    private QuickSortParallel(Semaphore mi, int start, int end, int[] arr) {
        this.mi = mi;
        this.start = start;
        this.end = end;
        this.arr = arr;
    }

    public QuickSortParallel(Semaphore s, int[] arr) {
        this(s, 0, arr.length - 1, arr);
    }

    @Override
    public void run() {
        if (start >= end)
            return;

        int pIndex = partition(arr, start, end);

        QuickSortParallel left = new QuickSortParallel(mi, start, pIndex - 1, arr);
        QuickSortParallel right = new QuickSortParallel(mi, pIndex + 1, end, arr);

        try {
            boolean iniciouEsquerda = false;
            boolean iniciouDireita = false;
            if (mi.tryAcquire()) {
                iniciouEsquerda = true;
                left.start();

            } else {
                left.run();
            }

            if (mi.tryAcquire()) {
                right.start();
                iniciouDireita = true;
            } else {
                right.run();
            }

            if (iniciouDireita)
                left.join();
            if (iniciouEsquerda)
                right.join();


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
