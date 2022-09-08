import java.util.Arrays;
import java.util.Stack;

class Solution {

    // 和 leetcode.2104 有關聯
    // Time: O(N)
    // Space: O(N)
    // https://www.youtube.com/watch?v=TZyBPy7iOAw
    public int sumSubarrayMins(int[] arr) {

        if (arr.length == 0 || arr == null) {
            return 0;
        }

        Stack<Integer> stack = new Stack<>();

        // find next Smaller
        int[] next = new int[arr.length]; // 存某index點的後一個小於自己的值的Index
        Arrays.fill(next, arr.length); // 預設值為最右idex的下一個，代表目前處理到的這個index是最值
        for (int i = 0; i < arr.length; i++) {
            // 往右找第一個出現小於自己的，如果遇到等於自己的就要停止，只是找prev時遇到自己就要算下去
            while (!stack.isEmpty() && arr[i] < arr[stack.peek()]) {
                next[stack.pop()] = i;
            }
            stack.push(i);
        }

        stack.clear();

        // find prev Smaller
        int[] prev = new int[arr.length]; // 存某index點的前一個小於自己的值的Index
        Arrays.fill(prev, -1); // 預設值為最左index的上一個，代表目前處理到的這個index是最值
        for (int i = arr.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[i] <= arr[stack.peek()]) {
                prev[stack.pop()] = i;
            }
            stack.push(i);
        }

        // System.out.println("int[] next: " + Arrays.toString(next));
        // System.out.println("int[] prev: " + Arrays.toString(prev));

        long ans = 0; // 要用long，注意可能會爆位
        long M = (long) ((1e+9) + 7);
        for (int i = 0; i < arr.length; i++) {
            long count = ((next[i] - i) * (i - prev[i]));
            ans += (arr[i] * count);
        }

        return (int) (ans % M);
    }
}