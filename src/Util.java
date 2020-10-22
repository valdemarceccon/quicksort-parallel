import java.util.Random;

public class Util {

    public static int[] criarArrayEmbaralhado(int tam) {
        int[] ret = new int[tam];

        for (int i = 0; i < tam; i++) {
            ret[i] = i + 1;
        }

        embaralhar(ret);

        return ret;
    }

    private static void embaralhar(int[] ret) {
        Random r = new Random();
        for (int i = 0; i < ret.length; i++) {
            int p = r.nextInt(ret.length);
            trocar(ret, i, p);
        }
    }

    public static void trocar(int[] ret, int i, int p) {
        int aux = ret[i];
        ret[i] = ret[p];
        ret[p] = aux;
    }
}
