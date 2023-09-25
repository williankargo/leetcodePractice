import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Solution {

    // TC: O(N)
    // SC: O(N)
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {

        // 0. build the graph to do the dfs
        Map<String, Map<String, Double>> graph = new HashMap<>();
        for (int i = 0; i < equations.size(); i++) {
            String up = equations.get(i).get(0); // a
            String down = equations.get(i).get(1); // b
            graph.putIfAbsent(up, new HashMap<>());
            graph.putIfAbsent(down, new HashMap<>());

            graph.get(up).put(down, values[i]);
            graph.get(down).put(up, 1.0 / values[i]);
        }

        // 1. pre-processing DFS
        double[] results = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            String up = queries.get(i).get(0);
            String down = queries.get(i).get(1);

            if (!graph.containsKey(up) || !graph.containsKey(down)) {
                results[i] = -1.0;
            } else if (up == down) {
                results[i] = 1.0;
            } else {
                Set<String> visited = new HashSet<>();
                visited.add(up);
                results[i] = dfs(up, down, graph, visited);
            }
        }

        return results;
    }

    // DFS (BackTracking)
    // up / down = ?
    private double dfs(String up, String target, Map<String, Map<String, Double>> graph, Set<String> visited) {

        // 0. exit
        if (up.equals(target)) {
            return 1.0;
        }

        // 1. for
        for (Map.Entry<String, Double> entry : graph.get(up).entrySet()) {

            String down = entry.getKey();
            Double value = entry.getValue();

            if (!visited.contains(down)) {
                visited.add(down);

                Double subValue = dfs(down, target, graph, visited);
                if (subValue != -1.0) {
                    return value * subValue;
                }

                visited.remove(down);
            }
        }

        // 3. return
        // can't find "up / target"
        return -1.0;
    }
}