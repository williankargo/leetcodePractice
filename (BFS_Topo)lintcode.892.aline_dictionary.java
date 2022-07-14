import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

class Solution {
    /**
     * @param words: a list of words
     * @return: a string which is correct order
     */
    public String alienOrder(String[] words) {

        // build the graph
        Map<Character, Set<Character>> graph = buildGraph(words);
        if (graph == null) {
            return "";
        }

        return topo(graph);
    }

    private Map<Character, Set<Character>> buildGraph(String[] words) {

        Map<Character, Set<Character>> graph = new HashMap<>();

        // 初始化，生成所有點，每個點暫時為空
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words[i].length(); j++) { // String => word.length()
                char item = words[i].charAt(j);
                if (!graph.containsKey(item)) { // Map => containsKey(key)
                    graph.put(item, new HashSet<>());
                }
            }
        }

        for (int i = 0; i < words.length - 1; i++) {
            int index = 0;
            while (index < words[i].length() & index < words[i + 1].length()) {
                if (words[i].charAt(index) != words[i + 1].charAt(index)) { // String => word.charAt(i)
                    graph.get(words[i].charAt(index)).add(words[i + 1].charAt(index));
                    break; // 每兩組詞只能判斷前後一次
                }
                index++;
            }

            // 如果a是b的前缀且b出现在a之前，那么这个顺序是无效的。
            // {abc} {ab}
            if (index == Math.min(words[i].length(), words[i + 1].length())) { // index已經探底到其中一個
                if (words[i].length() > words[i + 1].length()) {
                    return null;
                }
            }

        }

        return graph;
    }

    private String topo(Map<Character, Set<Character>> graph) {

        // 0. indegree
        Map<Character, Integer> indegree = countIndegree(graph);

        // 可以按照字母排序的queue
        Queue<Character> queue = new PriorityQueue<>();
        // 1. indegree = 0
        for (Character item : indegree.keySet()) {
            if (indegree.get(item) == 0) {
                queue.offer(item);
            }
        }

        StringBuilder sb = new StringBuilder(); // StringBuilder => word.length();

        // 2. neighbors
        while (!queue.isEmpty()) {
            Character item = queue.poll();
            sb.append(item);
            for (Character neighbor : graph.get(item)) {
                indegree.put(neighbor, indegree.get(neighbor) - 1);
                // 3. add new root
                if (indegree.get(neighbor) == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        return sb.length() == graph.size() ? sb.toString() : "";
    }

    private Map<Character, Integer> countIndegree(Map<Character, Set<Character>> graph) {

        // 初始化
        Map<Character, Integer> indegree = new HashMap<>();
        for (Character item : graph.keySet()) {
            indegree.put(item, 0);
        }

        for (Character item : graph.keySet()) {
            for (Character innerItem : graph.get(item)) {
                indegree.put(innerItem, indegree.get(innerItem) + 1);
            }
        }

        return indegree;
    }

}