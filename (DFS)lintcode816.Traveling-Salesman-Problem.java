import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {

    // Recursion: BT
    // DFS + prunning
    public int minCost(int n, int[][] roads) {

        Set<Integer> visited = new HashSet<>();
        List<Integer> path = new ArrayList<>();
        Result result = new Result(Integer.MAX_VALUE >> 4); // 右移法
        int[][] graph = constructGraph(roads, n);

        // 題目說要從1出發
        visited.add(1);
        path.add(1);

        dfs(1, 0, result, graph, path, visited, n);

        return result.minResult;
    }

    class Result {
        int minResult;

        Result(int minResult) {
            this.minResult = minResult;
        }
    }

    private void dfs(int city, int cost, Result result, int[][] graph, List<Integer> path, Set<Integer> visited,
            int n) {

        // exit
        if (path.size() == n) {
            result.minResult = Math.min(result.minResult, cost);
            return; // end of recurrsion
        }

        // for
        for (int i = 1; i < graph[city].length; i++) {

            if (visited.contains(i)) {
                continue;
            }
            if (haveBetterPath(i, path, graph)) {
                continue;
            }

            visited.add(i);
            path.add(i);

            dfs(i, cost + graph[city][i], result, graph, path, visited, n);

            visited.remove(i);
            path.remove(path.size() - 1);
        }
    }

    // OOO...--(i-1)--(i)--(last)--(city)
    private boolean haveBetterPath(int city, List<Integer> path, int[][] graph) {

        int lastSpot = path.get(path.size() - 1);

        for (int i = 1; i < path.size(); i++) {
            int spotA = path.get(i - 1);
            int spotB = path.get(i);

            if (graph[spotA][spotB] + graph[lastSpot][city] > graph[spotA][lastSpot] + graph[spotB][city]) {
                return true;
            }
        }

        return false;
    }

    // 構建兩點路徑最小的圖
    private int[][] constructGraph(int[][] roads, int n) {

        int[][] graph = new int[n + 1][n + 1]; // 因為從1開始
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                graph[i][j] = Integer.MAX_VALUE >> 4; // 右移四位防爆掉
            }
        }

        int roadLength = roads.length;
        for (int i = 0; i < roadLength; i++) {
            int spotA = roads[i][0];
            int spotB = roads[i][1];
            int sum = roads[i][2];
            graph[spotA][spotB] = Math.min(graph[spotA][spotB], sum);
            graph[spotB][spotA] = Math.min(graph[spotB][spotA], sum);
        }

        return graph;
    }

}