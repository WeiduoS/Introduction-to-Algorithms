package chapter21;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Weiduo
 * @date 2019/8/31 - 4:21 PM
 */
public class UnionFindSet {

    class Node {
        int data, rank;
        Node parent;

        Node(int data, int rank) {
            this.data = data;
            this.rank = rank;
            this.parent = this;
        }

        @Override
        public String toString() {
            return "Node{" +
                "data=" + data +
                ", rank=" + rank +
                '}';
        }
    }

    private Map<Integer, Node> map = new HashMap<>();

    private void makeSet(int[] nums) {
        for(int num : nums) {
            map.put(num, new Node(num, 1));
        }
    }

    private void union(int x, int y) {
        Node p1 = find(x);
        Node p2 = find(y);

        if(p1 == p2) return;

        if(p1.rank > p2.rank) {
            p2.parent = p1.parent;
            p1.rank += p2.rank;
        }else if(p1.rank < p2.rank) {
            p1.parent = p2.parent;
            p2.rank += p1.rank;
        }else{
            p2.parent = p1.parent;
            p1.rank += p2.rank;
        }

    }

    private Node find(int x) {
        if(map.get(x).parent.data != x) map.get(x).parent = find(map.get(x).parent.data);
        return map.get(x).parent;
    }

    public static void main(String[] args) {
        UnionFindSet uf = new UnionFindSet();

        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7};

        uf.makeSet(nums);
        uf.union(1, 2);
        uf.union(2, 3);
        uf.union(4, 5);
        uf.union(6, 7);
        uf.union(5, 6);
        uf.union(3, 7);

        System.out.println(uf.find(1));
        System.out.println(uf.find(2));
        System.out.println(uf.find(3));
        System.out.println(uf.find(4));
        System.out.println(uf.find(5));
        System.out.println(uf.find(6));
        System.out.println(uf.find(7));
    }


}
