// TC: O(N)
// SC: O(N)

import java.util.Stack;

class Solution {
    public String removeDuplicateLetters(String s) {

        // Map<Character, Integer> freqMap = new HashMap<>();
        int[] freq = new int[26];
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            // freqMap.put(s.charAt(i), freqMap.getOrDefault(s.charAt(i), 0) + 1);
            freq[s.charAt(i) - 'a']++;
        }

        for (int i = 0; i < s.length(); i++) {

            // 目標就是要找到一個合理然後最小的領頭，然後在稍後的stack中放不重複的字

            // freqMap.put(s.charAt(i), freqMap.get(s.charAt(i)) - 1);
            freq[s.charAt(i) - 'a']--;

            if (stack.contains(s.charAt(i))) {
                continue;
            }

            while (!stack.isEmpty() && stack.peek() > s.charAt(i) && freq[stack.peek() - 'a'] > 0) {
                stack.pop();
            }

            stack.push(s.charAt(i));
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        return sb.reverse().toString(); // we can use reverse() in StringBuilder() !!!
    }
}