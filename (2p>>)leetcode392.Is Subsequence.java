// TC: O(N)
// SC: O(1)
class Solution {
    public boolean isSubsequence(String s, String t) {

        int tp = 0;

        for (int i = 0; i < s.length(); i++) {
            while (tp < t.length() && s.charAt(i) != t.charAt(tp)) {
                tp++;
            }
            if (tp >= t.length()) {
                return false;
            }
            tp++;
        }

        return true;
    }
}