/*
 * @lc app=leetcode id=153 lang=java
 *
 * [153] Find Minimum in Rotated Sorted Array
 */

// @lc code=start
class Solution {

    // Time: O(log(N))
    // Space: O(k)
    public int findMin(int[] nums) {

        if (nums.length == 0 || nums == null) {
            return -1;
        }
        
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if(nums[mid] > nums[end]){
                start = mid;
            } else {
                // <
                end = mid;
            }
        }
        
        return Math.min(nums[start], nums[end]);
    }
}
// @lc code=end

