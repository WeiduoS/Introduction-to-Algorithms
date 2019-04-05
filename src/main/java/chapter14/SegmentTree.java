package chapter14;

public class SegmentTree {
    private class Node {
        int l, r, k;
        Node left, right;

        Node(int l, int r, int k) {
            this.l = l;
            this.r = r;
            this.k = k;
        }
    }

    private Node root;

    private Node construct(Node node, int l, int r, int val) {
        if (l == r) {
            return new Node(l, r, val);
        } else {
            node = new Node(l, r, val);
            int m = l + (r - l) / 2;
            node.left = construct(node.left, l, m, val);
            node.right = construct(node.right, m + 1, r, val);
        }
        return node;
    }

    private int query(Node node, int qs, int qe) {
        if (qs > qe || node == null || qs > node.r || qe < node.l)
            return 0;
        if (qs <= node.l && qe >= node.r)
            return node.k;
        return query(node.left, qs, qe) + query(node.right, qs, qe);
    }

    private void update(Node node, int ss, int se, int val) {
        if (node.l == node.r && ss <= node.l && se >= node.r) {
            node.k += val;
            return;
        }
        if (ss > se || node == null || ss > node.r || se < node.l)
            return;
        update(node.left, ss, se, val);
        update(node.right, ss, se, val);
        node.k = node.left.k + node.right.k;
    }

    public static void main(String[] args) {
        int arr[] = { 1, 3, 5, 7, 9, 11 };
        int n = arr.length;
        int h = (int) Math.ceil((Math.log(n) / Math.log(2)));
        int size = (int) Math.pow(2, h + 1) - 1;
        SegmentTree obj = new SegmentTree();
        SegmentTree.Node root = obj.construct(obj.root, 0, size, 0);
        for (int i = 0; i < arr.length; i++) {
            obj.update(root, i, i, arr[i]);
        }

        System.out.println("Sum of values in given range = " + obj.query(root, 1, 3));
        obj.update(root, 1, 1, 7);
        System.out.println("Updated sum of values in given range = " + obj.query(root, 1, 3));

    }
}