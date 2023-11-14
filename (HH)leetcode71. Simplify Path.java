// solution: https://www.youtube.com/watch?v=qYlHrAKJfyA&ab_channel=NeetCode
// TC: O(N)
// SC: O(N)

import java.util.Stack;

class Solution {
    public String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();
        String[] pathArray = path.split("/");

        for (String word : pathArray) {
            if (word.equals("..")) { // String的比較要用equals() !!!
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else if (!word.equals("") && !word.equals(".")) {
                stack.push(word);
            }
        }

        return "/" + String.join("/", stack);
    }
}