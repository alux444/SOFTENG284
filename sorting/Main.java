class Main {

    public static int[] selectionSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int minIndex = i;
            int value = array[i];
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            array[i] = array[minIndex];
            array[minIndex] = value;
        }
        return array;
    }

    public static int[] insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int currentValue = array[i];
            int insertAt = i - 1;
            while ((insertAt >= 0) && (currentValue < array[insertAt])) {
                array[insertAt + 1] = array[insertAt];
                insertAt--;
            }
            array[insertAt + 1] = currentValue;
        }
        return array;
    }

    public static void main(String[] args) {
        int[] sortedBySelection = selectionSort(new int[] { 1, 2, 3, 5, -2, -3, 4 });
        int[] sortedByInsertion = insertionSort(new int[] { 1, 2, 3, 5, -2, -3, 4 });
        for (int num : sortedBySelection) {
            System.out.println(num);
        }
        for (int num : sortedByInsertion) {
            System.out.println(num);
        }
    }
}