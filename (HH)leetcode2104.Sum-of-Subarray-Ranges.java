import java.util.Arrays;
import java.util.Stack;

class Solution {

    // Time: O(N)
    // Space: O(N)
    public long subArrayRanges(int[] nums) {

        if (nums.length == 0 || nums == null) {
            return 0L;
        }

        int length = nums.length;
        long[] min = count(nums, true); // 紀錄某個位置成為區間最小的個數
        long[] max = count(nums, false); // 紀錄某個位置成為區間最大的個數

        long ans = 0L;
        for (int i = 0; i < length; i++) {
            ans += nums[i] * (max[i] - min[i]);
        }

        return ans;
    }

    private long[] count(int[] nums, boolean calMin) {

        Stack<Integer> stack = new Stack<>();

        int[] next = new int[nums.length]; // i前(右邊)滿足條件的最近index
        Arrays.fill(next, nums.length);
        // 如果一個點永遠不會被更新到，代表他的右邊沒有比他最的，他是最值，因為下面要算距離所以要寫超出範圍
        for (int i = 0; i < nums.length; i++) {
            // calMin == true => 找比自己小的，在這個『比自己小的』範圍之內，自己是最小的
            // calMin要記得加上括號，避免編譯器以為是 while ((!stack.isEmpty() && calMin) ? ...)
            while (!stack.isEmpty() && (calMin ? nums[i] < nums[stack.peek()] : nums[i] > nums[stack.peek()])) {
                next[stack.pop()] = i;
            }
            stack.push(i);
        }

        stack.clear();

        int[] previous = new int[nums.length]; // i後(左邊)滿足條件的最近index
        Arrays.fill(previous, -1);
        // 如果一個點永遠不會被更新到，代表他的左邊沒有比他最的，他是最值，因為下面要算距離所以要寫成-1
        for (int i = nums.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && (calMin ? nums[i] <= nums[stack.peek()] : nums[i] >= nums[stack.peek()])) {
                previous[stack.pop()] = i;
            }
            stack.push(i);
        }

        long[] result = new long[nums.length];
        for (int i = 0; i < nums.length; i++) {
            result[i] = (long) (i - previous[i]) * (next[i] - i);
        }

        return result;
    }
}