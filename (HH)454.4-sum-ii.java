import java.util.HashMap;
import java.util.Map;

/*
 * @lc app=leetcode id=454 lang=java
 *
 * [454] 4Sum II
 */

// @lc code=start
// Time O(N^2)
// Space O(K)
// similar to twosum3
class Solution {
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {

        Map<Integer,Integer> map = new HashMap<Integer,Integer>();

        for (int one : nums1) {
            for (int two : nums2) {
                int sum = one + two;
                map.put(sum, map.getOrDefault(sum, 0) + 1); // sum, 次數
            }
        }
        
        int count = 0;
        for(int three: nums3){
            for(int four: nums4){
                int sum = three + four;
                count += map.getOrDefault(-sum, 0);
            }
        }

        return count;
    }
}
// @lc code=end

