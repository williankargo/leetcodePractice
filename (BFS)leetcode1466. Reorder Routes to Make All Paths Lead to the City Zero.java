import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

class Solution1 {

    // BFS
    // TC: O(N)
    // SC: O(N)
    public int minReorder(int n, int[][] connections) {

        Map<Integer, List<Integer>> neighbors = new HashMap<>();
        Queue<Integer> queue = new ArrayDeque<>();
        Set<Integer> isVisited = new HashSet<>();
        Set<List<Integer>> path = new HashSet<>();
        int changes = 0;

        // 0. preprocessing data
        for (int i = 0; i < n; i++) {
            neighbors.put(i, new ArrayList<>());
        }
        for (int[] connection : connections) {
            neighbors.get(connection[0]).add(connection[1]);
            neighbors.get(connection[1]).add(connection[0]);
            path.add(Arrays.asList(connection[0], connection[1]));
        }

        // BFS
        queue.offer(0);
        isVisited.add(0);
        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (Integer neighbor : neighbors.get(node)) {

                if (isVisited.contains(neighbor)) {
                    continue;
                }
                if (!path.contains(Arrays.asList(neighbor, node))) { // can't use "new int[]{}" to compare!!!
                    changes++;
                }
                queue.offer(neighbor);
                isVisited.add(neighbor);
            }
        }

        return changes;
    }
}

// DFS
class Solution2 {

    // DFS
    // TC: O(N)
    // SC: O(N)
    public int minReorder(int n, int[][] connections) {

        Map<Integer, List<Integer>> neighbors = new HashMap<>();
        Set<List<Integer>> path = new HashSet<>();
        int changes = 0;

        Set<Integer> isVisited = new HashSet<>();

        // 0. preprocessing data
        for (int i = 0; i < n; i++) {
            neighbors.put(i, new ArrayList<>());
        }
        for (int[] connection : connections) {
            neighbors.get(connection[0]).add(connection[1]);
            neighbors.get(connection[1]).add(connection[0]);
            path.add(Arrays.asList(connection[0], connection[1]));
        }

        isVisited.add(0);
        return dfs(0, isVisited, neighbors, path);
    }

    private int dfs(int from, Set<Integer> isVisited, Map<Integer, List<Integer>> neighbors, Set<List<Integer>> path) {

        int changes = 0;

        for (Integer neighbor : neighbors.get(from)) {
            if (isVisited.contains(neighbor)) {
                continue;
            }
            if (!path.contains(Arrays.asList(neighbor, from))) {
                changes++;
            }
            isVisited.add(neighbor);
            changes += dfs(neighbor, isVisited, neighbors, path);
        }

        return changes;
    }
}