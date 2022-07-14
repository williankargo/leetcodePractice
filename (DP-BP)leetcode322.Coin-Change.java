// int version 1 - 時間複雜度優化
// Time: O(length * amount)
// Space: O(length * amount)
class Solution1 {

    int INF = Integer.MAX_VALUE >> 4; // 左乘右除，左移四位防暴掉

    public int coinChange(int[] coins, int amount) {

        // 0. definition
        // 在前i個物品中，挑東西放滿容量為j的背包，的最少物品數
        int length = coins.length;
        int[][] dp = new int[length + 1][amount + 1];

        // 1. initialization
        // 哨兵值
        dp[0][0] = 0;
        for (int j = 1; j < amount + 1; j++) {
            dp[0][j] = INF; // 設定無效值
        }

        // 2. decide
        for (int i = 1; i < length + 1; i++) {
            int value = coins[i - 1];
            for (int j = 0; j < amount + 1; j++) {

                // 不選
                int no = dp[i - 1][j];

                // 選
                int yes = value <= j ? dp[i][j - value] + 1 : INF;

                dp[i][j] = Math.min(no, yes);
            }
        }

        // 3. return
        // ！要注意有無效值出現的可能性！
        return dp[length][amount] != INF ? dp[length][amount] : -1;
    }
}


// int version 2 - 時間複雜度 + 空間複雜度 優化
// Time: O(length * amount)
// Space: O(amount)
class Solution2 {

    int INF = Integer.MAX_VALUE >> 4; // 左乘右除，左移四位防暴掉

    public int coinChange(int[] coins, int amount) {

        // 0. definition
        // (在前i個物品中)，挑東西放滿容量為j的背包，的最少物品數
        int length = coins.length;
        int[] dp = new int[amount + 1]; // int[][] dp = new int[length + 1][amount + 1];

        // 1. initialization
        // 哨兵值
        dp[0] = 0; // dp[0][0] = 0;
        for (int j = 1; j < amount + 1; j++) {
            dp[j] = INF; // 設定無效值 // dp[0][j] = INF
        }

        // 2. decide
        for (int i = 1; i < length + 1; i++) {
            int value = coins[i - 1];
            for (int j = 0; j < amount + 1; j++) { // 正序

                // 不選
                int no = dp[j]; // dp[i - 1][j]

                // 選
                int yes = value <= j ? dp[j - value] + 1 : INF; // dp[i][j - value] + 1

                dp[j] = Math.min(no, yes);
            }
        }

        // 3. return
        // ！要注意有無效值出現的可能性！
        return dp[amount] != INF ? dp[amount] : -1; // dp[length][amount]
    }
}
