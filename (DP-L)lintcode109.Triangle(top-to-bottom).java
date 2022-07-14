class Solution {

    // DP
    // top to bottom version
    // Time: O(n^2)
    // Space: O(n^2)
    public int minimumTotal(int[][] triangle) {

        if (triangle.length == 0 || triangle == null) {
            return -1;
        }

        int n = triangle.length;
        int[][] dp = new int[n][n];
        // 左側和右側和第一個要先初始化
        dp[0][0] = triangle[0][0];
        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i - 1][0] + triangle[i][0];
            dp[i][i] = dp[i - 1][i - 1] + triangle[i][i];
        }

        // 開始up to bottom 使用記憶化搜索
        for (int i = 2; i < n; i++) {
            for (int j = 1; j < i; j++) {
                dp[i][j] = Math.min(dp[i - 1][j - 1], dp[i - 1][j]) + triangle[i][j];
            }
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            min = Math.min(dp[n - 1][i], min);
        }

        return min;
    }
}