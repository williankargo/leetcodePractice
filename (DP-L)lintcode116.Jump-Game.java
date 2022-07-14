class Solution {

    // 求可行性、單向方向性 => DP
    // Time: O(N^2)
    // Space: O(N)
    public boolean canJump(int[] a) {

        if (a == null || a.length == 0) {
            return false;
        }

        // 0. definition
        // 可以從[0]到達這裡
        boolean[] dp = new boolean[a.length];

        // 1. initialization
        dp[0] = true;
        int length = a.length;

        // 2. decide
        for (int i = 1; i < length; i++) {
            for (int j = 0; j < i; j++) {
                if ((j + a[j] >= i) && dp[j]) {
                    dp[i] = true;
                    break; // 確定dp是true,就不用再找其他可能性
                }
            }
        }

        // 3. return
        return dp[length - 1];
    }
}