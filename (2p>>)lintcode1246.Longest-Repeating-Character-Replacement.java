import java.util.HashMap;
import java.util.Map;

class Solution {

    // 同向雙指針
    // Time: O(N)
    // Space: O(N)
    public int characterReplacement(String s, int k) {

        if (s == null || s.length() == 0) {
            return 0;
        }

        int j = 0; // 當前subString的尾端後後一個值（為了有效判斷maxFreq）
        int maxFreq = 0; // 當前出現最多次的字母的次數
        int maxLength = 0; // 當前最長的substring
        int count; // temp
        Map<Character, Integer> counter = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            while (j < s.length() && ((j - i) - maxFreq) <= k) { // 需要替換的字母數，這裡的maxFreq記錄到此substring的末尾而不是末尾下一個
                count = counter.getOrDefault(s.charAt(j), 0) + 1;
                counter.put(s.charAt(j), count);
                maxFreq = Math.max(maxFreq, count);
                j++;
            }
            if ((j - i) - maxFreq > k) { // 超過一個k
                maxLength = Math.max((j - i) - 1, maxLength);
            } else { // 需要替換的字母數<=k且j已經走到沒有下一步了
                return Math.max((j - i), maxLength);
            }

            count = counter.get(s.charAt(i)) - 1; // 因為i要往前一位了
            counter.put(s.charAt(i), count); // 因為i要往前一位了

            maxFreq = getMaxFreq(counter);
        }

        return maxLength;
    }

    private int getMaxFreq(Map<Character, Integer> counter) {
        int max = 0;
        for (int value : counter.values()) {
            max = Math.max(max, value);
        }
        return max;
    }
}