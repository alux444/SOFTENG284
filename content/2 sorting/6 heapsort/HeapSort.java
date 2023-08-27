public class HeapSort {

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
    }
}
