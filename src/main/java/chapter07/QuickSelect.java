package chapter07;

public class QuickSelect {
    public static int quickSelect(int[] nums, int l, int r, int k) {
        if (l == r)
            return nums[l];
        int pivot = partition(nums, l, r);
        if (pivot == k)
            return nums[pivot];
        else if (pivot < k)
            return quickSelect(nums, pivot + 1, r, k);
        else
            return quickSelect(nums, l, pivot - 1, k);
    }

    public static int partition(int[] nums, int l, int r) {
        int pivot = nums[r], index = l;
        for (int i = l; i <= r - 1; i++) {
            if (nums[i] < pivot) {
                nums[index++] = nums[i];
            }
        }
        nums[index] = pivot;
        return index;
    }

    public static void main(String[] args) {
        int[] nums = { 24, 2, 45, 20, 56, 75, 2, 56, 99, 53, 12 };
        // the third num
        int the_third = quickSelect(nums, 0, nums.length - 1, 2);
        System.out.println("the third num is: " + the_third);
    }
}