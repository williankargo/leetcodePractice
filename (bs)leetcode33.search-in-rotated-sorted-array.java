/*
 * @lc app=leetcode id=33 lang=java
 *
 * [33] Search in Rotated Sorted Array
 */

// @lc code=start
class Solution {

    // Time : O(log(N))
    // Space : O(3)
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            // left large
            if (nums[mid] > nums[end]) {
                if (nums[start] <= target && nums[mid] >= target) {
                    end = mid;
                } else {
                    start = mid;
                }
            } else {
                // right large
                if (nums[mid] <= target && nums[end] >= target) {
                    start = mid;
                } else {
                    end = mid;
                }
            }
        }
        
        // 要注意答案不存在的狀況
        int ans = (nums[start] == target) ? start : (nums[end] == target) ? end : -1;
        
        return ans;
    }
}
// @lc code=end

