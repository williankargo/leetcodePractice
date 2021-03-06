/*
 * @lc app=leetcode id=162 lang=java
 *
 * [162] Find Peak Element
 */

// @lc code=start
class Solution {
    // Time: log(N)
    // Space:  K
    public int findPeakElement(int[] nums) {

        if (nums == null || nums.length == 0) {
            return -1;
        }
        
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid + 1] > nums[mid]) {
                start = mid;
            } else if(nums[mid - 1] > nums[mid]){
                end = mid;
            } else {
                // peak == mid
                return mid;
            }
            
        }
        int ans = Math.max(nums[start], nums[end]) == nums[start] ? start : end;
        return ans;
    }
}
// @lc code=end

