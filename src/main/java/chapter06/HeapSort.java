package chapter06;

import java.util.*;

public class HeapSort {
    public static void heapSort(int[] nums) {
        int n = nums.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(nums, n, i);
        }
        for (int i = n - 1; i >= 0; i--) {
            int temp = nums[0];
            nums[0] = nums[i];
            nums[i] = temp;
            heapify(nums, i, 0);
        }
    }

    public static void heapify(int[] nums, int n, int i) {
        int l = i * 2 + 1, r = i * 2 + 2;
        int largest = i;
        if (l < n && nums[l] > nums[largest])
            largest = l;
        if (r < n && nums[r] > nums[largest])
            largest = r;
        if (largest != i) {
            int temp = nums[largest];
            nums[largest] = nums[i];
            nums[i] = temp;
            heapify(nums, n, largest);
        }
    }

    public static void main(String[] args) {
        int[] nums = { 24, 2, 45, 20, 56, 75, 2, 56, 99, 53, 12 };
        heapSort(nums);
        System.out.println(Arrays.toString(nums));
    }
}