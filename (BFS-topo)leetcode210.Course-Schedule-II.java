import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

class Solution {

    // TC: O(N)
    // SC: O(N), N = numCourses
    public int[] findOrder(int numCourses, int[][] prerequisites) {

        // [1, 0] 0 -> 1
        // 1. build the graph
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < numCourses; i++) {
            graph.put(i, new ArrayList<>());
        }

        // 2. count the indegree
        Map<Integer, Integer> indegree = new HashMap<>();
        for (int i = 0; i < numCourses; i++) {
            indegree.put(i, 0);
        }
        for (int i = 0; i < prerequisites.length; i++) {
            indegree.put(prerequisites[i][0], indegree.get(prerequisites[i][0]) + 1);
            List<Integer> neighbors = graph.get(prerequisites[i][1]); // 這裡記得要把List分開處理！
            neighbors.add(prerequisites[i][0]);
            graph.put(prerequisites[i][1], neighbors);
        }

        // 3. indegree == 0 ==> queue
        Queue<Integer> queue = new ArrayDeque<>();
        for (int key : indegree.keySet()) {
            if (indegree.get(key) == 0) {
                queue.offer(key);
            }
        }

        // 4. BFS
        List<Integer> list = new ArrayList<>();
        int i = 0;
        while (!queue.isEmpty()) {
            int current = queue.poll();
            if (i == numCourses) {
                break;
            }
            list.add(current);
            i++;
            for (int neighbor : graph.get(current)) {
                indegree.put(neighbor, indegree.get(neighbor) - 1);
                if (indegree.get(neighbor) == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        // turn List to array
        int list_size = list.size();
        if (list_size != numCourses) { // no answer
            return new int[] {};
        }
        int[] ans = new int[list_size];
        for (int c = 0; c < list_size; c++) {
            ans[c] = list.get(c);
        }

        return ans;
    }
}