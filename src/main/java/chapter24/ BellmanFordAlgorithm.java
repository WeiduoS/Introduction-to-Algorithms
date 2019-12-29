package chapter24;

import java.util.Arrays;

class BellmanFordAlgorithm {
    public static void BellmanFord(int[][] graph, int n, int start) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[start] = 0;
        for (int i = 1; i < n; i++) {
            for (int[] g : graph) {
                int from = g[0], to = g[1], c = g[2];
                if (dp[from] != Integer.MAX_VALUE && dp[to] > dp[from] + c) {
                    dp[to] = dp[from] + c;
                }
            }
        }
        System.out.println(Arrays.toString(dp));
    }

    public static void main(String[] args) {
        int[][] graph = new int[][] { { 2, 1, 1 }, { 2, 3, 1 }, { 3, 4, 1 } };
        BellmanFord(graph, 4, 2);
    }
}