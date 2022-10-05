import java.util.HashMap;
import java.util.Map;

class Solution {
    public String minWindow(String s, String t) {

        // Time: O(s_length + t_length)
        // Space: O(t_length)
        // 用HashMap避免j到底了就不能算了的問題
        Map<Character, Integer> target = new HashMap<>();
        for (char item : t.toCharArray()) {
            target.put(item, target.getOrDefault(item, 0) + 1);
        }
        int t_length = t.length();

        int countS = 0;
        int j = 0;
        int min = Integer.MAX_VALUE;
        String minString = "";
        for (int i = 0; i < s.length(); i++) {
            while (j < s.length() && countS < t_length) {
                if (target.containsKey(s.charAt(j))) {
                    if (target.get(s.charAt(j)) > 0) {
                        countS++;
                    }
                    target.put(s.charAt(j), target.get(s.charAt(j)) - 1);
                }
                j++;
            }

            if (countS >= t_length) {
                if ((j - i) < min) {
                    min = j - i;
                    minString = s.substring(i, j);
                }
            }

            // move forward
            if (target.containsKey(s.charAt(i))) {
                if (target.get(s.charAt(i)) >= 0) {
                    countS--;
                }
                target.put(s.charAt(i), target.get(s.charAt(i)) + 1);
            }
        }

        return minString;
    }
}