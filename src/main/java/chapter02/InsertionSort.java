package chapter02;

import java.util.Arrays;

public class InsertionSort {
    public static void insertionSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            int key = nums[i], j = i - 1;
            while (j >= 0 && nums[j] > key) {
                nums[j + 1] = nums[j--];
            }
            nums[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        int[] nums = { 24, 2, 45, 20, 56, 75, 2, 56, 99, 53, 12 };
        insertionSort(nums);
        System.out.println(Arrays.toString(nums));
    }
}