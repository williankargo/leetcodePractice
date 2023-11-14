// solution: https://www.youtube.com/watch?v=081AqOuasw0&ab_channel=JessicaLin
// TC: O(N)
// SC: O(N)

import java.util.Stack;

class Solution {
    public int calculate(String s) {

        int sum = 0;
        char operator = '.';
        Stack<Character> opStack = new Stack<>();
        Stack<Integer> sumStack = new Stack<>();

        char[] sArray = s.toCharArray();
        for (int i = 0; i < sArray.length; i++) {
            char item = sArray[i];
            if (item == '(') {
                opStack.push(operator);
                sumStack.push(sum);
                sum = 0;
                operator = '.';
            } else if (item == ')') {
                operator = opStack.pop();
                int prevSum = sumStack.pop();
                sum = Math(prevSum, sum, operator);
            } else if (item == '+' || item == '-' || item == '*' || item == '/') {
                operator = item;
            } else if (Character.isDigit(item)) {
                int num = item - '0';
                int j = 0;
                for (j = i + 1; j < sArray.length; j++) {
                    if (Character.isDigit(s.charAt(j))) {
                        num = 10 * num + (s.charAt(j) - '0');
                    } else {
                        break;
                    }
                }
                i = j - 1;
                sum = Math(sum, num, operator);
            }
        }

        return sum;
    }

    private int Math(int num1, int num2, char operator) {
        if (operator == '+') {
            return num1 + num2;
        } else if (operator == '-') {
            return num1 - num2;
        } else if (operator == '*') {
            return num1 * num2;
        } else if (operator == '/') {
            return num1 / num2;
        }
        return num1 + num2; // default '+'
    }
}