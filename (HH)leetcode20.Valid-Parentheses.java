import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

class Solution {

    // Time: O(N)
    // Space: O(N)
    public boolean isValid(String s) {

        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');

        // Stack
        Deque<Character> deque = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            char item = s.charAt(i);
            if (!deque.isEmpty() && deque.peekFirst() == map.get(item)) {
                deque.pollFirst();
            } else {
                deque.offerFirst(item);
            }
        }

        return deque.size() == 0;
    }
}