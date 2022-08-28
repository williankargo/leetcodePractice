
// Time O(n^2)
class Solution {

    // 背向雙指針
    public String longestPalindrome(String s) {
        if (s == null) {
            return null;
        }

        String longest = ""; // 初始化如果是null 一開始不能計算length()
        for (int i = 0; i < s.length(); i++) {

            // odd
            String currentOddPalindrome = findCurrentLongest(s, i, i);
            if (currentOddPalindrome.length() > longest.length()) {
                longest = currentOddPalindrome;
            }

            // even
            String currentEvenPalindrome = findCurrentLongest(s, i, i + 1);
            if (currentEvenPalindrome.length() > longest.length()) {
                longest = currentEvenPalindrome;
            }

        }
        return longest;

    }

    private String findCurrentLongest(String s, int left, int right) {

        while (left >= 0 && right < s.length()) {
            if (s.charAt(left) != s.charAt(right)) {
                break;
            }
            left--;
            right++;
        }

        return s.substring(left + 1, right);
    }
}

// dynamic programming version
// leetcode 5
class Solution2 {

    // Time: O(n^2)
    // Space: O(n^2)
    public String longestPalindrome(String s) {

        if (s == null || s.length() == 0) {
            return "";
        }

        // 0.
        // 這一段 i, j 是不是回文串
        int n = s.length();
        boolean[][] dp = new boolean[n][n];

        int maxLength = 0;
        int startIndex = 0;
        char[] s_char = s.toCharArray();

        // 1.
        // 初始化長度1
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
            maxLength = 1;
            startIndex = i;
        }

        // 初始化長度2
        for (int i = 0; i < (n - 1); i++) {
            if (s_char[i] == s_char[i + 1]) {
                dp[i][i + 1] = true;
                maxLength = 2;
                startIndex = i;
            }
        }

        // 2.
        // 遍歷長度
        // 從3開始，1,2已經初始化了
        for (int length = 3; length <= n; length++) {
            // 左指針i
            for (int i = 0; i < n && (i + length - 1) < n; i++) {
                // 右指針j
                int j = i + length - 1;
                // 大區間依賴於小區間
                dp[i][j] = dp[i + 1][j - 1] && s_char[i] == s_char[j];
                if (dp[i][j] && (j - i + 1) > maxLength) {
                    maxLength = (j - i + 1);
                    startIndex = i;
                }
            }
        }

        // 3.
        return s.substring(startIndex, startIndex + maxLength);
    }
}