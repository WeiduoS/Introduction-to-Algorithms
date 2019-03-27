package chapter12.BST_Traverse;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PostOrder {
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

    public static List<Integer> postOrderTraverseRec(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        helper(root, list);
        return list;
    }

    private static void helper(TreeNode root, List<Integer> list) {
        if (root == null)
            return;
        helper(root.left, list);
        helper(root.right, list);
        list.add(root.val);
    }

    public static List<Integer> postOrderTraverseIter01(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                list.add(0, root.val);
                stack.push(root);
                root = root.right;
            }
            root = stack.pop();
            root = root.left;
        }
        return list;
    }

    public static List<Integer> postOrderTraverseIter02(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (root != null) {
            list.add(0, root.val);
            if (root.left != null) {
                stack.push(root.left);
            }
            root = root.right;
            if (root == null && !stack.isEmpty()) {
                root = stack.pop();
            }
        }
        return list;
    }

    public static List<Integer> postOrderTraverseIter03(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null)
            return list;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            list.add(0, node.val);
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }
        return list;
    }

    public static List<Integer> postOrderMorrisTraverse(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        TreeNode cur = root;
        TreeNode successor = null;
        while (cur != null) {
            if (cur.right == null) {
                list.add(0, cur.val);
                cur = cur.left;
            } else {
                successor = cur.right;
                while (successor.left != null && successor.left != cur) {
                    successor = successor.left;
                }
                if (successor.left == cur) {
                    successor.left = null;
                    cur = cur.left;
                } else {
                    list.add(0, cur.val);
                    successor.left = cur;
                    cur = cur.right;
                }
            }
        }
        return list;
    }

    public static void main(String[] args) {
        PostOrder obj = new PostOrder();
        TreeNode root = obj.creatTree();
        System.out.println("Recursive postorder traverse: ");
        System.out.println(postOrderTraverseRec(root));
        System.out.println("Iterative postorder traverse 01: ");
        System.out.println(postOrderTraverseIter01(root));
        System.out.println("Iterative postorder traverse 02: ");
        System.out.println(postOrderTraverseIter02(root));
        System.out.println("Iterative postorder traverse 03: ");
        System.out.println(postOrderTraverseIter03(root));
        System.out.println("Morris postorder traverse: ");
        System.out.println(postOrderMorrisTraverse(root));
    }
}