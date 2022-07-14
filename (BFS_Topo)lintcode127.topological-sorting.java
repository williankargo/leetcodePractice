import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

class DirectedGraphNode {
    int label;
    List<DirectedGraphNode> neighbors;

    DirectedGraphNode(int x) {
        label = x;
        neighbors = new ArrayList<DirectedGraphNode>(); // 會到哪些點
    }
}

class Solution {
    /**
     * @param graph: A list of Directed graph node
     * @return: Any topological order for the given graph.
     */
    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        // write your code here

        // 1. 紀錄每個點的入度
        Map<DirectedGraphNode, Integer> indegree = countIndegree(graph);

        // 2. 將入度是0的點塞到queue中
        Queue<DirectedGraphNode> queue = new ArrayDeque<>();
        for (DirectedGraphNode node : graph) {
            if (!indegree.containsKey(node)) { // map: containsKey(key)
                queue.offer(node);
            }
        }
    
        // 3. 逐一檢視鄰居
        ArrayList<DirectedGraphNode> result = new ArrayList<DirectedGraphNode>();
        while (!queue.isEmpty()) {
            DirectedGraphNode node = queue.poll();
            result.add(node);
            for (DirectedGraphNode neighbor : node.neighbors) {
                indegree.put(neighbor, indegree.get(neighbor) - 1);

                // 4. indegree == 0 add to queue
                if (indegree.get(neighbor) == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        
        return result;
    }
    
    // 如果不在裡就代表入度是0
    private Map<DirectedGraphNode, Integer> countIndegree(ArrayList<DirectedGraphNode> graph) {
        
        Map<DirectedGraphNode, Integer> map = new HashMap<>();

        for (DirectedGraphNode node : graph) {
            for (DirectedGraphNode neighbor : node.neighbors) {
                map.put(neighbor, map.getOrDefault(neighbor, 0) + 1); // map: getOrDefault()
            }
        }

        return map;
    }


}