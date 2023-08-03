public class CountingSort {

    public void countingSortForDecimal(int A[], int exp) {
        int[] count = new int[10];
        for (int n : A) {
            count[(n / exp) % 10]++;
        }
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }
        int[] out = new int[A.length];
        for (int i = A.length - 1; i >= 0; i--) {
            int current = A[i];
            int positionInArray = count[(current / exp) % 10] - 1;
            out[positionInArray] = current;
            count[(current / exp) % 10]--;
        }

        for (int i = 0; i < A.length; i++) {
            A[i] = out[i];
        }
    }

    public void radixSort(int A[], int size) {
        int max = getMax(A, size);

        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSortForDecimal(A, exp);
        }
    }

    int getMax(int array[], int n) {
        int max = array[0];
        for (int i = 1; i < n; i++)
            if (array[i] > max)
                max = array[i];
        return max;
    }

    public static void main(String[] args) {
        int[] toSort = new int[] { 2, 1, 3 };
        CountingSort sorter = new CountingSort();
        sorter.radixSort(toSort, 3);
        for (int n : toSort) {
            System.out.println(n);
        }
    }
}
