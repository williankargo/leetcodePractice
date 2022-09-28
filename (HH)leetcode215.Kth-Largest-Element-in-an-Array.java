import java.util.PriorityQueue;
import java.util.Queue;

// heap version
// Time: O(NLogK)
// Space: O(K)
class Solution1 {
    public int findKthLargest(int[] nums, int k) {

        Queue<Integer> queue = new PriorityQueue<>(); // size = k
        for (int i = 0; i < nums.length; i++) {

            if (queue.size() < k) {
                queue.offer(nums[i]);
            } else if (queue.size() == k) {
                if (nums[i] > queue.peek()) {
                    queue.poll();
                    queue.offer(nums[i]);
                }
            }

        }

        return queue.peek();
    }
}

// quick-select version
