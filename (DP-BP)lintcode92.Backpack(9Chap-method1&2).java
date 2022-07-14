class Solution0 {

    // 01 背包問題: 狀態1
    // Time: O(NM)
    // Space: O(NM)
    public int backPack(int m, int[] a) {

        int length = a.length;

        // 0. definition
        // 前i個非0數加起來可不可以等於j
        // 因為要用到『前length個總合有沒有為m』，所以都要+1
        boolean[][] dp = new boolean[length + 1][m + 1]; // default: false

        // 1. initialization
        // 加入前0個數總和為0為true的初始條件
        dp[0][0] = true;

        // 2. decide
        for (int i = 1; i < length + 1; i++) { // 前i個(前i個 = a[i - 1])
            for (int j = 0; j < m + 1; j++) { // 總和
                if (a[i - 1] <= j) { // 第(i-1個)的數字小於等於總和
                    // (1)沒有你也沒差 (2)有你剛剛好，那把你去掉
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - a[i - 1]];
                } else { // 第(i-1個)的數字大於總和
                    // (1)沒有你也沒差，反正用不到你
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        // 3. return
        // 前length個物品湊成的最大和是多少
        for (int j = m; j >= 0; j--) {
            if (dp[length][j] == true) {
                return j;
            }
        }

        return -1;
    }
}

class Solution1 {

    // 01背包問題：狀態2
    // 但因為『或』比『加法』還要快，所以使用狀態一會比較快！
    // Time: O(MN)
    // Space: O(MN)
    public int backPack(int m, int[] a) {

        // corner case
        if (a == null || a.length == 0) {
            return -1;
        }

        int length = a.length;
        // 0. definition: 前i個東西加起來<=j的最大值
        // 1. initialization
        int[][] dp = new int[length + 1][m + 1];

        // 2. decide
        for (int i = 1; i < length + 1; i++) {
            for (int j = 0; j < m + 1; j++) {
                if (a[i - 1] <= j) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - a[i - 1]] + a[i - 1]); // (1)沒有你也差 (2)有你剛剛好
                } else {
                    // 沒有你也沒差，因為你太大了用不到
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        // 3. return
        return dp[length][m];
    }
}