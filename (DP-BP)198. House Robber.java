// DP-BP
// TC: O(N)
// SC: O(N)
class Solution {
    public int rob(int[] nums) {

        // 0.

        // 1.
        // 前n間房子總和最大為多少
        int[] dp = new int[nums.length + 1];
        // 2.
        dp[0] = 0;
        dp[1] = nums[0];

        // 3.
        for (int i = 2; i <= nums.length; i++) {
            // select or not select nums[i - 1]
            int select = dp[i - 2] + nums[i - 1];
            int noSelect = dp[i - 1];
            dp[i] = Math.max(select, noSelect);
        }

        // 4.
        return dp[nums.length];
    }
}