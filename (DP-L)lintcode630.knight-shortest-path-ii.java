// https://mrleonhuang.gitbooks.io/lintcode/content/dynamic-programming/knight-shortest-path-ii.html
class Solution1 {

    // DP: 必須要有統一的方向，才可以使用DP / 求最值
    // Time: O(MN)
    // Space: O(MN)
    int[][] directions = new int[][] { { -1, -2 }, { -2, -1 }, { -2, 1 }, { -1, 2 } };

    public int shortestPath2(boolean[][] grid) {

        if (grid == null || grid.length == 0) {
            return -1;
        }

        // 0. definition: 從 [0][0] to [i][j] 總共有多少種路徑
        int n = grid[0].length;
        int m = grid.length;
        int[][] dp = new int[m][n];

        // 1. initialization
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dp[j][i] = Integer.MAX_VALUE;
            }
        }
        dp[0][0] = 0; // 不需要第一排或第一行都初始化，因為這是用跳的，初始化的地方不一定可以走到

        // 2. decide
        // dp 要統一同一個方向，所以先固定column，上下走row
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                if (grid[j][i]) {
                    continue;
                }

                for (int[] direction : directions) {
                    int newX = i + direction[0];
                    int newY = j + direction[1];

                    // 檢查有沒有越界
                    if (newX < 0 || newY < 0 || newX > (n - 1) || newY > (m - 1)) {
                        continue;
                    }

                    // 檢查是不是block 或 之前並沒有走過的（因為是跳的，所以有這個可能性）
                    if (dp[newX][newY] == Integer.MAX_VALUE) {
                        continue;
                    }

                    dp[j][i] = Math.min(dp[j][i], dp[newY][newX] + 1); // dp[newY][newX] + 1 => 從後面走到 dp[j][i]
                }
            }
        }

        // 真的可以跳到這裡
        if (dp[m - 1][n - 1] == Integer.MAX_VALUE) {
            return -1;
        }

        return dp[m - 1][n - 1];
    }
}

// 滾動數組優化版本
class Solution2 {

    // Time: O(MN)
    // Space:
    int[][] directions = new int[][] { { -1, -2 }, { -2, -1 }, { -2, 1 }, { -1, 2 } };
    int INF = Integer.MAX_VALUE >> 4;

    public int shortestPath2(boolean[][] grid) {

        if (grid == null || grid.length == 0) {
            return -1;
        }

        // 0. definition
        // from [0][0] to [m][n] 的最小路徑長度
        int m = grid.length; // j
        int n = grid[0].length; // i
        int[][] dp = new int[m][3]; // 滾動數組優化 (畫圖出來看便可以知道最少需要多少)

        // 1. initialization
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < m; j++) {
                dp[j][i] = INF;
            }
        }
        dp[0][0] = 0;

        // 2. decide
        // 固定住同column往右往遍歷
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                // stone, skip
                if (grid[j][i]) {
                    continue;
                }

                // 從鄰居來
                for (int[] direction : directions) {
                    // 後面找來
                    int newX = i + direction[0];
                    int newY = j + direction[1];

                    if (newX < 0 || newY < 0 || newX > (n - 1) || newY > (m - 1)) {
                        continue;
                    }

                    // 後面走來的沒有被訪問過，可能是因為是石頭或本來就不會被訪問到
                    if (dp[newY][newX % 3] == INF) {
                        continue;
                    }

                    dp[j][i % 3] = Math.min(dp[j][i % 3], dp[newY][newX % 3] + 1);
                }

            }
        }

        // 3. return
        return dp[m - 1][(n - 1) % 3] != INF ? dp[m - 1][(n - 1) % 3] : -1;
    }
}