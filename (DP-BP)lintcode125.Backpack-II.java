class Solution {

    // valued 01 backpack
    // Time: O(length * m`)
    // Space: O(length * m)
    public int backPackII(int m, int[] a, int[] v) { // a: size, v:value

        // 0. definition 1. initialization
        // 前i個東西滿足在j空間內的最大價值
        int length = a.length;
        int[][] dp = new int[length + 1][m + 1];

        // 2. decide
        for (int i = 1; i < length + 1; i++) {
            int value = a[i - 1];
            for (int j = 0; j < m + 1; j++) {

                int no = dp[i - 1][j];

                int yes = value <= j ? dp[i - 1][j - value] + v[i - 1] : 0;

                dp[i][j] = Math.max(no, yes);
            }
        }

        // 3. return
        return dp[length][m];
    }
}
