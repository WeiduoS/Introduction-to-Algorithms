package chapter12.BST_Traverse;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class InOrder {
    public class TreeNode {
        int val;
        TreeNode left, right;

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

    public static List<Integer> inOrderTraverseRec(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        helper(root, list);
        return list;
    }

    private static void helper(TreeNode root, List<Integer> list) {
        if (root == null)
            return;
        helper(root.left, list);
        list.add(root.val);
        helper(root.right, list);
    }

    public static List<Integer> inOrderTraverseIter01(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            list.add(root.val);
            root = root.right;
        }
        return list;
    }

    public static List<Integer> inOrderTraverseIter02(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null)
            return list;
        Stack<TreeNode> stack = new Stack<>();
        Set<TreeNode> set = new HashSet<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.peek();
            if (node.left != null && !set.contains(node)) {
                set.add(node);
                stack.push(node.left);
            } else {
                list.add(node.val);
                stack.pop();
                if (node.right != null)
                    stack.push(node.right);
            }
        }
        return list;
    }

    public static List<Integer> inOrderMorrisTraverse(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        TreeNode cur = root;
        TreeNode predecessor = null;
        while (cur != null) {
            if (cur.left == null) {
                list.add(cur.val);
                cur = cur.right;
            } else {
                predecessor = cur.left;
                while (predecessor.right != null)
                    predecessor = predecessor.right;
                predecessor.right = cur;
                TreeNode temp = cur.left;
                cur.left = null;
                cur = temp;
            }
        }
        return list;
    }

    public static void main(String[] args) {
        InOrder obj = new InOrder();
        TreeNode root = obj.creatTree();
        System.out.println("Recursive inorder traverse: ");
        System.out.println(inOrderTraverseRec(root));
        System.out.println("Iterative inorder traverse 01: ");
        System.out.println(inOrderTraverseIter01(root));
        System.out.println("Iterative inorder traverse 02: ");
        System.out.println(inOrderTraverseIter02(root));
        System.out.println("Morris inorder traverse: ");
        System.out.println(inOrderMorrisTraverse(root));
    }

}