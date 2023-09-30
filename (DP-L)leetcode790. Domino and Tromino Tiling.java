// solution: https://www.youtube.com/watch?v=S-fUTfqrdq8&ab_channel=HuaHua
// TC: O(N)
// SC: O(N)
class Solution {
    public int numTilings(int n) {

        long mod = (long) ((1e+9) + 7);

        // 1.
        // ways to cover 前i個 columns
        // remember to (n + 1) !
        long[][] dp = new long[n + 1][2]; // 2: type 0 or type 1 (refer the picture of the video)

        // 2.
        dp[0][0] = dp[1][0] = 1; // dp[0][0] == 1 要去思考dp[2][0]時才會知道

        // 3.
        for (int i = 2; i <= n; i++) {
            dp[i][0] = (dp[i - 1][0] + dp[i - 2][0] + 2 * dp[i - 1][1]) % mod;
            dp[i][1] = (dp[i - 2][0] + dp[i - 1][1]) % mod;
        }

        // 4.
        return (int) dp[n][0];
    }
}