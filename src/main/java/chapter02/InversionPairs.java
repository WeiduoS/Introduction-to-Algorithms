package chapter02;

import java.util.Arrays;

public class InversionPairs {
    static int mergeSort(int[] nums, int l, int r) {
        if (l == r)
            return 0;
        int m = l + (r - l) / 2, res = 0;
        res += mergeSort(nums, l, m);
        res += mergeSort(nums, m + 1, r);
        res += merge(nums, l, m, r);
        return res;
    }

    static int merge(int[] nums, int l, int m, int r) {
        int[] left = Arrays.copyOfRange(nums, l, m + 1);
        int[] right = Arrays.copyOfRange(nums, m + 1, r + 1);
        int res = 0, i = 0, j = 0;
        for (int k = l; k <= m; k++) {
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
                res += left.length - i;
                nums[k] = right[j++];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = { 1, 20, 6, 4, 5 };
        int res = mergeSort(nums, 0, nums.length - 1);
        System.out.println("res: " + res);
    }
}