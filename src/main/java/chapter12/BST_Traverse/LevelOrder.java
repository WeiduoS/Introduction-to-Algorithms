package chapter12.BST_Traverse;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelOrder {
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

    public static List<List<Integer>> levelOrderRec(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        helper(root, 0, list);
        return list;
    }

    private static void helper(TreeNode root, int level, List<List<Integer>> list) {
        if (root == null)
            return;
        if (level == list.size())
            list.add(new ArrayList<Integer>());
        list.get(level).add(root.val);
        helper(root.left, level + 1, list);
        helper(root.right, level + 1, list);
    }

    public static List<List<Integer>> levelOrderIter(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null)
            return list;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> temp = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                temp.add(cur.val);
                if (cur.left != null)
                    queue.offer(cur.left);
                if (cur.right != null)
                    queue.offer(cur.right);

            }
            list.add(new ArrayList<>(temp));
        }
        return list;
    }

    public static void main(String[] args) {
        LevelOrder obj = new LevelOrder();
        TreeNode root = obj.creatTree();
        System.out.println("level order traverse Recursive: ");
        List<List<Integer>> list01 = levelOrderRec(root);
        for (List<Integer> row : list01)
            System.out.println(row);
        System.out.println("level order traverse Iterative: ");
        List<List<Integer>> list02 = levelOrderIter(root);
        for (List<Integer> row : list02)
            System.out.println(row);
    }
}