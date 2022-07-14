
/**
 * 0. int[][] dp 原始版本
 */

// Time: O(length * sum)
// Space: O(length * sum)
class Solution0 {

    public boolean canPartition(int[] nums) {

        // corener case
        if (nums.length == 0 || nums == null) {
            return false;
        }

        int length = nums.length;
        int totalSum = 0;
        for (int item : nums) {
            totalSum += item;
        }
        int sum = totalSum / 2;

        // 沒辦法對半分
        if (sum * 2 != totalSum) {
            return false;
        }

        // 0. definition & 1. initialization
        // 求前i個東西加起來<=j的最大值
        int[][] dp = new int[length + 1][sum + 1];
        // 前0個東西加起來 <=0 的最大值是0

        // 2. decide
        for (int i = 1; i < length + 1; i++) {
            for (int j = 0; j < sum + 1; j++) { // 正序、反序 都可以

                // 不選第i件物品
                // 從前(i - 1)件物品中挑選
                int no = dp[i - 1][j];

                // 選第i件物品
                // 先把第i個空間去掉，得到之前的dp，再加上第i個的空間
                int yes = nums[i - 1] <= j ? dp[i - 1][j - nums[i - 1]] + nums[i - 1] : 0;

                // 在選或不選中挑最大的
                dp[i][j] = Math.max(no, yes);
            }
        }

        // 3. return
        if (dp[length][sum] == sum) {
            return true;
        }

        return false;
    }
}

/**
 * 1. int[][] dp 滾動數組版本
 */

// Time: O(length * sum)
// Space: O(2 * sum)
class Solution1 {

    public boolean canPartition(int[] nums) {

        // corener case
        if (nums.length == 0 || nums == null) {
            return false;
        }

        int length = nums.length;
        int totalSum = 0;
        for (int item : nums) {
            totalSum += item;
        }
        int sum = totalSum / 2;

        // 沒辦法對半分
        if (sum * 2 != totalSum) {
            return false;
        }

        // 0. definition & 1. initialization
        // 求前i個東西加起來<=j的最大值
        int[][] dp = new int[2][sum + 1]; // 奇數偶數會輪番交替
        // 前0個東西加起來 <=0 的最大值是0

        // 2. decide
        for (int i = 1; i < length + 1; i++) {
            for (int j = 0; j < sum + 1; j++) { // 正序、反序都可以

                // 不選第i件物品
                // 從前(i - 1)件物品中挑選
                int no = dp[(i - 1) & 1][j];

                // 選第i件物品
                // 先把第i個空間去掉，得到之前的dp，再加上第i個的空間
                int yes = nums[i - 1] <= j ? dp[(i - 1) & 1][j - nums[i - 1]] + nums[i - 1] : 0;

                // 在選或不選中挑最大的
                dp[(i) & 1][j] = Math.max(no, yes);
            }
        }

        // 3. return
        if (dp[(length) & 1][sum] == sum) {
            return true;
        }

        return false;
    }
}

/**
 * 2. int[] dp 一維空間優化版本
 * dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - nums[i - 1]] + nums[i -1]);
 * （答案只看上個維度(i - 1)的東西，所以可以維度消除）
 */

// Time: O(length * sum)
// Space: O(sum)
class Solution2 {

    public boolean canPartition(int[] nums) {

        // corener case
        if (nums.length == 0 || nums == null) {
            return false;
        }

        int length = nums.length;
        int totalSum = 0;
        for (int item : nums) {
            totalSum += item;
        }
        int sum = totalSum / 2;

        // 沒辦法對半分
        if (sum * 2 != totalSum) {
            return false;
        }

        // 0. definition & 1. initialization
        // 求(前i個東西)加起來<=j的最大值, 這裡i要偷偷理解
        int[] dp = new int[sum + 1];
        // dp[0] = 0;

        // 2. decide
        for (int i = 1; i < length + 1; i++) {
            for (int j = sum; j >= 0; j--) { // ！記得倒序！ dp[後面] 才不會判斷到已經更動過變成這一層值的 dp[前面]

                // 不選第i件物品
                // 偷偷理解為 dp[i - 1][j]
                int no = dp[j];

                // 選第i件物品
                // 先把第i個空間去掉，得到之前的dp，再加上第i個的空間
                int yes = nums[i - 1] <= j ? dp[j - nums[i - 1]] + nums[i - 1] : 0; // 偷偷理解為 dp[i - 1][j - nums[i - 1]]
                                                                                    // + nums[i - 1]

                // 在選或不選中挑最大的
                dp[j] = Math.max(no, yes); // 偷偷理解為 dp[i][j]
            }
        }

        // 3. return
        if (dp[sum] == sum) {
            return true;
        }

        return false;
    }
}