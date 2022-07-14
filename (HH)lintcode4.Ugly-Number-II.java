import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

class Solution {

    // Time: O(3Nlog3N) 每次加到queue耗時log3N，共加入3N次
    // Space: O(3N) poll()出N個，加到queue 3N個
    public int nthUglyNumber(int n) {

        // minHeap
        PriorityQueue<Long> queue = new PriorityQueue<>(); // Long 防止找到答案後繼續乘然後爆掉
        queue.add(1L); // java表示Long的方法

        // visited
        Set<Long> seen = new HashSet<>();
        seen.add(1L);

        // factors
        int[] factors = new int[] { 2, 3, 5 };

        long current = 0L, next;
        for (int i = 0; i < n; i++) {
            current = queue.poll();
            for (int factor : factors) {
                next = current * factor;
                if (!seen.contains(next)) {
                    queue.offer(next);
                    seen.add(next);
                }
            }
        }

        return (int) current;
    }

}