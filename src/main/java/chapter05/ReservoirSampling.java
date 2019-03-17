package chapter05;

import java.util.*;

public class ReservoirSampling {
    public static int[] reservoirSampling(int[] arr, int k) {
        int[] res = new int[k];
        int i = 0;
        for (; i < k; i++)
            res[i] = arr[i];
        for (; i < arr.length; i++) {
            int j = (int) Math.random() * (i + 1);
            if (j < k) {
                res[j] = arr[i];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };
        int[] res = reservoirSampling(arr, 5);
        System.out.println(Arrays.toString(res));
    }
}