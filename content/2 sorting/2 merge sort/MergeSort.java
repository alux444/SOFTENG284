public class MergeSort {

    public void mergeSort(int[] A, int start, int end) {
        // we need to split the whole array into sortable pairs and recombine.

        // base case for recursion.
        if (start == end) {
            return;
        }

        // recursively run after splitting array in half
        int mid = (start + end) / 2;
        mergeSort(A, start, mid);
        mergeSort(A, mid + 1, end);

        // recombine sorted arrays with a combining algorithm.
        combineSorted(A, start, mid, end);
    }

    public void combineSorted(int[] A, int start, int mid, int end) {
        System.out.println("combine sorting");
        int[] B = new int[end - start + 1];
        int leftPointer = start;
        int rightPointer = mid + 1;

        for (int i = 0; i < end - start + 1; i++) {
            // if the left pointer has gone through first half of array:
            if (leftPointer > mid) {
                B[i] = A[rightPointer++];
            }
            // if the right pointer has gone through the other half already:
            else if (rightPointer > end) {
                B[i] = A[leftPointer++];
            }
            // otherwise, compare item from each side of array. increment pointer of used
            // value.
            else if (A[leftPointer] < A[rightPointer]) {
                B[i] = A[leftPointer++];
            } else {
                // else, the right pointer is smaller. increment the right side.
                B[i] = A[rightPointer++];
            }
        }

        // copy array B to A
        for (int j = 0; j < B.length; j++) {
            A[start + j] = B[j];
            System.out.println(B[j]);
        }
    }

    public static void main(String[] args) {
        int[] toSort = new int[] { 2, 1, 3 };
        MergeSort mergeSorter = new MergeSort();
        mergeSorter.mergeSort(toSort, 0, toSort.length - 1);
    }
}
