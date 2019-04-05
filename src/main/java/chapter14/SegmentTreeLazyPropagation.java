package chapter14;

public class SegmentTreeLazyPropagation {
    private class Node {
        int l, r, k, lazy;
        Node left, right;

        Node(int l, int r, int k) {
            this.l = l;
            this.r = r;
            this.k = k;
        }
    }

    private int query(Node node, int l, int r) {
        normalize(node);
        if (l > r || node == null || l > node.r || r < node.l)
            return 0;
        if (l <= node.l && r >= node.r)
            return node.k;
        return query(node.left, l, r) + query(node.right, l, r);
    }

    private void update(Node node, int l, int r, int val) {
        normalize(node);
        if (l > r || node == null || l > node.r || l < node.l)
            return;
        if (l <= node.l && r >= node.r) {
            node.lazy += val;
            normalize(node);
            return;
        }

        update(node.left, l, r, val);
        update(node.right, l, r, val);
        node.k = node.left.k + node.right.k;
    }

    private void normalize(Node node) {
        if (node.lazy > 0) {
            node.k += node.lazy;
        }
        if (node.l < node.r) {
            if (node.left == null || node.right == null) {
                int mid = node.l + (node.r - node.l) / 2;
                node.left = new Node(node.l, mid, node.k);
                node.right = new Node(mid + 1, node.r, node.k);
            } else if (node.lazy > 0) {
                node.left.lazy += node.lazy;
                node.right.lazy += node.lazy;
            }
        }
        node.lazy = 0;
    }

    public static void main(String[] args) {
        int arr[] = { 1, 3, 5, 7, 9, 11 };
        int n = arr.length;
        int h = (int) Math.ceil((Math.log(n) / Math.log(2)));
        int size = (int) Math.pow(2, h + 1) - 1;
        SegmentTreeLazyPropagation obj = new SegmentTreeLazyPropagation();
        SegmentTreeLazyPropagation.Node root = obj.new Node(0, size, 0);
        for (int i = 0; i < arr.length; i++) {
            obj.update(root, i, i, arr[i]);
        }
        System.out.println("Sum of values in given range = " + obj.query(root, 1, 3));
        obj.update(root, 1, 1, 7);
        System.out.println("Updated sum of values in given range = " + obj.query(root, 1, 3));
    }
}