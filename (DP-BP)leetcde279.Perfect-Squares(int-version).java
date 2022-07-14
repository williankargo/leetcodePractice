import java.util.ArrayList;
import java.util.List;

// int version - primitive
// Space: O(n^1/2 * n)
// Time: O(n^1/2 * n * n)
class Solution0 {
    public int numSquares(int n) {

        // collect all posibilities
        List<Integer> list = new ArrayList<>();
        int num = 1;
        while (num * num <= n) {
            list.add(num * num);
            num++;
        }

        // 0. definition
        // 定義需經過轉換
        // 在前i種物品中，每種物品可以抓取無限次，抓取到背包中填滿j的最少物品數
        int length = list.size();
        int[][] dp = new int[length + 1][n + 1];

        // 1. initialization
        dp[0][0] = 0;
        for (int j = 1; j < n + 1; j++) {
            dp[0][j] = Integer.MAX_VALUE;
        }

        // 2. decide
        for (int i = 1; i < length + 1; i++) { // 從第1個開始
            int value = list.get(i - 1);
            for (int j = 0; j < n + 1; j++) {

                // 不選
                int no = dp[i - 1][j];

                // 選
                int yes = Integer.MAX_VALUE;
                for (int count = 1; count * value <= j; count++) {
                    yes = Math.min(yes, dp[i - 1][j - count * value] + count);
                }

                dp[i][j] = Math.min(no, yes);
            }
        }

        // 3. return
        return dp[length][n];
    }
}

// int version - 時間複雜度優化
// Space: O(n^1/2 * n)
// Time: O(n^1/2 * n)
class Solution1 {
    public int numSquares(int n) {

        // collect all posibilities
        List<Integer> list = new ArrayList<>();
        int num = 1;
        while (num * num <= n) {
            list.add(num * num);
            num++;
        }

        // 0. definition
        // 定義需經過轉換
        // 在前i種物品中，每種物品可以抓取無限次，抓取到背包中填滿j的最少物品數
        int length = list.size();
        int[][] dp = new int[length + 1][n + 1];

        // 1. initialization
        dp[0][0] = 0;
        for (int j = 1; j < n + 1; j++) {
            dp[0][j] = Integer.MAX_VALUE;
        }

        // 2. decide
        for (int i = 1; i < length + 1; i++) { // 從第1個開始
            int value = list.get(i - 1);
            for (int j = 0; j < n + 1; j++) {

                // 不選
                int no = dp[i - 1][j];

                // 選
                // https://reurl.cc/RrMO5z
                // 時間複雜度優化：(i - 1)變 i，只需處理第一個count
                int yes = value <= j ? dp[i][j - value] + 1 : Integer.MAX_VALUE;

                dp[i][j] = Math.min(no, yes);
            }
        }

        // 3. return
        return dp[length][n];
    }
}

// int version - 時間複雜度＋空間複雜度 優化 (一維數組版本)
// Space: O(n)
// Time: O(n^1/2 * n)
class Solution2 {
    public int numSquares(int n) {

        // collect all posibilities
        List<Integer> list = new ArrayList<>();
        int num = 1;
        while (num * num <= n) {
            list.add(num * num);
            num++;
        }

        // 0. definition
        // 定義需經過轉換
        // (在前i種物品中)，每種物品可以抓取無限次，抓取到背包中填滿j的最少物品數
        int length = list.size();
        int[] dp = new int[n + 1];

        // 1. initialization
        dp[0] = 0;
        for (int j = 1; j < n + 1; j++) {
            dp[j] = Integer.MAX_VALUE; // dp[0][j] = Integer.MAX_VALUE;
        }

        // 2. decide
        for (int i = 1; i < length + 1; i++) { // 從第1個開始
            int value = list.get(i - 1);
            for (int j = 0; j < n + 1; j++) { // 依賴於現在的i：正序

                // 不選
                int no = dp[j]; // int no = dp[i - 1][j];

                // 選
                // https://reurl.cc/RrMO5z
                // 時間複雜度優化：(i - 1)變 i，只需處理第一個count
                int yes = value <= j ? dp[j - value] + 1 : Integer.MAX_VALUE; // dp[i][j - value] + 1

                dp[j] = Math.min(no, yes);
            }
        }

        // 3. return
        return dp[n];
    }
}