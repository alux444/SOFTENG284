public class Quicksort {

    public void quickSort(int[] A, int start, int end) {
        // we want to recursively partition around a random pivot.
        // in this implementation, we are doing this around the very last element.

        // recursion base case
        if (start >= end) {
            return;
        }

        int q = partition(A, start, end);

        // recursively call to each half of the array, as we can't be sure those are
        // sorted.
        quickSort(A, start, q - 1);
        quickSort(A, q + 1, end);

    }

    public int partition(int A[], int start, int end) {
        // in place implementation of partition
        int q = end;

        for (int i = end - 1; i >= 0; i--) {
            // moving is only required if the value to the left of q is greater.
            // we are placing the greater values to the right and lower values to the left.
            if (A[q] < A[i]) {
                // check if the index of i is equal to the index on the left of q. if not, swap
                // for efficient swapping instead of shifts.
                if (i != q - 1) {
                    swap(A, i, q - 1);
                }
                // swap the larger value to the right of q.
                swap(A, q - 1, q);
                // decrement q after swap
                q--;
            }
        }

        return q;
    }

    public static void swap(int[] arr, int i, int j) {
        if (i == j) {
            return;
        }

        // Swap the elements using a temporary variable
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] toSort = new int[] { 2, 1, 3 };
        Quicksort quicksorter = new Quicksort();
        quicksorter.quickSort(toSort, 0, 2);
        for (int i : toSort) {
            System.out.println(i);
        }
    }
}
