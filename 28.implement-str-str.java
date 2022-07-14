/*
 * @lc app=leetcode id=28 lang=java
 *
 * [28] Implement strStr()
 */

// O(n^2)
class Solution {
    public int strStr(String haystack, String needle) {
        if (needle == null || needle.equals("")) {
            return 0;
        }

        for (int i = 0; i < haystack.length() - needle.length() + 1; i++) {
            boolean answer = true;
            for (int j = 0; j < needle.length(); j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    answer = false;
                    break; // only breaks inner for loop
                }
            }
            if (answer) {
                return i; // find the first index
            }
        }
        return -1;
    }
}
// @lc code=end

