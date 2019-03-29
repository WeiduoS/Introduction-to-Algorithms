package chapter12.AVL_Delete;

import java.util.ArrayList;
import java.util.List;

public class AVLTreeDelete {
    private class TreeNode {
        int val, height;
        TreeNode left, right;

        TreeNode(int n) {
            this.val = n;
            this.height = 1;
        }
    }

    public TreeNode insert(TreeNode root, int val) {
        if (root == null)
            return new TreeNode(val);
        if (root.val < val) {
            root.right = insert(root.right, val);
        } else if (root.val > val) {
            root.left = insert(root.left, val);
        } else {
            return root;
        }

        root.height = 1 + Math.max(height(root.left), height(root.right));

        int balance = getBalance(root);

        if (balance > 1 && val < root.left.val) {
            return rightRotate(root);
        }
        if (balance < -1 && val > root.right.val) {
            return leftRotate(root);
        }
        if (balance > 1 && val > root.left.val) {
            root = leftRotate(root);
            return rightRotate(root);
        }
        if (balance < -1 && val < root.right.val) {
            root = rightRotate(root);
            return leftRotate(root);
        }
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

        root.height = 1 + Math.max(height(root.left), height(root.right));

        int balance = getBalance(root);

        if (balance > 1 && val < root.left.val) {
            return rightRotate(root);
        }
        if (balance < -1 && val > root.right.val) {
            return leftRotate(root);
        }
        if (balance > 1 && val > root.left.val) {
            root = leftRotate(root);
            return rightRotate(root);
        }
        if (balance < -1 && val < root.right.val) {
            root = rightRotate(root);
            return leftRotate(root);
        }
        return root;
    }

    private TreeNode rightRotate(TreeNode y) {
        TreeNode x = y.left;
        TreeNode T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = 1 + Math.max(height(y.left), height(y.right));
        x.height = 1 + Math.max(height(x.left), height(x.right));

        return x;
    }

    private TreeNode leftRotate(TreeNode x) {
        TreeNode y = x.right;
        TreeNode T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = 1 + Math.max(height(x.left), height(x.right));
        y.height = 1 + Math.max(height(y.left), height(y.right));

        return y;
    }

    private int getBalance(TreeNode node) {
        if (node == null)
            return 0;
        return height(node.left) - height(node.right);
    }

    private int height(TreeNode node) {
        if (node == null)
            return 0;
        return node.height;
    }

    private int findSuccessor(TreeNode node) {
        while (node.left != null)
            node = node.left;
        return node.val;
    }

    public List<Integer> preOrder(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        helper(root, res);
        return res;
    }

    public void helper(TreeNode root, List<Integer> list) {
        if (root == null)
            return;
        list.add(root.val);
        helper(root.left, list);
        helper(root.right, list);
    }

    public static void main(String[] args) {
        AVLTreeDelete obj = new AVLTreeDelete();
        TreeNode root = null;
        root = obj.insert(root, 50);
        root = obj.insert(root, 40);
        root = obj.insert(root, 30);
        root = obj.insert(root, 20);
        root = obj.insert(root, 10);
        root = obj.insert(root, 55);
        System.out.println(obj.preOrder(root));
        System.out.println("root is: " + root.val);
        System.out.println(" ");
        obj.delete(root, 40);
        System.out.println("Preorder traversal after deletion of 40 : ");
        System.out.println(obj.preOrder(root));
        System.out.println("root is: " + root.val);
    }
}