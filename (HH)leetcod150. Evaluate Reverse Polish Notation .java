// TC: O(N)
// SC: O(N)

import java.util.Stack;

class Solution {

    public int evalRPN(String[] tokens) {

        Stack<String> stack = new Stack<>();
        for (String token : tokens) {
            if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) {
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                token = String.valueOf(Math(num1, num2, token));
            }
            stack.push(token);
        }

        return Integer.parseInt(stack.peek());
    }

    private int Math(int num1, int num2, String sign) {
        if (sign.equals("+")) {
            return num1 + num2;
        } else if (sign.equals("-")) {
            return num1 - num2;
        } else if (sign.equals("*")) {
            return num1 * num2;
        } else if (sign.equals("/")) {
            return num1 / num2;
        }
        return -1;
    }
}