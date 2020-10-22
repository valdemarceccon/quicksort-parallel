public class Main {


    public static int MAX_THREADS = 1;

    public static void main(String[] args) throws InterruptedException {
        int p = 1;
        while (p <= Runtime.getRuntime().availableProcessors()) {
            if (p * 2 <= Runtime.getRuntime().availableProcessors())
                p = p * 2;
            else
                break;
        }
        MAX_THREADS = p;
        test();
    }

    public static void test() throws InterruptedException {
        System.out.printf("Número máximo de threads %d%n", MAX_THREADS);
        for (int i = 15; i <= 26; i++) {
            int qtde = (int) Math.pow(2, i);
            int[] a = Util.criarArrayEmbaralhado(qtde);
            QuickSort quickSort = new QuickSort(a);
            System.out.printf("sort de 2^%d elementos levou ", i);
            long tempo = new ThreadTimer(quickSort).analisar();
            System.out.printf("%d ms%n", tempo);
        }
    }
}
