import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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

    // Time: O(N^2)*(log(N))
    // Space: O(N^2)
    public List<List<Integer>> connectedSet(List<UndirectedGraphNode> nodes) {

        List<List<Integer>> result = new ArrayList<>();
        if (nodes == null || nodes.size() == 0) {
            return result;
        }

        UnionFind uf = new UnionFind(nodes);

        // merge
        for (UndirectedGraphNode nodeObject : nodes) {
            for (UndirectedGraphNode neighborObject : nodeObject.neighbors) {
                int neighbor = neighborObject.label;
                int node = nodeObject.label;
                uf.merge(neighbor, node);
            }
        }

        Set<Integer> hasBeen = new HashSet<>();
        for (UndirectedGraphNode nodeObject : nodes) {
            int node = uf.find(nodeObject.label);
            if (hasBeen.contains(node)) {
                continue;
            }
            hasBeen.add(node);
            Set<Integer> subList = uf.family.get(node);
            result.add(new ArrayList(subList));
        }

        return result;
    }
}

class UnionFind {

    Map<Integer, Integer> father;
    Map<Integer, Set<Integer>> family;

    UnionFind(List<UndirectedGraphNode> nodes) {

        int length = nodes.size();
        father = new HashMap<>();
        family = new HashMap<>(); // Map記得要實例化！！！！！

        for (UndirectedGraphNode node : nodes) {
            father.put(node.label, node.label);
            family.put(node.label, new TreeSet<>());
            family.get(node.label).add(node.label);
        }
    }

    int find(int node) {

        int root = node;

        // find the stem root
        while (root != father.get(root)) {
            root = father.get(root);
        }

        // compress the tree
        while (node != father.get(node)) {
            int temp = node;
            father.put(temp, root);
            node = father.get(temp);
        }

        return root;
    }

    void merge(int node, int neighbor) {

        int nodeRoot = find(node);
        int neighborRoot = find(neighbor);

        if (nodeRoot != neighborRoot) {
            father.put(neighborRoot, nodeRoot);
            family.get(nodeRoot).addAll(family.get(neighborRoot)); // Set: addAll()
        }
    }

}
