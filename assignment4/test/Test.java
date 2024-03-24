public class Test {
    public static void main(String[] args) {
        int[] nums = { 5, 4, 2, 3, 1 };
        sortNumbers(nums);
        for (int num : nums) {
            System.out.println(num);
        }
    }

    // sortNumbers(int[] nums) method is expected to sort the array (nums) integers
    // in ascending order.
    static void sortNumbers(int[] nums) {
        int n = nums.length;
        int buffer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i - 1); j++) {
                if (nums[j - 1] > nums[j]) {
                    buffer = nums[j - 1];
                    nums[j - 1] = nums[j];
                    nums[j] = buffer;
                }
            }
        }
    }
}