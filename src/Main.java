import java.util.Arrays;
import java.util.concurrent.Semaphore;

public class Main {


    public static void main(String[] args) throws InterruptedException {
        test();
    }

    private static int getMaxThreads() {
        int p = 1;
        int nProc = Runtime.getRuntime().availableProcessors();
        while (p <= nProc) {
            if (p * 2 <= nProc)
                p = p * 2;
            else
                break;
        }
        return p;
    }

    public static void test() throws InterruptedException {
        int maxThreads = getMaxThreads();
        System.out.printf("Número máximo de threads %d%n", maxThreads);
        for (int i = 15; i <= 26; i++) {
            int qtde = (int) Math.pow(2, i);
            int[] a = Util.criarArrayEmbaralhado(qtde);
            int[] b = Arrays.copyOf(a, a.length);
            quickSortParallel(maxThreads, i, a);
            quickSort(i, b);
            System.out.println();
        }
    }

    private static void quickSortParallel(int maxThreads, int i, int[] a) throws InterruptedException {
        Semaphore s = new Semaphore(maxThreads);
        QuickSortParallel quickSortParallel = new QuickSortParallel(s, a);
        System.out.printf("sort de 2^%d com thread elementos levou ", i);
        long ini = System.currentTimeMillis();
        quickSortParallel.start();
        quickSortParallel.join();
        long fim = System.currentTimeMillis();
        System.out.printf("%d ms%n", fim - ini);
    }

    private static void quickSort(int i, int[] b) {
        long ini;
        long fim;
        QuickSort quickSort = new QuickSort(b);
        System.out.printf("sort de 2^%d sem thread elementos levou ", i);
        ini = System.currentTimeMillis();
        quickSort.sort();
        fim = System.currentTimeMillis();
        System.out.printf("%d ms%n", fim - ini);
    }
}
