package chapter14;

public class BinaryIndexTreeReverse {
    int[] BITree;

    BinaryIndexTreeReverse(int n) {
        BITree = new int[n];
    }

    private void update(int n, int index, int val) {
        index = index + 1;
        while (index > 0) {
            BITree[index] += val;
            index -= index & (-index);
        }
    }

    private int sum(int n, int index) {
        index = index + 1;
        int res = 0;
        while (index <= n) {
            res += BITree[index];
            index += index & (-index);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = { 2, 1, 1, 3, 2, 3, 4, 5, 6, 7, 8, 9 };
        BinaryIndexTreeReverse tree = new BinaryIndexTreeReverse(arr.length + 1);
        for (int i = 0; i < arr.length; i++) {
            tree.update(arr.length, i, arr[i]);
        }
        System.out.println("Sum of Elements in arr[n - 5..n - 1] is: " + tree.sum(arr.length, 5));
        arr[arr.length - 1] += 6;

        tree.update(arr.length, arr.length - 1, 6);
        System.out.println("Sum of Elements in arr[n - 5..n - 1] is: " + tree.sum(arr.length, 5));

    }

}