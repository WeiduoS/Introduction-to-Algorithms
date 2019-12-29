package chapter24;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @author Weiduo
 * @date 2019/9/7 - 2 AM
 */
public class DijkstraAlgorithm {

    public static void Dijkstra(int[][] graph, int N, int start) {
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        for (int[] g : graph) {
            int from = g[0], to = g[1], cost = g[2];
            map.computeIfAbsent(from, f -> new HashMap<>()).put(to, cost);
        }

        Set<Integer> set = new HashSet<>();

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[1] - b[1]));
        pq.offer(new int[] { start, 0 });

        int[] dists = new int[N + 1];
        Arrays.fill(dists, Integer.MAX_VALUE);
        dists[start] = 0;

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            if (set.contains(cur[0]))
                continue;
            set.add(cur[0]);

            Map<Integer, Integer> neighbor = map.getOrDefault(cur[0], new HashMap<>());

            for (int next : neighbor.keySet()) {
                if (set.contains(next))
                    continue;
                pq.offer(new int[] { next, cur[1] + neighbor.get(next) });
                dists[next] = Math.min(dists[next], cur[1] + neighbor.get(next));
            }
        }

        System.out.println(Arrays.toString(dists));

    }

    public static void main(String[] args) {
        int[][] graph = new int[][] { { 2, 1, 1 }, { 2, 3, 1 }, { 3, 4, 1 } };
        Dijkstra(graph, 4, 2);

    }
}
