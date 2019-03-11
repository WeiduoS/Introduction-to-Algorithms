package chapter02;

import java.util.Arrays;

public class MergeSort {
    public static void mergeSort(int[] nums, int l, int r) {
        if (l == r)
            return;
        int m = l + (r - l) / 2;
        mergeSort(nums, l, m);
        mergeSort(nums, m + 1, r);
        merge(nums, l, m, r);
    }

    public static void merge(int[] nums, int l, int m, int r) {
        int[] left = Arrays.copyOfRange(nums, l, m + 1);
        int[] right = Arrays.copyOfRange(nums, m + 1, r + 1);
        int i = 0, j = 0;
        for (int k = l; k <= r; k++) {
            if (i == left.length) {
                nums[k] = right[j++];
                continue;
            }
            if (j == right.length) {
                nums[k] = left[i++];
                continue;
            }
            if (left[i] <= right[j]) {
                nums[k] = left[i++];
            } else {
                nums[k] = right[j++];
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = { 24, 2, 45, 20, 56, 75, 2, 56, 99, 53, 12 };
        mergeSort(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));
    }
}