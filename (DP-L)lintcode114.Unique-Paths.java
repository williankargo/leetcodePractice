class Solution {

    // Time: O(mn)
    // Space: O(mn)
    public int uniquePaths(int m, int n) {

        // corner case
        if (m == 1 || n == 1) {
            return 1;
        }

        // 0. 定義: [0][0]走到[n][m]有多少種路徑可能性
        int[][] dp = new int[n][m];

        // 2. 拆解
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 1. 初始化（出口）
                if (i == 0 || j == 0) {
                    dp[i][j] = 1; // 只能往down, right走
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }

        // 3. answer(調用)
        return dp[n - 1][m - 1];
    }
}