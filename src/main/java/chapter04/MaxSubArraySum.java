package chapter04;

public class MaxSubArraySum {
    public static int maxSubArraySum(int[] nums, int l, int r) {
        if (l == r)
            return nums[l];
        int m = l + (r - l) / 2;
        return Math.max(maxCrossingSum(nums, l, m, r),
                Math.max(maxSubArraySum(nums, l, m), maxSubArraySum(nums, m + 1, r)));
    }

    public static int maxCrossingSum(int[] nums, int l, int m, int r) {
        int sum = 0, leftMax = Integer.MIN_VALUE, rightMax = Integer.MIN_VALUE;
        for (int i = m; i >= l; i--) {
            sum += nums[i];
            if (sum > leftMax)
                leftMax = sum;
        }
        sum = 0;
        for (int i = m + 1; i <= r; i++) {
            sum += nums[i];
            if (sum > rightMax)
                rightMax = sum;
        }
        return leftMax + rightMax;
    }

    public static void main(String[] args) {
        int[] nums = { 2, 3, 4, 5, 7 };
        int res = maxSubArraySum(nums, 0, nums.length - 1);
        System.out.println("res: " + res);
    }
}