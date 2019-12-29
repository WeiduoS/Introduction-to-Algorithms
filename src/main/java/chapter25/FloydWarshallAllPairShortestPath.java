package chapter25;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author Weiduo
 * @date 2019/9/6 - 11:56 PM
 */
public class FloydWarshallAllPairShortestPath {

    private static final int INF = 1000_000;

    public int[][] floydWarshall(int[][] graph) {

        int n = graph.length;
        int[][] distances = new int[n][n];
        int[][] paths = new int[n][n];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                distances[i][j] = graph[i][j];
                if(graph[i][j] != INF && i != j) {
                    paths[i][j] = i;
                }else{
                    paths[i][j] = -1;
                }
            }
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if(distances[i][k] == INF || distances[k][j] == INF) continue;

                    if (distances[i][j] > distances[i][k] + distances[k][j]) {
                        distances[i][j] = distances[i][k] + distances[k][j];
                        paths[i][j] = paths[k][j];
                    }

                }
            }
        }

        printPath(paths, 3, 2);

        return distances;
    }


    public void printPath(int[][] paths, int start, int end) {
        Stack<Integer> stack = new Stack<>();

        while(paths[start][end] != -1) {
            stack.push(end);
            end = paths[start][end];
        }
        if(end == start) stack.push(end);
        while(!stack.isEmpty()) System.out.print(stack.pop() + " -> ");
        System.out.println("end");
    }


    public static void main(String args[]) {
        int[][] graph = { { 0, 3, 6, 15 }, { INF, 0, -2, INF }, { INF, INF, 0, 2 }, { 1, INF, INF, 0 } };

        FloydWarshallAllPairShortestPath shortestPath = new FloydWarshallAllPairShortestPath();

        int[][] distance = shortestPath.floydWarshall(graph);

        for(int[] row : distance) System.out.println(Arrays.toString(row));
    }
}
