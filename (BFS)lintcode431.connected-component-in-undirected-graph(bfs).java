import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

/**
 * Definition for Undirected graph.
 * class UndirectedGraphNode {
 * int label;
 * List<UndirectedGraphNode> neighbors;
 * UndirectedGraphNode(int x) {
 * label = x;
 * neighbors = new ArrayList<UndirectedGraphNode>();
 * }
 * }
 */

class Solution {

    // Time: O(N + M), M is the total edges
    // Space: O(N), N is the total nodes
    public List<List<Integer>> connectedSet(List<UndirectedGraphNode> nodes) {

        List<List<Integer>> result = new ArrayList<>();
        Set<Integer> route = new HashSet<>();

        if (nodes == null || nodes.size() == 0) {
            return result;
        }

        for (UndirectedGraphNode nodeObject : nodes) {
            if (!route.contains(nodeObject.label)) {
                bfs(nodeObject, route, result);
            }
        }

        return result;
    }

    private void bfs(UndirectedGraphNode nodeObject, Set<Integer> route, List<List<Integer>> result) {

        Queue<UndirectedGraphNode> queue = new ArrayDeque<>();
        Set<Integer> set = new TreeSet<>();

        queue.offer(nodeObject);
        set.add(nodeObject.label);
        route.add(nodeObject.label);

        while (!queue.isEmpty()) {
            UndirectedGraphNode node = queue.poll();
            for (UndirectedGraphNode neighbor : node.neighbors) {
                if (!set.contains(neighbor.label)) {
                    queue.offer(neighbor);
                    set.add(neighbor.label);
                    route.add(neighbor.label);
                }
            }
        }

        result.add(new ArrayList<Integer>(set));
    }

}