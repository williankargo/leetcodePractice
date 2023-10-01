// Solution 1: Bottom-Up
// solution: https://www.youtube.com/watch?v=XYi2-LPrwm4&ab_channel=NeetCode
// Bottom-up
// TC: O(MN)
// SC: O(MN)
class Solution1 {
    public int minDistance(String word1, String word2) {

        // 1.
        // 從此ij出發往右往下，j要進行多少運算才能跟i一樣
        // 記得多一個，當空值，方便 bottom-up
        int[][] dp = new int[word2.length() + 1][word1.length() + 1];

        // 2.
        for (int j = 0; j <= word1.length(); j++) {
            dp[word2.length()][j] = word1.length() - j;
        }
        for (int i = 0; i <= word2.length(); i++) {
            dp[i][word1.length()] = word2.length() - i;
        }

        // 3.
        for (int i = word2.length() - 1; i >= 0; i--) {
            for (int j = word1.length() - 1; j >= 0; j--) {
                if (word1.charAt(j) == word2.charAt(i)) {
                    dp[i][j] = dp[i + 1][j + 1];
                } else {
                    // need to
                    // insert (dp[i][j + 1])
                    // delete i (dp[i + 1][j])
                    // replace ij (dp[i + 1][j + 1])
                    dp[i][j] = 1 + Math.min(dp[i][j + 1], Math.min(dp[i + 1][j], dp[i + 1][j + 1]));
                }
            }
        }

        // 4.
        return dp[0][0];
    }
}

// Solution 2: Top-Down
class Solution2 {
    public int minDistance(String word1, String word2) {

        // 1.
        // 此j往左看，要進行多少操作，才能跟此i往上看一樣
        int[][] dp = new int[word2.length() + 1][word1.length() + 1];

        // 2.
        for (int j = 0; j <= word1.length(); j++) {
            dp[0][j] = j;
        }
        for (int i = 0; i <= word2.length(); i++) {
            dp[i][0] = i;
        }

        // 3.
        for (int i = 1; i <= word2.length(); i++) {
            for (int j = 1; j <= word1.length(); j++) {
                if (word1.charAt(j - 1) == word2.charAt(i - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // replace, insert, delete
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j]));
                }
            }
        }

        // 4.
        return dp[word2.length()][word1.length()];
    }
}