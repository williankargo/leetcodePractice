import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

class Solution {

    // Time: O(N) + O(N)
    // Space: O(N)

    // 這道題本來以為可以用sliding window(i,j)解，但發現要不斷在區間中找最大，傳統的sliding window(i,j)無法解
    // 於是用新方法：mono-queue (因為要返回隊尾的資料，所以不能用普通的queue要用deque)
    // 1. maintain a mono-decreasing queue (the queue-head is the max of sliding
    // window)
    // 2. check if queue-head is outdated
    // https://www.youtube.com/watch?v=b1rqOQ5p6EA
    public int[] maxSlidingWindow(int[] nums, int k) {

        if (nums.length == 1 && k == 1) {
            return nums;
        }

        List<Integer> ans = new ArrayList<>();
        // 0. Deque
        Deque<Integer> queue = new ArrayDeque<>(); // 存放index，deque -> 使其mono-decreasing

        for (int i = 0; i < nums.length; i++) {

            // 1. maintain a mono-decreasing queue
            while (!queue.isEmpty() && nums[i] >= nums[queue.peekLast()]) {
                queue.pollLast(); // queue中的元素最多只會被poll出來一次
            }
            queue.offerLast(i);

            // 2. check if queue-head is outdated
            while (!queue.isEmpty() && i - queue.peekFirst() >= k) {
                queue.pollFirst();
            }

            // 3. return valid value
            if (i >= (k - 1)) {
                ans.add(nums[queue.peekFirst()]);
            }
        }

        int[] finalAns = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            finalAns[i] = ans.get(i);
        }

        return finalAns;
    }
}