package chapter12.BST_Traverse;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PreOrder {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int n) {
            this.val = n;
        }
    }

    // 40
    // / \
    // 20 60
    // / \ / \
    // 10 30 50 70
    public TreeNode creatTree() {
        TreeNode rootNode = new TreeNode(40);
        TreeNode node20 = new TreeNode(20);
        TreeNode node10 = new TreeNode(10);
        TreeNode node30 = new TreeNode(30);
        TreeNode node60 = new TreeNode(60);
        TreeNode node50 = new TreeNode(50);
        TreeNode node70 = new TreeNode(70);

        rootNode.left = node20;
        rootNode.right = node60;

        node20.left = node10;
        node20.right = node30;

        node60.left = node50;
        node60.right = node70;

        return rootNode;
    }

    public static List<Integer> preOrderTraverseRec(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        helper(root, list);
        return list;
    }

    private static void helper(TreeNode root, List<Integer> list) {
        if (root == null)
            return;
        list.add(root.val);
        helper(root.left, list);
        helper(root.right, list);
    }

    public static List<Integer> preOrderTraverseIter01(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                list.add(root.val);
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            root = root.right;
        }
        return list;
    }

    public static List<Integer> preOrderTraverseIter02(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (root != null) {
            list.add(root.val);
            if (root.right != null)
                stack.push(root.right);
            root = root.left;
            if (root == null && !stack.isEmpty())
                root = stack.pop();
        }
        return list;
    }

    public static List<Integer> preOrderTraverseIter03(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null)
            return list;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            root = stack.pop();
            list.add(root.val);
            if (root.right != null)
                stack.push(root.right);
            if (root.left != null)
                stack.push(root.left);
        }
        return list;
    }

    public static List<Integer> preOrderMorrisTraverse(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        TreeNode cur = root;
        TreeNode predecessor = null;
        while (cur != null) {
            if (cur.left == null) {
                list.add(cur.val);
                cur = cur.right;
            } else {
                predecessor = cur.left;
                while (predecessor.right != null && predecessor.right != cur) {
                    predecessor = predecessor.right;
                }
                if (predecessor.right == cur) {
                    predecessor.right = null;
                    cur = cur.right;
                } else {
                    list.add(cur.val);
                    predecessor.right = cur;
                    cur = cur.left;
                }
            }
        }
        return list;
    }

    public static void main(String[] args) {
        PreOrder obj = new PreOrder();
        TreeNode root = obj.creatTree();
        System.out.println("Recursive preorder traverse: ");
        System.out.println(preOrderTraverseRec(root));
        System.out.println("Iterative preorder traverse 01: ");
        System.out.println(preOrderTraverseIter01(root));
        System.out.println("Iterative preorder traverse 02: ");
        System.out.println(preOrderTraverseIter02(root));
        System.out.println("Iterative preorder traverse 03: ");
        System.out.println(preOrderTraverseIter03(root));
        System.out.println("Morris preorder traverse: ");
        System.out.println(preOrderMorrisTraverse(root));
    }
}