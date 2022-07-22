import java.util.ArrayDeque;
import java.util.Queue;

// BFS version
// 最短路徑想到BFS
class Solution1 {

    // Time: O(N^2) ！！數據範圍 <= 5000 可以用到 O(N^2) 不會超時！！
    // Spze: O(N)
    public int jump(int[] nums) {

        int numsLength = nums.length;

        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[numsLength];

        // 將第一個index加進去
        queue.offer(0);
        visited[0] = true;

        int step = 0;

        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            for (int i = 0; i < queueSize; i++) {
                Integer curIndex = queue.poll();
                if (curIndex == numsLength - 1) { // 已經走到終點了
                    return step;
                }
                for (int ii = curIndex + 1; ii <= curIndex + nums[curIndex] && ii < numsLength; ii++) {
                    // 如果沒有被訪問過
                    if (!visited[ii]) {
                        visited[ii] = true;
                        queue.offer(ii);
                    }
                }
            }
            step++;
        }

        return step;
    }
}

// DP + 2p + Greedy version
// 如果數據範圍變 10^6 -> 用 O(N)
// 如果用jumpI的版本寫，會超時
class Solution {

    // Time: O(N)
    // Space: O(N)
    public int jump(int[] nums) {

        // 0.
        // 到達i的最少步數
        int[] dp = new int[nums.length];

        // 1.
        dp[0] = 0;

        // 2.
        for (int i = 1, j = 0; i < nums.length; i++) {

            // greedy: 取當下最佳
            // greedy + 2p>>
            while (j + nums[j] < i) {
                j++;
            }

            dp[i] = dp[j] + 1;
        }

        // 3.
        return dp[nums.length - 1];
    }
}