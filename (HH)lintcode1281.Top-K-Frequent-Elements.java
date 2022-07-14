import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

class Solution {

    // Time: O(N + NlogK)
    // Space: O(N)
    public List<Integer> topKFrequent(int[] nums, int k) {

        if (nums == null || nums.length == 0) {
            return new ArrayList<Integer>();
        }

        Map<Integer, Integer> hashMap = new HashMap<>();
        Queue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>(k, // item : times
                new Comparator<Map.Entry<Integer, Integer>>() {
                    public int compare(Map.Entry<Integer, Integer> map1, Map.Entry<Integer, Integer> map2) {
                        return map1.getValue() - map2.getValue();
                    }
                });

        // 以HashMap統計各item的次數
        for (int num : nums) {
            hashMap.put(num, hashMap.getOrDefault(num, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : hashMap.entrySet()) {
            // 維持queue k 的大小
            if (pq.size() < k) {
                pq.offer(entry);
            } else if (pq.peek().getValue() < entry.getValue()) {
                pq.poll();
                pq.offer(entry);
            }
        }

        List<Integer> result = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : pq) {
            result.add(entry.getKey());
        }

        return result;
    }
}