class Solution {

    // Time: O(MN)
    // Space: O(MN)
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {

        // corner case
        if (obstacleGrid.length == 0 || obstacleGrid == null) {
            return 0;
        }

        // 0. definition: 從[0][0] to [i][j]的總路徑數
        int n = obstacleGrid.length;
        int m = obstacleGrid[0].length;
        int[][] dp = new int[n + 1][m + 1];

        // 1. initialization
        for (int i = 0; i < n; i++) {
            if (obstacleGrid[i][0] == 1) {
                break;
            }
            dp[i][0] = 1;
        }
        for (int i = 0; i < m; i++) {
            if (obstacleGrid[0][i] == 1) {
                break;
            }
            dp[0][i] = 1;
        }

        // 2. decide
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (obstacleGrid[i][j] == 1) {
                    continue;
                }
                dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
            }
        }

        // 3. return answer
        return dp[n - 1][m - 1];
    }
}