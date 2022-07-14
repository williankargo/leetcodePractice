import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://aaronice.gitbook.io/lintcode/dynamic_programming/longest_increasing_continuous_subsequence_ii

class Solution {

    // 這題提醒了：使用動態規劃時要有序（背包問題除外）
    // lintcode76 的變體
    // Time: O(NM * 4)
    // Space: O(NM)
    public int longestContinuousIncreasingSubsequence(int[][] matrix) {

        if (matrix == null || matrix.length == 0) {
            return 0;
        }

        List<List<Integer>> oneDarray = new ArrayList<>();
        int m = matrix.length; // j
        int n = matrix[0].length; // i
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                oneDarray.add(Arrays.asList(i, j, matrix[j][i])); // x y value
            }
        }

        // 記得變成有序！才可以使用動態規劃
        oneDarray.sort((p1, p2) -> p1.get(2) - p2.get(2));

        // 0.
        // 以 matrix(i, j) 座標位置為結尾的 LCIS 的長度
        int[][] dp = new int[n][m];

        // 1.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dp[i][j] = 1;
            }
        }

        // 2.
        // 從小處裡到大
        int oneDarrayLength = oneDarray.size();
        int[][] directions = new int[][] { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
        for (int i = 0; i < oneDarrayLength; i++) {
            int originalX = oneDarray.get(i).get(0);
            int originalY = oneDarray.get(i).get(1);

            for (int[] direction : directions) {

                int preX = originalX + direction[0];
                int preY = originalY + direction[1];

                if (preX < 0 || preY < 0 || preX > (n - 1) || preY > (m - 1)) {
                    continue;
                }

                if (matrix[preX][preY] < matrix[originalX][originalY]) {
                    dp[originalX][originalY] = Math.max(dp[preX][preY] + 1, dp[originalX][originalY]);
                }
            }
        }

        // 3.
        int longest = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                longest = Math.max(longest, dp[i][j]);
            }
        }

        return longest;
    }
}