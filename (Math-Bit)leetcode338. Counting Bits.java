// solution: https://www.youtube.com/watch?v=RyBM56RIWrM&ab_channel=NeetCode
// TC: O(N) (brute-force: NlogN, 每個數要花logN次檢視可以被2除幾次)
// SC: O(N)
class Solution {
    public int[] countBits(int n) {

        // 0.
        int[] dp = new int[n + 1];
        dp[0] = 0;
        int offset = 1;

        for (int i = 1; i <= n; i++) {
            if (offset * 2 == i) {
                offset = i;
            }
            dp[i] = 1 + dp[i - offset];
        }

        return dp;
    }
}