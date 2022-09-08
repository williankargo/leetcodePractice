import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

class Solution {

    // 和 leetcode.2104 有關聯
    // Time: O(N)
    // Space: O(N)
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {

        int[] ans = new int[nums1.length];

        if (nums1.length == 0 || nums2.length == 0) {
            Arrays.fill(ans, -1);
            return ans;
        }

        HashMap<Integer, Integer> map = new HashMap<>(); // 紀錄某個數字next greater number
        Stack<Integer> stack = new Stack<>();

        // find nextGreater
        for (int i = 0; i < nums2.length; i++) {
            // 如果目前下一個不是next greater，先放到stack中存著
            while (!stack.isEmpty() && nums2[i] > stack.peek()) {
                map.put(stack.pop(), nums2[i]);
            }
            stack.push(nums2[i]);
        }

        for (int i = 0; i < nums1.length; i++) {
            ans[i] = map.getOrDefault(nums1[i], -1);
        }

        return ans;
    }
}