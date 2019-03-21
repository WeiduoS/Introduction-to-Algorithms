package chapter08;

import java.util.Arrays;

public class RadixSort {
    public static int[] radixSort(int[] nums) {
        int max = Integer.MIN_VALUE;
        for (int num : nums)
            max = Math.max(num, max);
        for (int exp = 1; max / exp > 0; exp *= 10) {
            nums = countSort(nums, exp);
        }
        return nums;
    }

    public static int[] countSort(int[] nums, int exp) {
        int[] res = new int[nums.length];
        int[] count = new int[10];

        for (int num : nums) {
            count[(num / exp) % 10]++;
        }
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }
        for (int i = nums.length - 1; i >= 0; i--) {
            res[count[(nums[i] / exp) % 10] - 1] = nums[i];
            count[(nums[i] / exp) % 10]--;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = { 24, 2, 45, 20, 56, 75, 2, 56, 99, 53, 12 };
        nums = radixSort(nums);
        System.out.println(Arrays.toString(nums));
    }
}