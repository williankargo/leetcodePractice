import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

class Solution {

    // Time: 建图时每条边都会访问 1 次，搜索时每个点都会被询问1次，时间复杂度为O(max(n, m))
    // Space: O(N^2) 因為存graph
    public boolean validTree(int n, int[][] edges) {

        // 想Tree的概念
        // 1. 不可以有環，node# == edge#
        if (edges.length != (n - 1)) {
            return false;
        }

        // 2. 不可以有孤立的edge，要遍歷到所有的點
        // 要遍歷Tree，所以想到BFS
        return bfs(n, edges);
    }

    private boolean bfs(int n, int[][] edges) {

        Map<Integer, Set<Integer>> graph = initGraph(edges, n);
        Queue<Integer> queue = new ArrayDeque<>();
        Set<Integer> set = new HashSet<>();

        queue.offer(0);
        set.add(0);
        while (!queue.isEmpty()) {
            Integer node = queue.poll();
            for (Integer neighbor : graph.get(node)) {
                if (set.contains(neighbor)) {
                    continue;
                }
                queue.offer(neighbor);
                set.add(neighbor);
            }
        }

        return set.size() == n;
    }

    private Map<Integer, Set<Integer>> initGraph(int[][] edges, int n) {

        Map<Integer, Set<Integer>> graph = new HashMap<>();

        // 初始化
        for (int i = 0; i < n; i++) {
            graph.put(i, new HashSet<Integer>());
        }

        for (int i = 0; i < edges.length; i++) {
            graph.get(edges[i][0]).add(edges[i][1]);
            graph.get(edges[i][1]).add(edges[i][0]); // 因為不是有向樹，所以兩邊都要加
        }

        return graph;
    }
}