import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

class UndirectedGraphNode {
    int label;
    ArrayList<UndirectedGraphNode> neighbors;

    UndirectedGraphNode(int x) {
        label = x;
        neighbors = new ArrayList<UndirectedGraphNode>();
    }
};

class Solution {

    // Time: O(N + E) N為點的數量 E為邊的數量
    // Space: O(N) N為點的數量
    public UndirectedGraphNode searchNode(ArrayList<UndirectedGraphNode> graph,
            Map<UndirectedGraphNode, Integer> values,
            UndirectedGraphNode node,
            int target) {

        // graph 就是打醬油用的
        if (graph == null || values == null || node == null) {
            return null;
        }

        Queue<UndirectedGraphNode> queue = new ArrayDeque<>();
        Set<UndirectedGraphNode> set = new HashSet<>();

        queue.offer(node);
        set.add(node); // Set .add()

        while (!queue.isEmpty()) {

            UndirectedGraphNode currentNode = queue.poll(); // Queue .poll()
            if (values.get(currentNode) == target) {
                return currentNode;
            }

            for (UndirectedGraphNode neighbor : currentNode.neighbors) {
                if (!set.contains(neighbor)) {
                    queue.offer(neighbor);
                    set.add(neighbor);
                }
            }
        }

        return null;
    }
}