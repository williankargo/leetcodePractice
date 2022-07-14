class Solution {

    // DP
    // bottom to top version
    // Time: O(n^2)
    // Space: O(n^2)
    public int minimumTotal(int[][] triangle) {

        if (triangle.length == 0 || triangle == null) {
            return -1;
        }

        int n = triangle.length;
        int[][] dp = new int[n][n];

        // 先初始化最底層
        for (int i = 0; i < n; i++) {
            dp[n - 1][i] = triangle[n - 1][i];
        }

        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle[i][j];
            }
        }

        return dp[0][0];
    }
}