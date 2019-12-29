package chapter22;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author Weiduo
 * @date 2019/8/31 - 6:54 PM
 */
public class KosarajuSCC {
    private  List<List<Integer>> adj = new ArrayList<>();

    private void findSCCs() {
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[adj.size()];

        for(int i = 0; i < adj.size(); i++) {
            if(visited[i] == false) dfs1(i, visited, stack);
        }

        adj = reverseGraph();

        visited = new boolean[adj.size()];

        while(!stack.isEmpty()) {

            int cur = stack.pop();

            if(visited[cur] == false) {
                dfs2(cur, visited);
                System.out.println("-------------");
            }

        }

    }

    private void dfs1(int cur, boolean[] visited, Stack<Integer> stack) {
        visited[cur] = true;

        for(Integer neighbor : adj.get(cur)) {
            if(visited[neighbor] == false) {
                dfs1(neighbor, visited, stack);
            }
        }

        stack.push(cur);
    }

    private void dfs2(int cur, boolean[] visited) {
        visited[cur] = true;

        System.out.println("cur: " + cur);

        for(Integer neighbor : adj.get(cur)) {
            if(visited[neighbor] == false) {
                dfs2(neighbor, visited);
            }
        }


    }

    private List<List<Integer>> reverseGraph() {
        List<List<Integer>> res = new ArrayList<>();
        for(int i = 0; i < adj.size(); i++) res.add(new ArrayList<>());

        for(int i = 0; i < adj.size(); i++) {
            for(Integer neighbor : adj.get(i)) {
                res.get(neighbor).add(i);
            }
        }

        return res;
    }


    public static void main(String[] args) {
        KosarajuSCC obj = new KosarajuSCC();

        for(int i = 0; i < 5; i++) {
            obj.adj.add(new ArrayList<>());
        }

        obj.adj.get(1).add(0);
        obj.adj.get(0).add(2);
        obj.adj.get(2).add(1);
        obj.adj.get(0).add(3);

        obj.findSCCs();

    }
}
