package chapter12.BST_Insert;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTreeInsert {
    private class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int n) {
            this.val = n;
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
        BinarySearchTreeInsert obj = new BinarySearchTreeInsert();
        TreeNode root = null;
        root = obj.insert(root, 50);
        obj.insert(root, 20);
        obj.insert(root, 40);
        obj.insert(root, 70);
        obj.insert(root, 60);
        obj.insert(root, 80);
        System.out.println(obj.inOrder(root));
    }
}