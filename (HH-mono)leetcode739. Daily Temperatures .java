// Monotonic Stack
// Solution: https://www.youtube.com/watch?v=cTBiBSnjO3c&ab_channel=NeetCode
// TC: O(N)
// SC: O(N)

import java.util.Stack;

class Solution {
    public int[] dailyTemperatures(int[] temperatures) {

        int[] ans = new int[temperatures.length];
        Stack<Point> stack = new Stack<>();
        stack.push(new Point(0, temperatures[0]));

        for (int i = 1; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[i] > stack.peek().value) {
                Point point = stack.pop();
                ans[point.index] = i - point.index;
            }
            stack.push(new Point(i, temperatures[i]));
        }

        return ans;
    }
}

class Point {
    int index;
    int value;

    Point(int index, int value) {
        this.index = index;
        this.value = value;
    }
}