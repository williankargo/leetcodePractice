class Solution {
    // TC: O(N)
    // SC: O(1)
    public boolean isSubsequence(String s, String t) {

        int sp = 0;
        int tp = 0;
        while (sp < s.length()) {
            if (tp >= t.length()) { // 雙指針問題，有對指針進行操作，就要注意越界問題！
                return false;
            }
            if (s.charAt(sp) == t.charAt(tp)) {
                sp++;
                tp++;
            } else {
                tp++;
            }
        }

        return true;
    }
}