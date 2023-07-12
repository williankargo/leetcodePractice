import java.util.Stack;

class Solution {

    // TC: O(k * N)
    // SC: O(N)
    public String decodeString(String s) {

        Stack<StringBuilder> stack_string = new Stack<>();
        Stack<Integer> stack_digit = new Stack<>();

        int k = 0;
        StringBuilder currentString = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);
            if (Character.isDigit(currentChar)) {
                k = k * 10 + currentChar - '0';
            } else if (currentChar == '[') {
                stack_string.push(currentString);
                stack_digit.push(k);
                currentString = new StringBuilder();
                k = 0;
            } else if (currentChar == ']') {
                StringBuilder getString = stack_string.pop();
                Integer times = stack_digit.pop();
                for (int j = 0; j < times; j++) {
                    getString.append(currentString);
                }
                currentString = getString;
            } else {
                currentString.append(currentChar);
            }

        }

        return currentString.toString();
    }
}