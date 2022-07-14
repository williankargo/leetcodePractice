/*
 * @lc app=leetcode id=5 lang=java
 *
 * [5] Longest Palindromic Substring
 */

// @lc code=start
// Enuneration O(n^2)
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
// todo: 可以用動態規劃優化
// @lc code=end

