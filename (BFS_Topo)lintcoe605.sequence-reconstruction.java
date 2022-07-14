
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

// https://www.lintcode.com/problem/605/

class Solution {

    public static void main(String[] args) {
        int[] org = new int[] { 1 };
        int[][] seqs = new int[][] { { 1 } };
        System.out.print(new Solution().sequenceReconstruction(org, seqs));
    }

    public boolean sequenceReconstruction(int[] org, int[][] seqs) {
        // write your code here

        Map<Integer, Set<Integer>> graph = buildGraph(seqs);
        List<Integer> topo = getTopoOrder(graph); // List: .size()

        if (topo == null || org.length != topo.size()) { // Array: .length
            return false;
        }

        for (int i = 0; i < org.length; i++) {
            if (topo.get(i) != org[i]) {
                return false;
            }
        }

        return true;
    }

    // 1. build the graph
    private Map<Integer, Set<Integer>> buildGraph(int[][] seqs) {

        Map<Integer, Set<Integer>> graph = new HashMap<>();

        for (int[] seq : seqs) {
            for (int i = 0; i < seq.length; i++) {
                if (!graph.containsKey(seq[i])) { // Map: containsKey(key)
                    graph.put(seq[i], new HashSet<>());
                }
            }
        }

        for (int[] seq : seqs) { // seq[] 不一定兩位
            for (int i = 1; i < seq.length; i++) { // 不可以從0開始，要考慮index可能會爆掉
                graph.get(seq[i - 1]).add(seq[i]); // 0 1
            }
        }

        return graph;
    }

    // 1. count the indegree
    private Map<Integer, Integer> getIndegrees2(Map<Integer, Set<Integer>> graph) {

        Map<Integer, Integer> indegree = new HashMap<>();

        for (Integer key : graph.keySet()) {
            for (Integer node : graph.get(key)) { // 進不來，所以紀錄不到1 的 indegree = 0
                indegree.put(node, indegree.getOrDefault(node, 0) + 1); // getOrDefault()雖然精簡但是可能會發生錯誤
            }
        }

        System.out.println(indegree); // {}
        return indegree;
    }

    // 1. count the indegree
    private Map<Integer, Integer> getIndegrees(Map<Integer, Set<Integer>> graph) {
        System.out.println(graph); // {1=[]}
        Map<Integer, Integer> indegrees = new HashMap<>();
        for (Integer node : graph.keySet()) {
            indegrees.put(node, 0);
        }
        for (Integer node : graph.keySet()) {
            for (Integer neighbor : graph.get(node)) {
                indegrees.put(neighbor, indegrees.get(neighbor) + 1);
            }
        }
        System.out.println(indegrees); // {1=0}
        return indegrees;
    }

    private List<Integer> getTopoOrder(Map<Integer, Set<Integer>> graph) {

        // 2. indegree == 0 => queue
        Map<Integer, Integer> indegree = getIndegrees(graph);
        Queue<Integer> queue = new ArrayDeque();
        List<Integer> topo = new ArrayList();

        for (Integer key : indegree.keySet()) {
            if (indegree.get(key) == 0) {
                queue.offer(key);
                topo.add(key);
            }
        }

        // 3. neighbors
        while (!queue.isEmpty()) {
            // 代表有非唯一解
            if (queue.size() > 1) {
                return null;
            }

            Integer node = queue.poll();
            for (Integer neighbor : graph.get(node)) {
                indegree.put(neighbor, indegree.get(neighbor) - 1); // Map: put(key, value)
                // 4. add new node
                if (indegree.get(neighbor) == 0) {
                    queue.offer(neighbor);
                    topo.add(neighbor);
                }
            }
        }

        if (topo.size() != graph.size()) { // Map: .size()
            // 代表未能走完全部的點
            return null;
        }

        return topo;
    }
}