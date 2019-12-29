package chapter22;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @author Weiduo
 * @date 2019/8/31 - 7:47 PM
 */
public class TarjanSCC {

    private int n;
    private List<List<Integer>> adj;
    private List<List<Integer>> res;

    private boolean[] instack;
    private Stack<Integer> stack;
    private int[] dfn;
    private int[] low;

    private int time;

    public TarjanSCC(List<List<Integer>> adj, int n) {
        this.adj = adj;
        this.n = n;
        this.instack = new boolean[n];
        this.stack = new Stack<>();
        dfn = new int[n];
        low = new int[n];

        Arrays.fill(dfn, -1);
        Arrays.fill(low, -1);

        res = new ArrayList<>();
    }

    public List<List<Integer>> run() {
        for (int i = 0; i < n; i++) {
            if (dfn[i] == -1)
                tarjan(i);
        }
        return res;
    }

    private void tarjan(int cur) {
        dfn[cur] = low[cur] = time++;
        instack[cur] = true;
        stack.push(cur);

        for(Integer neighbor : adj.get(cur)) {
            if(dfn[neighbor] == -1) {
                tarjan(neighbor);
                low[cur] = Math.min(low[cur], low[neighbor]);
            }else if(instack[neighbor]) {
                low[cur] = Math.min(low[cur], dfn[neighbor]);
            }
        }

        if(low[cur] == dfn[cur]) {
            List<Integer> temp = new ArrayList<>();
            int j = -1;
            while (cur != j) {
                j = stack.pop();
                instack[j] = false;
                temp.add(j);
            }
            res.add(temp);
        }
    }


    public static void main(String[] args) {
        int n = 6;
        List<List<Integer>> adj = new ArrayList<>();
        for(int i = 0; i < n; i++) adj.add(new ArrayList<>());

        adj.get(0).add(1);
        adj.get(0).add(2);
        adj.get(1).add(3);
        adj.get(2).add(3);
        adj.get(2).add(4);
        adj.get(3).add(0);
        adj.get(3).add(5);
        adj.get(4).add(5);

        TarjanSCC t = new TarjanSCC(adj, n);

        List<List<Integer>> result = t.run();

        for (int i = 0; i < result.size(); i++) {
            for (int j = 0; j < result.get(i).size(); j++) {
                System.out.print(result.get(i).get(j) + " ");
            }
            System.out.println();
        }

    }




}
