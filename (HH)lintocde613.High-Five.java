import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

class Record {
    public int id, score;

    public Record(int id, int score) {
        this.id = id;
        this.score = score;
    }
}

class Solution {

    // Time: O(Nlog5)
    // Space: O(5)
    public Map<Integer, Double> highFive(Record[] results) {

        Map<Integer, Double> resultMap = new HashMap<>();
        if (results.length == 0 || results == null) {
            return resultMap;
        }

        // 搜集資料
        Map<Integer, Queue<Integer>> currentMap = new HashMap<>();
        for (Record result : results) {

            if (!currentMap.containsKey(result.id)) {
                currentMap.put(result.id, new PriorityQueue<Integer>());
            }

            Queue<Integer> tempQueue = currentMap.get(result.id);

            // prunning
            if (tempQueue.size() == 5) {
                if (result.score < tempQueue.peek()) {
                    continue;
                } else {
                    tempQueue.poll();
                }
            }
            tempQueue.offer(result.score);
        }

        for (Map.Entry<Integer, Queue<Integer>> entry : currentMap.entrySet()) {

            Queue<Integer> tempQueue = entry.getValue();
            int sum = 0;
            while (!tempQueue.isEmpty()) {
                sum += tempQueue.poll();
            }

            resultMap.put(entry.getKey(), (sum / 5.0)); // int碰double轉型成double
        }

        return resultMap;
    }

}