package chapter05;

import java.util.Arrays;

public class PermutationSort {
    public static void permutationSort(int[] arr) {
        while (!isSort(arr)) {
            shuffle(arr);
        }
    }

    private static void shuffle(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            swap(arr, i, (int) (Math.random() * arr.length));
        }
    }

    private static boolean isSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] > arr[i])
                return false;
        }
        return true;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = { 3, 2, 5, 1, 0, 4, 90, 98 };
        System.out.println("Before sort: ");
        System.out.println(Arrays.toString(arr));
        permutationSort(arr);
        System.out.println("After sort: ");
        System.out.println(Arrays.toString(arr));
    }
}