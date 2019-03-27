package chapter12.BST_Successor;

import java.util.ArrayList;
import java.util.List;

public class InOrderSuccessor {
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

    public static List<Integer> inOrderTraverse(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        helper(root, res);
        return res;
    }

    public static void helper(TreeNode root, List<Integer> list) {
        if (root == null)
            return;
        helper(root.left, list);
        list.add(root.val);
        helper(root.right, list);
    }

    public static TreeNode findSuccessorIter(TreeNode root, int target) {
        TreeNode successor = null;
        while (root != null) {
            if (root.val > target) {
                successor = root;
                root = root.left;
            } else {
                root = root.right;
            }
        }
        return successor;
    }

    public static TreeNode findSuccessorRecur(TreeNode root, int target) {
        if (root == null)
            return null;
        if (root.val <= target)
            return findSuccessorRecur(root.right, target);
        TreeNode successor = findSuccessorRecur(root.left, target);
        return successor == null ? root : successor;
    }

    public static void main(String[] args) {
        InOrderSuccessor obj = new InOrderSuccessor();
        TreeNode root = obj.creatTree();
        System.out.println("Tree inorder traverse: ");
        System.out.println(inOrderTraverse(root));
        TreeNode t1 = findSuccessorIter(root, 20);
        System.out.println("20's successor(Iterative): ");
        System.out.println(t1.val);
        TreeNode t2 = findSuccessorRecur(root, 50);
        System.out.println("50's successor(Recursive): ");
        System.out.println(t2.val);
    }
}