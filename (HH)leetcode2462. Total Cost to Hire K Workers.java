import java.util.PriorityQueue;
import java.util.Queue;

class Solution {

    // head amount == tail amount == N
    // TC: O(NlogN) + O(KlogN), 每次插入pq耗O(logN)
    // SC: O(N)
    public long totalCost(int[] costs, int k, int candidates) {

        Queue<Integer> head = new PriorityQueue<>();
        Queue<Integer> tail = new PriorityQueue<>();

        // get the head sum and tail sum
        for (int i = 0; i < candidates; i++) {
            head.offer(costs[i]);
        }
        // 確保不會跟head的取值重疊
        for (int j = Math.max(candidates, costs.length - candidates); j < costs.length; j++) {
            tail.offer(costs[j]);
        }

        // nextIndex
        int headNextIndex = candidates;
        int tailNextIndex = costs.length - candidates - 1;

        // select k person
        long total = 0;
        for (int i = 0; i < k; i++) {
            if (tail.isEmpty() || !head.isEmpty() && head.peek() <= tail.peek()) {
                total += head.poll();
                if (headNextIndex <= tailNextIndex) {
                    head.offer(costs[headNextIndex++]);
                }
            } else {
                total += tail.poll();
                if (headNextIndex <= tailNextIndex) {
                    tail.offer(costs[tailNextIndex--]);
                }
            }
        }

        return total;
    }
}