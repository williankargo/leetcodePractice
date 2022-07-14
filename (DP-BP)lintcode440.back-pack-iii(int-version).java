/**
 * 解法一：未優化版本 (可以先簡單加上滾動數組 &1 優化)
 */
// Time: O(length * m * m)
// Space: O(length * m * m)
class Solution0 {

    public int backPackIII(int[] a, int[] v, int m) {

        // 0. definition
        // 前i個東西選擇放到背包中<=j的最大值
        int length = a.length;
        int[][] dp = new int[length + 1][m + 1];

        // 1. initialization
        // dp[0][0] = 0;

        // 2. decide
        for (int i = 1; i < length + 1; i++) {
            for (int j = 0; j < m + 1; j++) {

                // 不選
                int no = dp[i - 1][j];

                // 選
                int yes = 0;
                for (int k = 1; k <= m + 1; k++) {
                    if (a[i - 1] * k > j) {
                        break;
                    }
                    yes = Math.max(yes, dp[i - 1][j - k * a[i - 1]] + k * v[i - 1]);
                }

                dp[i][j] = Math.max(no, yes);
            }
        }

        // 3. return
        return dp[length][m];
    }
}

/**
 * 解法二：時間複雜度優化版本
 */
// Time: O(length * m)
// Space: O(length * m)
class Solution1 {

    public int backPackIII(int[] a, int[] v, int m) {

        // 0. definition
        // 前i個東西選擇放到背包中<=j的最大值
        int length = a.length;
        int[][] dp = new int[length + 1][m + 1];

        // 1. initialization
        // dp[0][0] = 0;

        // 2. decide
        for (int i = 1; i < length + 1; i++) {
            for (int j = 0; j < m + 1; j++) {

                // 不選
                int no = dp[i - 1][j];

                // 選
                // int yes = 0;
                // for (int k = 1; k <= m + 1; k++){
                // if (a[i - 1] * k > j){
                // break;
                // }
                // yes = Math.max(yes, dp[i - 1][j - k * a[i - 1]] + k * v[i - 1]);
                // }

                // https://reurl.cc/WrKKoy
                // 可由以上的數學過程推導出以下狀態，寫i
                int yes = a[i - 1] <= j ? dp[i][j - a[i - 1]] + v[i - 1] : 0; // ！記起來！

                dp[i][j] = Math.max(no, yes);
            }
        }

        // 3. return
        return dp[length][m];
    }
}

/**
 * 解法二：時間複雜度優化 + 空間複雜度優化(一維數組) 版本
 */
// 答案依賴於 dp[i][j - x]，所以必須正序
// Time: O(length * m)
// Space: O(m)
class Solution2 {

    public int backPackIII(int[] a, int[] v, int m) {

        // 0. definition
        // (前i個東西)選擇放到背包中<=j的最大值
        int length = a.length;
        int[] dp = new int[m + 1];

        // 1. initialization
        // dp[0] = 0;

        // 2. decide
        for (int i = 1; i < length + 1; i++) {
            for (int j = 0; j < m + 1; j++) { // 必須正序，因為要確保dp[j - a[i - 1]]是更新後的狀態

                // 不選
                int no = dp[j]; // dp[i - 1][j];

                // 選
                int yes = a[i - 1] <= j ? dp[j - a[i - 1]] + v[i - 1] : 0; // dp[i][j - a[i - 1]] + v[i - 1]

                dp[j] = Math.max(no, yes); // dp[i][j]
            }
        }

        // 3. return
        return dp[m];
    }
}