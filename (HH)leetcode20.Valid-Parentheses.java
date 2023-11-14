import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

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

// TC: O(N)
// SC: O(N)
class Solution2 {
    public boolean isValid(String s) {

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);
            if (curr == '(' || curr == '[' || curr == '{') {
                stack.push(curr);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                char popChar = stack.pop();
                if ((popChar == '(' && curr != ')')
                        || (popChar == '[' && curr != ']')
                        || (popChar == '{' && curr != '}')) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }
}