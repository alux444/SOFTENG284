public class Main {

    public static int findIndexOf(int toFind, int[] array) {
        int left = 1;
        int right = array.length;

        while (left <= right) {
            int k = (left + right) / 2;
            if (array[k] == toFind) {
                return k;
            } else if (array[k] < toFind) {
                left = k + 1;
            } else if (array[k] > toFind) {
                right = k - 1;
            }
        }

        return -1;
    }

    public static int findFirstIndexOf(int toFind, int[] array) {
        int left = 1;
        int right = array.length;

        while (left <= right) {
            int k = (left + right) / 2;
            if (array[k] >= toFind) {
                right = k;
            } else {
                left = k + 1;
            }
            if (array[left] == toFind) {
                return left;
            }
        }

        return -1;
    }

    public static int findLastIndexOf(int toFind, int[] array) {
        int left = 1;
        int right = array.length;

        while (left <= right) {
            int k = (int) Math.ceil((left + right) / 2);
            if (array[k] <= toFind) {
                left = k;
            } else {
                right = k - 1;
            }
            if (array[left] == toFind) {
                return left;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] array = new int[] { 1, 2, 3, 3, 3, 3, 3, 5, 6, 7, 8 };
        // Given a sorted array of integers A and an integer x, find an index of x in A
        // or return -1 if x is not in A.
        // System.out.println(findIndexOf(3, array));
        System.out.println(findFirstIndexOf(3, array));
        System.out.println(findLastIndexOf(3, array));
    }
}