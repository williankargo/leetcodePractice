// int verion -  primitive
// Space: O(length * amount)
// Time: O(length * amount * amount)
class Solution0 {
    public int change(int amount, int[] coins) {

        // 0. definition
        // 完全背包問題
        // 在前i個東西中，找剛好塞滿背包大小為j，的所有可能性
        int length = coins.length;
        int[][] dp = new int[length + 1][amount + 1];

        // 1. initialization
        dp[0][0] = 1; // 哨兵值：考慮前0個物品，塞滿空背包，也是種方案
        // for (int j = 1; j < amount + 1; j++){ // 設定無效值
        // dp[0][j] = 0;
        // }

        // 2. decide
        for (int i = 1; i < length + 1; i++) {
            int value = coins[i - 1];
            for (int j = 0; j < amount + 1; j++) {

                // 不選
                int no = dp[i - 1][j];

                // 選
                int yes = 0;
                for (int count = 1; value * count <= j; count++) {
                    yes += dp[i - 1][j - count * coins[i - 1]];
                }

                dp[i][j] = yes + no;
            }
        }

        // 3. return
        return dp[length][amount];
    }
}

// int verion - 時間複雜度優化
// Space: O(length * amount)
// Time: O(length * amount)
class Solution1 {
    public int change(int amount, int[] coins) {

        // 0. definition
        // 完全背包問題
        // 在前i個東西中，找剛好塞滿背包大小為j，的所有可能性
        int length = coins.length;
        int[][] dp = new int[length + 1][amount + 1];

        // 1. initialization
        dp[0][0] = 1; // 哨兵值：考慮前0個物品，塞滿空背包，也是種方案
        // for (int j = 1; j < amount + 1; j++){ // 設定無效值
        // dp[0][j] = 0;
        // }

        // 2. decide
        for (int i = 1; i < length + 1; i++) {
            int value = coins[i - 1];
            for (int j = 0; j < amount + 1; j++) {

                // 不選
                int no = dp[i - 1][j];

                // 選
                // int yes = 0;
                // for (int count = 1; value * count <= j; count++) {
                //     yes += dp[i - 1][j - count * coins[i - 1]];
                // }
                // *count優化：(i - 1)變i，只處理第一count
                int yes = value <= j ? dp[i][j - value] : 0;

                dp[i][j] = yes + no;

            }
        }

        // 3. return
        return dp[length][amount];
    }
}

// int verion - 時間複雜度 + 空間複雜度（一維）優化
// Space: O(amount)
// Time: O(length * amount)
class Solution2 {
    public int change(int amount, int[] coins) {

        // 0. definition
        // 完全背包問題
        // 在前i個東西中，找剛好塞滿背包大小為j，的所有可能性
        int length = coins.length;
        int[] dp = new int[amount + 1]; // int[][] dp = new int[length + 1][amount + 1];

        // 1. initialization
        dp[0] = 1; // 哨兵值：考慮前0個物品，塞滿空背包，也是種方案 // d[0][0] = 1;
        // for (int j = 1; j < amount + 1; j++){ // 設定無效值
        // dp[j] = 0; // d[0][j] = 0;
        // }

        // 2. decide
        for (int i = 1; i < length + 1; i++) {
            int value = coins[i - 1];
            for (int j = 0; j < amount + 1; j++) {

                // 不選
                int no = dp[j]; // dp[i - 1][j];

                // 選
                int yes = coins[i - 1] <= j ? dp[j - coins[i - 1]] : 0; // dp[i][j - coins[i - 1]]

                dp[j] = yes + no;

            }
        }

        // 3. return
        return dp[amount];
    }
}