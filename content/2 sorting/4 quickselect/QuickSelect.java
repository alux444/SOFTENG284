public class QuickSelect {

    public int quickSelect(int[] A, int k) {
        int left = 0;
        int right = A.length - 1;

        while (left <= right) {
            int q = partition(A, left, right);

            // if the index is correct, we return
            if (q == k - 1) {
                return A[q];
            } else if (q > k - 1) {
                // if the index is greater than the one we want, we know that it must be in the
                // LEFT array from that partition
                right = q - 1;
            } else {
                // otherwise, the index is less. so we should go to the right array.
                left = q + 1;
            }
        }

        return A[left];
    }

    public int partition(int[] A, int start, int end) {
        int q = end;
        for (int i = end - 1; i >= 0; i--) {
            if (A[q] < A[i]) {

                // check if index value of i is currently adjacent to q. if not, swap i with q -
                // 1
                if (i != q - 1) {
                    swap(A, i, q - 1);
                }

                // if current value is less than the end, we want the value to be on the left
                // side of the array.
                swap(A, q, q - 1);
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
        int[] toSort = new int[] { 2, 1, 3, 5, 2, 8, 10 };
        QuickSelect selector = new QuickSelect();
        System.out.println(selector.quickSelect(toSort, 4));
    }
}
