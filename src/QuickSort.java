public class QuickSort {
    private final int start;
    private final int end;
    private final int[] arr;

    private QuickSort(int start, int end, int[] arr) {
        this.start = start;
        this.end = end;
        this.arr = arr;
    }

    public QuickSort(int[] arr) {
        this(0, arr.length - 1, arr);
    }

    public void sort() {
        if (start >= end)
            return;

        int p = partition(arr, start, end);

        QuickSort left = new QuickSort(start, p - 1, arr);
        QuickSort right = new QuickSort(p + 1, end, arr);

        left.sort();
        right.sort();
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
