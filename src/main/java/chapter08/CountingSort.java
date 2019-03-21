package chapter08;

import java.util.Arrays;

public class CountingSort {
    public static int[] countingSort(int[] A) {
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        for (int num : A) {
            max = Math.max(num, max);
            min = Math.min(num, min);
        }
        int[] C = new int[max - min + 1];
        for (int num : A) {
            C[num - min]++;
        }
        for (int i = 1; i < C.length; i++) {
            C[i] = C[i - 1] + C[i];
        }
        int[] B = new int[A.length];
        for (int i = A.length - 1; i >= 0; i--) {
            B[C[A[i] - min] - 1] = A[i];
            C[A[i] - min]--;
        }
        return B;
    }

    public static void main(String[] args) {
        int[] nums = { 24, 2, 45, 20, 56, 75, 2, 56, 99, 53, 12 };
        int[] res = countingSort(nums);
        System.out.println(Arrays.toString(res));
    }
}