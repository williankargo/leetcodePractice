class Solution {

    // DP-01BackPack version
    // Time: O(num * sum), sum = originalSum / 2
    // Space: O(num * sum)
    public int findMin(int[] nums) {

        if (nums == null || nums.length == 0) {
            return -1;
        }

        // 推敲後，可以知道本題是背包問題，要往背包放入i個東西接近於j的總和
        // i 最多為全部的個數，j最多為 sum / 2

        // 0. definition
        // 前i個物品可不可以加起來等於j
        int num = nums.length;
        int originalSum = 0;
        for (int i = 0; i < nums.length; i++) {
            originalSum += nums[i];
        }
        int sum = originalSum >> 1;
        boolean[][] dp = new boolean[num + 1][sum + 1];

        // 1. initialization
        dp[0][0] = true;

        // 2. decide
        for (int i = 1; i < num + 1; i++) {
            int value = nums[i - 1];
            for (int j = 0; j < sum + 1; j++) {

                boolean no = dp[i - 1][j];

                boolean yes = value <= j ? dp[i - 1][j - nums[i - 1]] : false;

                dp[i][j] = no | yes;
            }
        }

        // 3. return
        int ans = 0;
        for (int m = sum; m >= 0; m--) {
            if (dp[num][m]) {
                ans = m;
                break;
            }
        }

        return (originalSum - 2 * ans);
    }
}