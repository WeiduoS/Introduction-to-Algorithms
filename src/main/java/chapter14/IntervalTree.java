package chapter14;

public class IntervalTree {
    int[] tree;

    public IntervalTree(int[] arr, int n) {
        int h = (int) (Math.ceil(Math.log(n) / Math.log(2)));
        int size = 2 * (int) Math.pow(2, h) - 1;
        tree = new int[size];
        construct(arr, 0, n - 1, 0);
    }

    private int construct(int[] arr, int ss, int se, int si) {
        if (ss == se) {
            tree[si] = arr[ss];
            return arr[ss];
        }

        int mid = ss + (se - ss) / 2;
        tree[si] = construct(arr, ss, mid, si * 2 + 1) + construct(arr, mid + 1, se, si * 2 + 2);
        return tree[si];
    }

    private int getSum(int n, int qstart, int qend) {
        if (qstart < 0 || qend > n - 1 || qstart > qend) {
            System.out.println("Invalid Input");
            return -1;
        }
        return getSumUntil(0, n - 1, qstart, qend, 0);
    }

    private int getSumUntil(int ss, int se, int qs, int qe, int si) {
        if (qs <= ss && qe >= se) {
            return tree[si];
        }
        if (qs > se || qe < ss)
            return 0;
        int mid = ss + (se - ss) / 2;
        return getSumUntil(ss, mid, qs, qe, si * 2 + 1) + getSumUntil(mid + 1, se, qs, qe, si * 2 + 2);
    }

    private void updateValue(int[] arr, int n, int i, int value) {
        if (i < 0 || i > n - 1) {
            System.out.println("Invalid Input");
            return;
        }
        int diff = value - arr[i];
        arr[i] = value;
        updateValueUntil(0, n - 1, i, diff, 0);
    }

    private void updateValueUntil(int ss, int se, int i, int diff, int si) {
        if (ss > i || i > se)
            return;

        tree[si] += diff;
        if (se != ss) {
            int mid = ss + (se - ss) / 2;
            updateValueUntil(ss, mid, i, diff, si * 2 + 1);
            updateValueUntil(mid + 1, se, i, diff, si * 2 + 2);
        }
    }

    public static void main(String[] args) {
        int arr[] = { 1, 3, 5, 7, 9, 11 };
        int n = arr.length;
        IntervalTree tree = new IntervalTree(arr, n);

        System.out.println("Sum of values in given range = " + tree.getSum(n, 1, 3));

        tree.updateValue(arr, n, 1, 10);
        System.out.println("Updated sum of values in given range = " + tree.getSum(n, 1, 3));

    }

}