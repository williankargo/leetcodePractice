import java.util.Stack;

class Solution {

    // TC: O(N)
    // SC: O(N)
    public int[] asteroidCollision(int[] asteroids) {

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < asteroids.length; i++) {

            int rock = asteroids[i];
            boolean flag = true;

            // 把while當if用
            // 經過畫圖分析： - 才需要進入while做更深一步的探討 （由array前往後加入stack）
            // +
            while (flag && !stack.isEmpty() && rock < 0 && stack.peek() > 0) {
                if (stack.peek() < Math.abs(rock)) {
                    stack.pop();
                } else if (stack.peek() == Math.abs(rock)) {
                    stack.pop();
                    flag = false;
                } else if (stack.peek() > Math.abs(rock)) {
                    flag = false;
                }
            }
            if (flag) {
                stack.push(rock);
            }
        }

        int[] ans = new int[stack.size()];
        int i = stack.size() - 1;
        while (!stack.isEmpty()) {
            ans[i--] = stack.pop();
        }

        return ans;
    }
}