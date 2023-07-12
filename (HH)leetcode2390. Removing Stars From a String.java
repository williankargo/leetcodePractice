import java.util.Stack;

class Solution {

    // TC: O(N)
    // SC: O(N)
    public String removeStars(String s) {

        int starCount = 0;
        int length = s.length();
        Stack<Character> stack = new Stack<>();
        for (int i = length - 1; i >= 0; i--) {
            char word = s.charAt(i);
            if (word == '*') {
                starCount++;
            } else {
                if (starCount != 0) {
                    starCount--;
                } else {
                    stack.push(word);
                }

            }
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        return sb.toString();
    }
}