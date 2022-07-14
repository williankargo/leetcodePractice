import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

class Solution {

    // BFS(找最短路徑) + DFS(找所有結果)
    // Time: O(V+E) *bfs找neighbor時會產生E O(size(dict)) *dfs最壞情況遍歷全字典
    // Space: O(size(dict) * k) ＊註
    public List<List<String>> findLadders(String start, String end, Set<String> dict) {

        dict.add(start);
        dict.add(end);

        // start走到某點的最短路徑是多少
        Map<String, Integer> distance = new HashMap<>();

        // 最短路徑下，下一步要走到哪
        Map<String, List<String>> route = new HashMap<>(); // ＊註 Space: O(size(dict) * k)

        bfs(start, end, distance, route, dict);

        List<List<String>> result = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        List<String> temp = new ArrayList<>();
        temp.add(start);
        dfs(start, visited, temp, result, route, distance.get(end), end);

        return result;
    }

    private void bfs(String start, String end, Map<String, Integer> distance, Map<String, List<String>> route,
            Set<String> dict) {

        // 初始化route
        for (String word : dict) {
            route.put(word, new ArrayList<String>());
        }

        Queue<String> queue = new ArrayDeque<>();
        queue.offer(start);
        distance.put(start, 0);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            for (String neighbor : findNeighbor(current, dict)) {

                if (!distance.containsKey(neighbor) || distance.get(neighbor) == distance.get(current) + 1) {
                    // 還沒有被訪問過，或被訪問過但是current下一步就是neighbor(不然如果用純bfs找不到兩個current相交童neighbor)
                    route.get(current).add(neighbor);
                }

                if (!distance.containsKey(neighbor)) { // 此neighbor有沒有被bfs visit過
                    distance.put(neighbor, distance.getOrDefault(current, 0) + 1);
                    queue.offer(neighbor);
                }
            }
        }
    }

    private Set<String> findNeighbor(String current, Set<String> dict) {
        Set<String> result = new HashSet<>();
        for (char ch = 'a'; ch <= 'z'; ch++) {
            for (int i = 0; i < current.length(); i++) {
                if (current.charAt(i) == ch) {
                    continue;
                }
                String newCurrent = replace(current, i, ch);
                if (dict.contains(newCurrent)) {
                    result.add(newCurrent);
                }
            }
        }
        return result;
    }

    private String replace(String current, int i, char ch) {
        char[] charArray = current.toCharArray();
        charArray[i] = ch;
        return new String(charArray);
    }

    private void dfs(String start, Set<String> visited, List<String> temp, List<List<String>> result,
            Map<String, List<String>> route, int routeMinLength, String end) {

        // exit
        if (routeMinLength + 1 == temp.size() && (start.equals(end))) { // 因為題目一開始start和end的記憶體位置就不一樣，所以用start==end會跑出false
            result.add(new ArrayList<String>(temp));
            return;
        }

        // decide
        for (String neighbor : route.get(start)) {

            if (visited.contains(neighbor)) {
                continue;
            }

            visited.add(neighbor);
            temp.add(neighbor);

            dfs(neighbor, visited, temp, result, route, routeMinLength, end);

            visited.remove(neighbor);
            temp.remove(temp.size() - 1);
        }
    }

}