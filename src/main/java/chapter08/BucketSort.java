package chapter08;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BucketSort {
    public static int[] bucketSort(int[] nums) {
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        for (int num : nums) {
            max = Math.max(num, max);
            min = Math.min(num, min);
        }
        int size = max - min + 1;
        List<List<Integer>> buckets = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            buckets.add(new ArrayList<>());
        }
        for (int num : nums) {
            buckets.get((num - min) % size).add(num);
        }
        for (List<Integer> l : buckets) {
            Collections.sort(l);
        }
        List<Integer> res = new ArrayList<>();
        for (List<Integer> l : buckets)
            res.addAll(l);
        return res.stream().mapToInt(Integer::intValue).toArray();

    }

    public static void main(String[] args) {
        int[] nums = { 24, 2, 45, 20, 56, 75, 2, 56, 99, 53, 12 };
        nums = bucketSort(nums);
        System.out.println(Arrays.toString(nums));
    }
}