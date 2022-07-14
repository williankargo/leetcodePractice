/**
 * 0. boolean[][] dp 原始版本
 */

// Time: O(length * sum)
// Space: O(length * sum)
class Solution0 {
    public boolean canPartition(int[] nums) {

        int length = nums.length;
        int totalSum = 0;
        for (int item : nums) {
            totalSum += item;
        }
        int sum = totalSum / 2;

        if (sum * 2 != totalSum) {
            return false;
        }

        // 0. definition
        // 前i個東西能不能湊出剛好等於j
        boolean[][] dp = new boolean[length + 1][sum + 1];

        // 1. initialization
        dp[0][0] = true;

        // 2. decide
        for (int i = 1; i < length + 1; i++) {
            for (int j = 0; j < sum + 1; j++) {

                // 不選
                boolean no = dp[i - 1][j];

                // 選
                boolean yes = nums[i - 1] <= j ? dp[i - 1][j - nums[i - 1]] : false;

                dp[i][j] = no | yes;
            }
        }

        // 3. return
        return dp[length][sum];
    }
}

/**
 * 1. boolean[][] dp 滾動數組版本
 */

// Time: O(length * sum)
// Space: O(2 * sum)
class Solution1 {
    public boolean canPartition(int[] nums) {

        int length = nums.length;
        int totalSum = 0;
        for (int item : nums) {
            totalSum += item;
        }
        int sum = totalSum / 2;

        if (sum * 2 != totalSum) {
            return false;
        }

        // 0. definition
        // 前i個東西能不能湊出剛好等於j
        boolean[][] dp = new boolean[2][sum + 1];

        // 1. initialization
        dp[0][0] = true;

        // 2. decide
        for (int i = 1; i < length + 1; i++) {
            for (int j = 0; j < sum + 1; j++) {

                // 不選
                boolean no = dp[(i - 1) & 1][j];

                // 選
                boolean yes = nums[i - 1] <= j ? dp[(i - 1) & 1][j - nums[i - 1]] : false;

                dp[(i) & 1][j] = no | yes;
            }
        }

        // 3. return
        return dp[length & 1][sum];
    }
}

/**
 * 2. int[] dp 一維空間優化版本
 * dp[i][j] = (dp[i - 1][j] || dp[i - 1][j - nums[i - 1]]);
 * （答案只看上個維度(i - 1)的東西，所以可以維度消除）
 */
// 答案依賴於dp[i - 1][j - x]，所以要反序
// Time: O(length * sum)
// Space: O(sum)
class Solution2 {
    public boolean canPartition(int[] nums) {

        int length = nums.length;
        int totalSum = 0;
        for (int item : nums) {
            totalSum += item;
        }
        int sum = totalSum / 2;

        if (sum * 2 != totalSum) {
            return false;
        }

        // 0. definition
        // (前i個東西)能不能湊出剛好等於j
        boolean[] dp = new boolean[sum + 1];

        // 1. initialization
        dp[0] = true;

        // 2. decide
        for (int i = 1; i < length + 1; i++) {
            for (int j = sum; j >= 0; j--) { // ！記得倒序！ 因為沒有i了，從後面往前面走，在 dp[後面] = dp[前面] 中 dp[i][後面] 才不會判斷到已經被更動過(變成
                                             // dp[i][前面])的 dp[i - 1][前面]

                // 不選
                boolean no = dp[j]; // [i - 1][j]的狀態

                // 選
                boolean yes = nums[i - 1] <= j ? dp[j - nums[i - 1]] : false; // [i - 1][j - nums[i - 1]] 的狀態

                dp[j] = no | yes; // [i][j]的狀態
            }
        }

        // 3. return
        return dp[sum];
    }
}