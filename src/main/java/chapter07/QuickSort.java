package chapter07;

import java.util.*;

public class QuickSort {
    public static void quickSort(int[] nums, int l, int r) {
        int pivot = l + (r - l) / 2, left = l, right = r;
        while (l < r) {
            while (nums[l] < nums[pivot])
                l++;
            while (nums[r] > nums[pivot])
                r--;
            if (l <= r) {
                int temp = nums[l];
                nums[l] = nums[r];
                nums[r] = temp;
                l++;
                r--;
            }
        }
        if (left < r)
            quickSort(nums, left, r);
        if (l < right)
            quickSort(nums, l, right);
    }

    public static void shuffle(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            swap(nums, i, (int) (Math.random() * nums.length));
        }
    }

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = { 24, 2, 45, 20, 56, 75, 2, 56, 99, 53, 12 };
        shuffle(nums);
        quickSort(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));
    }
}