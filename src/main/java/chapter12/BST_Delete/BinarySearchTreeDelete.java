package chapter12.BST_Delete;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTreeDelete {
    public class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int n) {
            this.val = n;
            this.left = null;
            this.right = null;
        }
    }

    public TreeNode insert(TreeNode root, int val) {
        if (root == null)
            return new TreeNode(val);
        if (root.val < val)
            root.right = insert(root.right, val);
        else if (root.val > val)
            root.left = insert(root.left, val);
        return root;
    }

    public TreeNode delete(TreeNode root, int val) {
        if (root == null)
            return null;
        if (root.val < val) {
            root.right = delete(root.right, val);
        } else if (root.val > val) {
            root.left = delete(root.left, val);
        } else {
            if (root.left == null)
                return root.right;
            if (root.right == null)
                return root.left;
            root.val = findSuccessor(root.right);
            root.right = delete(root.right, root.val);
        }
        return root;
    }

    private int findSuccessor(TreeNode root) {
        while (root.left != null)
            root = root.left;
        return root.val;
    }

    public List<Integer> inOrder(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        helper(root, res);
        return res;
    }

    public void helper(TreeNode root, List<Integer> list) {
        if (root == null)
            return;
        helper(root.left, list);
        list.add(root.val);
        helper(root.right, list);

    }

    public static void main(String[] args) {
        BinarySearchTreeDelete obj = new BinarySearchTreeDelete();
        TreeNode root = null;
        root = obj.insert(root, 50);
        obj.insert(root, 20);
        obj.insert(root, 40);
        obj.insert(root, 70);
        obj.insert(root, 60);
        obj.insert(root, 80);
        System.out.println(obj.inOrder(root));
        obj.delete(root, 50);
        System.out.println(obj.inOrder(root) + " delete: 50");
        obj.delete(root, 70);
        System.out.println(obj.inOrder(root) + " delete: 70");
        obj.delete(root, 20);
        System.out.println(obj.inOrder(root) + " delete: 20");
    }
}