package chapter14;

public class BinaryIndexTree {
    int[] BITree;

    BinaryIndexTree(int n) {
        BITree = new int[n];
    }

    private void update(int n, int index, int val) {
        index = index + 1;
        while (index <= n) {
            BITree[index] += val;
            index += index & (-index);
        }
    }

    private int sum(int n, int index) {
        index = index + 1;
        int res = 0;
        while (index > 0) {
            res += BITree[index];
            index -= index & (-index);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = { 2, 1, 1, 3, 2, 3, 4, 5, 6, 7, 8, 9 };
        BinaryIndexTree tree = new BinaryIndexTree(arr.length + 1);
        for (int i = 0; i < arr.length; i++) {
            tree.update(arr.length, i, arr[i]);
        }
        System.out.println("Sum of Elements in arr[0..5] is: " + tree.sum(arr.length, 5));
        arr[3] += 6;

        tree.update(arr.length, 3, 6);
        System.out.println("Sum of Elements in arr[0..5] is: " + tree.sum(arr.length, 5));

    }
}